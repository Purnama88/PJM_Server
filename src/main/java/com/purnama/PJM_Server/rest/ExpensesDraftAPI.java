/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.pagination.ExpensesDraftPagination;
import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.CurrencyService;
import com.purnama.PJM_Server.service.ExpensesDraftService;
import com.purnama.PJM_Server.util.IdGenerator;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/v1/expensesdrafts")
public class ExpensesDraftAPI {
    
    private final ExpensesDraftService expensesdraftService;
    
    private final CurrencyService currencyService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getExpensesDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ExpensesDraft> ls = expensesdraftService.findByDraftidContaining(keyword, page, itemperpage);

        ExpensesDraftPagination expensesdraft_pagination = new ExpensesDraftPagination(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(expensesdraft_pagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getExpensesDraft(@PathVariable("id") int id) {
        return ResponseEntity.ok(expensesdraftService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveExpensesDraft(HttpServletRequest httpRequest) {
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        User user = new User();
        user.setId(JwtUtil.parseToken(header.substring(7)));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(JwtUtil.parseToken2(header.substring(7)));
        
        Currency currency = currencyService.findByDefaultcurrencyTrue().get();
        
        ExpensesDraft expensesdraft = new ExpensesDraft();
        expensesdraft.setDraftid(IdGenerator.generateId());
        expensesdraft.setInvoicedate(LocalDateTime.now());
        expensesdraft.setWarehouse(warehouse);
        expensesdraft.setNote("");
        expensesdraft.setSubtotal(0);
        expensesdraft.setDiscount(0);
        expensesdraft.setRounding(0);
        expensesdraft.setFreight(0);
        expensesdraft.setTax(0);
        expensesdraft.setUser(user);
        expensesdraft.setLastmodified(LocalDateTime.now());
        expensesdraft.setCreateddate(LocalDateTime.now());
        expensesdraft.setCurrency(currency);
        
        expensesdraftService.save(expensesdraft);
        
        return ResponseEntity.ok(expensesdraft.getId());
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateExpensesDraft(HttpServletRequest httpRequest,
            @RequestBody ExpensesDraft expensesdraft){
        
        try{
            expensesdraftService.save(expensesdraft);

            return ResponseEntity.ok(expensesdraft);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Id '" + expensesdraft.getId() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}