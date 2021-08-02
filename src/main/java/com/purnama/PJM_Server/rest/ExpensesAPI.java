/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.Expenses;
import com.purnama.PJM_Server.model.transactional.ItemExpenses;
import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemExpensesDraft;
import com.purnama.PJM_Server.service.ExpensesDraftService;
import com.purnama.PJM_Server.service.ExpensesService;
import com.purnama.PJM_Server.service.ItemExpensesDraftService;
import com.purnama.PJM_Server.service.ItemExpensesService;
import com.purnama.PJM_Server.service.NumberingService;
import com.purnama.PJM_Server.service.PartnerService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/expenses")
public class ExpensesAPI {
    
    private final ExpensesService expensesService;
    private final ItemExpensesService itemexpensesService;
    private final ExpensesDraftService expensesdraftService;
    private final ItemExpensesDraftService itemexpensesdraftService;
    private final NumberingService numberingService;
    private final PartnerService partnerService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getExpensesList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Expenses> ls = expensesService.findByNumberContaining(keyword, page, itemperpage);

        Pagination<Expenses> expensespagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(expensespagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getExpenses(@PathVariable("id") int id) {
        return ResponseEntity.ok(expensesService.findById(id));
    }
    
    @PostMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> addExpenses(@PathVariable("id") int id) {
        ExpensesDraft expensesdraft = expensesdraftService.findById(id).get();
        
        List<ItemExpensesDraft> itemexpensesdrafts = itemexpensesdraftService.findByExpensesdraft(expensesdraft);
        
        Numbering numbering = expensesdraft.getNumbering();
        
        if(numbering == null){
            return ResponseEntity.badRequest().body("Numbering is not valid");
        }
        else if(expensesdraft.getCurrency() == null){
            return ResponseEntity.badRequest().body("Currency is not valid");
        }
        else if(expensesdraft.getPartner() == null){
            return ResponseEntity.badRequest().body("Partner is not valid");
        }
        else if(itemexpensesdrafts.isEmpty()){
            return ResponseEntity.badRequest().body("Invoice is empty");
        }
        else if(expensesdraft.getDuedate().isBefore(expensesdraft.getInvoicedate()) || expensesdraft.getDuedate().isEqual(expensesdraft.getInvoicedate())){
            return ResponseEntity.badRequest().body("Due date is set before invoice date");
        }
        else if(expensesdraft.getRate() <= 0){
            return ResponseEntity.badRequest().body("Rate cannot be lower than 1");
        }
        
        Expenses expenses = new Expenses();
        expenses.setCreateddate(LocalDateTime.now());
        expenses.setCurrency(expensesdraft.getCurrency());
        expenses.setCurrencycode(expensesdraft.getCurrency().getCode());
        expenses.setCurrencyname(expensesdraft.getCurrency().getName());
        expenses.setDiscount(expensesdraft.getDiscount());
        expenses.setDraftid(expensesdraft.getDraftid());
        expenses.setDuedate(expensesdraft.getDuedate());
        expenses.setFreight(expensesdraft.getFreight());
        expenses.setInvoicedate(expensesdraft.getInvoicedate());
        expenses.setLastmodified(LocalDateTime.now());
        expenses.setNote(expensesdraft.getNote());
        expenses.setNumber(numbering.toString());
        expenses.setPaid(0);
        expenses.setPartner(expensesdraft.getPartner());
        expenses.setPartneraddress(expensesdraft.getPartner().getAddress());
        expenses.setPartnercode(expensesdraft.getPartner().getCode());
        expenses.setPartnername(expensesdraft.getPartner().getName());
        expenses.setPrinted(0);
        expenses.setRate(expensesdraft.getRate());
        expenses.setRounding(expensesdraft.getRounding());
        expenses.setStatus(true);
        expenses.setTax(expensesdraft.getTax());
        expenses.setSubtotal(expensesdraft.getSubtotal());
        expenses.setUser(expensesdraft.getUser());
        expenses.setUsercode(expensesdraft.getUser().getCode());
        expenses.setWarehouse(expensesdraft.getWarehouse());
        expenses.setWarehousecode(expensesdraft.getWarehouse().getCode());
        
        expensesService.save(expenses);
        
        List<ItemExpenses> itemexpenseslist = new ArrayList<>();
        for(ItemExpensesDraft itemexpensesdraft : itemexpensesdrafts){
            ItemExpenses itemexpenses = new ItemExpenses();
            itemexpenses.setQuantity(itemexpensesdraft.getQuantity());
            itemexpenses.setPrice(itemexpensesdraft.getPrice());
            itemexpenses.setDiscount(itemexpensesdraft.getDiscount());
            itemexpenses.setDescription(itemexpensesdraft.getDescription());
            itemexpenses.setExpenses(expenses);
            itemexpenses.setBox(itemexpensesdraft.getBox());
            itemexpenseslist.add(itemexpenses);
        }
        
        itemexpensesService.saveAll(itemexpenseslist);
        
        Partner partner = expenses.getPartner();
        partner.setBalance(partner.getBalance() - expenses.getTotaldefaultcurrency());
        
        partnerService.save(partner);
        
        numbering.setCurrent(numbering.getCurrent()+1);
        
        if(numbering.getCurrent() != numbering.getEnd()){
            
        }
        else{
            numbering.setStatus(false);
        }
        
        numberingService.save(numbering);
        
        itemexpensesdraftService.deleteAll(itemexpensesdrafts);
        
        expensesdraftService.deleteById(id);
        
        return ResponseEntity.ok(expenses.getId());
    }
}
