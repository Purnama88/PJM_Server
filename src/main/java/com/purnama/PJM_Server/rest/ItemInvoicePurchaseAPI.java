/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.InvoicePurchase;
import com.purnama.PJM_Server.model.transactional.ItemInvoicePurchase;
import com.purnama.PJM_Server.service.InvoicePurchaseService;
import com.purnama.PJM_Server.service.ItemInvoicePurchaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/v1/iteminvoicepurchases")
public class ItemInvoicePurchaseAPI {
    
    private final ItemInvoicePurchaseService iteminvoicepurchaseService;
    
    private final InvoicePurchaseService invoicepurchaseService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"invoiceid"})
    public ResponseEntity<?> getItemInvoicePurchaseList(
            @RequestParam(value="invoiceid") int invoiceid) {
        
        InvoicePurchase invoicepurchase = invoicepurchaseService.findById(invoiceid).get();
        
        List<ItemInvoicePurchase> ls = iteminvoicepurchaseService.findByInvoicePurchase(invoicepurchase);
        
        return ResponseEntity.ok(ls);
    }
    
}