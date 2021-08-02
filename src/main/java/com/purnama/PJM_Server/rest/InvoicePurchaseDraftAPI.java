/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.CurrencyService;
import com.purnama.PJM_Server.service.InvoicePurchaseDraftService;
import com.purnama.PJM_Server.service.ItemInvoicePurchaseDraftService;
import com.purnama.PJM_Server.util.IdGenerator;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/invoicepurchasedrafts")
public class InvoicePurchaseDraftAPI {
    
    private final InvoicePurchaseDraftService invoicepurchasedraftService;
    
    private final ItemInvoicePurchaseDraftService iteminvoicepurchasedraftService;
    
    private final CurrencyService currencyService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getInvoicePurchaseDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<InvoicePurchaseDraft> ls = invoicepurchasedraftService.findByDraftidContaining(keyword, page, itemperpage);

        Pagination<InvoicePurchaseDraft> invoicepurchasedraftpagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(invoicepurchasedraftpagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getInvoicePurchaseDraft(@PathVariable("id") int id) {
        return ResponseEntity.ok(invoicepurchasedraftService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveInvoicePurchaseDraft(HttpServletRequest httpRequest) {
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        User user = new User();
        user.setId(JwtUtil.parseToken(header.substring(7)));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(JwtUtil.parseToken2(header.substring(7)));
        
        Currency currency = currencyService.findByDefaultcurrencyTrue().get();
        
        InvoicePurchaseDraft invoicepurchasedraft = new InvoicePurchaseDraft();
        invoicepurchasedraft.setDraftid(IdGenerator.generateId());
        invoicepurchasedraft.setCreateddate(LocalDateTime.now());
        invoicepurchasedraft.setInvoicedate(LocalDateTime.now());
        invoicepurchasedraft.setDuedate(LocalDateTime.now());
        invoicepurchasedraft.setWarehouse(warehouse);
        invoicepurchasedraft.setNote("");
        invoicepurchasedraft.setSubtotal(0);
        invoicepurchasedraft.setDiscount(0);
        invoicepurchasedraft.setRounding(0);
        invoicepurchasedraft.setFreight(0);
        invoicepurchasedraft.setTax(0);
        invoicepurchasedraft.setUser(user);
        invoicepurchasedraft.setLastmodified(LocalDateTime.now());
        invoicepurchasedraft.setCurrency(currency);
        invoicepurchasedraft.setRate(1);
        
        invoicepurchasedraftService.save(invoicepurchasedraft);
        
        return ResponseEntity.ok(invoicepurchasedraft.getId());
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateInvoicePurchaseDraft(HttpServletRequest httpRequest,
            @RequestBody InvoicePurchaseDraft invoicepurchasedraft){
        
        try{
            invoicepurchasedraft.setLastmodified(LocalDateTime.now());
            invoicepurchasedraftService.save(invoicepurchasedraft);

            return ResponseEntity.ok(invoicepurchasedraft);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Id '" + invoicepurchasedraft.getId() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @DeleteMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteInvoicePurchaseDraft(@PathVariable("id") int id){
        InvoicePurchaseDraft invoicepurchasedraft = invoicepurchasedraftService.findById(id).get();
        
        iteminvoicepurchasedraftService.deleteByInvoicepurchasedraft(invoicepurchasedraft);
        
        invoicepurchasedraftService.deleteById(invoicepurchasedraft.getId());
        
        return ResponseEntity.ok("");
    }
}