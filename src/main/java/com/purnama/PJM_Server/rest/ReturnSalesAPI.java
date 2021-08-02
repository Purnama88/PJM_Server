/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.ItemReturnSales;
import com.purnama.PJM_Server.model.transactional.ReturnSales;
import com.purnama.PJM_Server.model.transactional.ReturnSales;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnSalesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import com.purnama.PJM_Server.service.ItemReturnSalesDraftService;
import com.purnama.PJM_Server.service.ItemReturnSalesService;
import com.purnama.PJM_Server.service.NumberingService;
import com.purnama.PJM_Server.service.PartnerService;
import com.purnama.PJM_Server.service.ReturnSalesDraftService;
import com.purnama.PJM_Server.service.ReturnSalesService;
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
@RequestMapping("/api/v1/returnsales")
public class ReturnSalesAPI {
    
    private final ReturnSalesService returnsalesService;
    private final ItemReturnSalesService itemreturnsalesService;
    private final ReturnSalesDraftService returnsalesdraftService;
    private final ItemReturnSalesDraftService itemreturnsalesdraftService;
    private final NumberingService numberingService;
    private final PartnerService partnerService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getReturnSalesList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ReturnSales> ls = returnsalesService.findByNumberContaining(keyword, page, itemperpage);

        Pagination<ReturnSales> returnsalespagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(returnsalespagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getReturnSales(@PathVariable("id") int id) {
        return ResponseEntity.ok(returnsalesService.findById(id));
    }
    
    @PostMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> addReturnSales(@PathVariable("id") int id) {
        ReturnSalesDraft returnsalesdraft = returnsalesdraftService.findById(id).get();
        
        List<ItemReturnSalesDraft> itemreturnsalesdrafts = itemreturnsalesdraftService.findByReturnsalesdraft(returnsalesdraft);
        
        Numbering numbering = returnsalesdraft.getNumbering();
        
        if(numbering == null){
            return ResponseEntity.badRequest().body("Numbering is not valid");
        }
        else if(returnsalesdraft.getCurrency() == null){
            return ResponseEntity.badRequest().body("Currency is not valid");
        }
        else if(returnsalesdraft.getPartner() == null){
            return ResponseEntity.badRequest().body("Partner is not valid");
        }
        else if(itemreturnsalesdrafts.isEmpty()){
            return ResponseEntity.badRequest().body("Return is empty");
        }
        else if(returnsalesdraft.getDuedate().isBefore(returnsalesdraft.getInvoicedate()) || returnsalesdraft.getDuedate().isEqual(returnsalesdraft.getInvoicedate())){
            return ResponseEntity.badRequest().body("Due date is set before return date");
        }
        else if(returnsalesdraft.getRate() <= 0){
            return ResponseEntity.badRequest().body("Rate cannot be lower than 1");
        }
        
        ReturnSales returnsales = new ReturnSales();
        returnsales.setCreateddate(LocalDateTime.now());
        returnsales.setCurrency(returnsalesdraft.getCurrency());
        returnsales.setCurrencycode(returnsalesdraft.getCurrency().getCode());
        returnsales.setCurrencyname(returnsalesdraft.getCurrency().getName());
        returnsales.setDiscount(returnsalesdraft.getDiscount());
        returnsales.setDraftid(returnsalesdraft.getDraftid());
        returnsales.setDuedate(returnsalesdraft.getDuedate());
        returnsales.setFreight(returnsalesdraft.getFreight());
        returnsales.setInvoicedate(returnsalesdraft.getInvoicedate());
        returnsales.setLastmodified(LocalDateTime.now());
        returnsales.setNote(returnsalesdraft.getNote());
        returnsales.setNumber(numbering.toString());
        returnsales.setPaid(0);
        returnsales.setPartner(returnsalesdraft.getPartner());
        returnsales.setPartneraddress(returnsalesdraft.getPartner().getAddress());
        returnsales.setPartnercode(returnsalesdraft.getPartner().getCode());
        returnsales.setPartnername(returnsalesdraft.getPartner().getName());
        returnsales.setPrinted(0);
        returnsales.setRate(returnsalesdraft.getRate());
        returnsales.setRounding(returnsalesdraft.getRounding());
        returnsales.setStatus(true);
        returnsales.setTax(returnsalesdraft.getTax());
        returnsales.setSubtotal(returnsalesdraft.getSubtotal());
        returnsales.setUser(returnsalesdraft.getUser());
        returnsales.setUsercode(returnsalesdraft.getUser().getCode());
        returnsales.setWarehouse(returnsalesdraft.getWarehouse());
        returnsales.setWarehousecode(returnsalesdraft.getWarehouse().getCode());
        
        returnsalesService.save(returnsales);
        
        List<ItemReturnSales> itemreturnsaleslist = new ArrayList<>();
        for(ItemReturnSalesDraft itemreturnsalesdraft : itemreturnsalesdrafts){
            ItemReturnSales itemreturnsales = new ItemReturnSales();
            itemreturnsales.setQuantity(itemreturnsalesdraft.getQuantity());
            itemreturnsales.setPrice(itemreturnsalesdraft.getPrice());
            itemreturnsales.setDiscount(itemreturnsalesdraft.getDiscount());
            itemreturnsales.setDescription(itemreturnsalesdraft.getDescription());
            itemreturnsales.setReturnsales(returnsales);
            itemreturnsales.setBox(itemreturnsalesdraft.getBox());
            itemreturnsales.setItem(itemreturnsalesdraft.getItem());
            itemreturnsaleslist.add(itemreturnsales);
        }
        
        itemreturnsalesService.saveAll(itemreturnsaleslist);
        
        Partner partner = returnsales.getPartner();
        partner.setBalance(partner.getBalance() - returnsales.getTotaldefaultcurrency());
        
        partnerService.save(partner);
        
        numbering.setCurrent(numbering.getCurrent()+1);
        
        if(numbering.getCurrent() != numbering.getEnd()){
            
        }
        else{
            numbering.setStatus(false);
        }
        
        numberingService.save(numbering);
        
        itemreturnsalesdraftService.deleteAll(itemreturnsalesdrafts);
        
        returnsalesdraftService.deleteById(id);
        
        return ResponseEntity.ok(returnsales.getId());
    }
}
