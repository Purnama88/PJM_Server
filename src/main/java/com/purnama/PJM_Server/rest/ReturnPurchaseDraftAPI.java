/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.model.pagination.ReturnPurchaseDraftPagination;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.CurrencyService;
import com.purnama.PJM_Server.service.ReturnPurchaseDraftService;
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
@RequestMapping("/api/v1/returnpurchasedrafts")
public class ReturnPurchaseDraftAPI {
    
    private final ReturnPurchaseDraftService returnpurchasedraftService;
    
    private final CurrencyService currencyService;
    
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getReturnPurchaseDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ReturnPurchaseDraft> ls = returnpurchasedraftService.findByDraftidContaining(keyword, page, itemperpage);

        ReturnPurchaseDraftPagination returnpurchasedraft_pagination = new ReturnPurchaseDraftPagination(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(returnpurchasedraft_pagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getReturnPurchaseDraft(@PathVariable("id") int id) {
        return ResponseEntity.ok(returnpurchasedraftService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveReturnPurchaseDraft(HttpServletRequest httpRequest) {
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        User user = new User();
        user.setId(JwtUtil.parseToken(header.substring(7)));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(JwtUtil.parseToken2(header.substring(7)));
        
        Currency currency = currencyService.findByDefaultcurrencyTrue().get();
        
        ReturnPurchaseDraft returnpurchasedraft = new ReturnPurchaseDraft();
        returnpurchasedraft.setDraftid(IdGenerator.generateId());
        returnpurchasedraft.setTransactiondate(LocalDateTime.now());
        returnpurchasedraft.setInvoicedate(LocalDateTime.now());
        returnpurchasedraft.setWarehouse(warehouse);
        returnpurchasedraft.setNote("");
        returnpurchasedraft.setSubtotal(0);
        returnpurchasedraft.setDiscount(0);
        returnpurchasedraft.setRounding(0);
        returnpurchasedraft.setFreight(0);
        returnpurchasedraft.setTax(0);
        returnpurchasedraft.setUser(user);
        returnpurchasedraft.setLastmodified(LocalDateTime.now());
        returnpurchasedraft.setCurrency(currency);
        
        returnpurchasedraftService.save(returnpurchasedraft);
        
        return ResponseEntity.ok(returnpurchasedraft.getId());
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateReturnPurchaseDraft(HttpServletRequest httpRequest,
            @RequestBody ReturnPurchaseDraft returnpurchasedraft){
        
        try{
            returnpurchasedraftService.save(returnpurchasedraft);

            return ResponseEntity.ok(returnpurchasedraft);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Id '" + returnpurchasedraft.getId() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}