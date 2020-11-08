/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.transactional.draft.InvoiceSalesDraft;
import com.purnama.PJM_Server.model.pagination.InvoiceSalesDraftPagination;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.CurrencyService;
import com.purnama.PJM_Server.service.InvoiceSalesDraftService;
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
@RequestMapping("/api/v1/invoicesalesdrafts")
public class InvoiceSalesDraftAPI {
    
    private final InvoiceSalesDraftService invoicesalesdraftService;
    
    private final CurrencyService currencyService;
    
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getInvoiceSalesDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<InvoiceSalesDraft> ls = invoicesalesdraftService.findByDraftidContaining(keyword, page, itemperpage);

        InvoiceSalesDraftPagination invoicesalesdraft_pagination = new InvoiceSalesDraftPagination(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(invoicesalesdraft_pagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getInvoiceSalesDraft(@PathVariable("id") int id) {
        return ResponseEntity.ok(invoicesalesdraftService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveInvoiceSalesDraft(HttpServletRequest httpRequest) {
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        User user = new User();
        user.setId(JwtUtil.parseToken(header.substring(7)));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(JwtUtil.parseToken2(header.substring(7)));
        
        Currency currency = currencyService.findByDefaultcurrencyTrue().get();
        
        InvoiceSalesDraft invoicesalesdraft = new InvoiceSalesDraft();
        invoicesalesdraft.setDraftid(IdGenerator.generateId());
        invoicesalesdraft.setCreateddate(LocalDateTime.now());
        invoicesalesdraft.setInvoicedate(LocalDateTime.now());
        invoicesalesdraft.setWarehouse(warehouse);
        invoicesalesdraft.setNote("");
        invoicesalesdraft.setSubtotal(0);
        invoicesalesdraft.setDiscount(0);
        invoicesalesdraft.setRounding(0);
        invoicesalesdraft.setFreight(0);
        invoicesalesdraft.setTax(0);
        invoicesalesdraft.setUser(user);
        invoicesalesdraft.setLastmodified(LocalDateTime.now());
        invoicesalesdraft.setCurrency(currency);
        
        invoicesalesdraftService.save(invoicesalesdraft);
        
        return ResponseEntity.ok(invoicesalesdraft.getId());
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateInvoiceSalesDraft(HttpServletRequest httpRequest,
            @RequestBody InvoiceSalesDraft invoicesalesdraft){
        
        try{
            invoicesalesdraftService.save(invoicesalesdraft);

            return ResponseEntity.ok(invoicesalesdraft);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Id '" + invoicesalesdraft.getId() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}