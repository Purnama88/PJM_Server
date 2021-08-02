/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemInvoicePurchaseDraft;
import com.purnama.PJM_Server.service.InvoicePurchaseDraftService;
import com.purnama.PJM_Server.service.ItemInvoicePurchaseDraftService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/v1/iteminvoicepurchasedrafts")
public class ItemInvoicePurchaseDraftAPI {
    
    private final ItemInvoicePurchaseDraftService iteminvoicepurchasedraftService;
    
    private final InvoicePurchaseDraftService invoicepurchasedraftService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"invoicedraftid"})
    public ResponseEntity<?> getItemInvoicePurchaseDraftList(
            @RequestParam(value="invoicedraftid") int invoicedraftid) {
        
        InvoicePurchaseDraft invoicepurchasedraft = invoicepurchasedraftService.findById(invoicedraftid).get();
        
        List<ItemInvoicePurchaseDraft> ls = iteminvoicepurchasedraftService.findByInvoicepurchasedraft(invoicepurchasedraft);
        
        return ResponseEntity.ok(ls);
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemInvoicePurchaseDraftList(
            @RequestBody List<ItemInvoicePurchaseDraft> iteminvoicepurchasedraftlist) {
        
        iteminvoicepurchasedraftService.saveAll(iteminvoicepurchasedraftlist);
        
        return ResponseEntity.ok(iteminvoicepurchasedraftlist);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteItemInvoicePurchaseDraftList(
            @RequestBody List<ItemInvoicePurchaseDraft> iteminvoicepurchasedraftlist){
        
        for(ItemInvoicePurchaseDraft iteminvoicepurchasedraft : iteminvoicepurchasedraftlist){
            if(iteminvoicepurchasedraft.getId() != 0){
                iteminvoicepurchasedraftService.deleteById(iteminvoicepurchasedraft.getId());
            }
            else{
                
            }
        }
        
        return ResponseEntity.ok("");
    }
}
