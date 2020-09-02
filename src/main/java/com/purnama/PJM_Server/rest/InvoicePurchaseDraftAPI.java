/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import com.purnama.PJM_Server.model.pagination.InvoicePurchaseDraftPagination;
import com.purnama.PJM_Server.service.InvoicePurchaseDraftService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class InvoicePurchaseDraftAPI {
    
    private final InvoicePurchaseDraftService invoicepurchasedraftService;
    
    
    @RequestMapping(value = "/api/invoicepurchasedraft/list", method = RequestMethod.GET, 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getInvoicePurchaseDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<InvoicePurchaseDraft> ls = invoicepurchasedraftService.findByDraftidContaining(keyword, page, itemperpage);
        
        InvoicePurchaseDraftPagination invoicepurchasedraft_pagination = new InvoicePurchaseDraftPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(invoicepurchasedraft_pagination);
    }
    
//    @RequestMapping(value = "/api/invoicepurchasedraft/save", method = RequestMethod.POST,
//            headers = "Accept=application/json")
//    public ResponseEntity<?> saveInvoicePurchaseDraft(HttpServletRequest httpRequest,
//            @RequestBody InvoicePurchaseDraft invoicepurchasedraft){
//        
//        invoicepurchasedraft.setCode(invoicepurchasedraft.getCode().toUpperCase());
//        
//        invoicepurchasedraft.setLastmodified(LocalDateTime.now());
//        
//        try{
//            invoicepurchasedraftService.save(invoicepurchasedraft);
//
//            return ResponseEntity.ok(invoicepurchasedraft);
//        }
//        catch(DataIntegrityViolationException e){
//            return ResponseEntity.badRequest().body("Code '" + invoicepurchasedraft.getCode() +"' already exist");
//        }
//        catch(Exception e){
//            return ResponseEntity.badRequest().body(e);
//        }
//    }
    
    @RequestMapping(value = "/api/invoicepurchasedraft/get", method = RequestMethod.GET,
            headers = "Accept=application/json", params = {"id"})
    public ResponseEntity<?> getInvoicePurchaseDraft(@RequestParam(value="id") int id) {
        return ResponseEntity.ok(invoicepurchasedraftService.findById(id));
    }
}