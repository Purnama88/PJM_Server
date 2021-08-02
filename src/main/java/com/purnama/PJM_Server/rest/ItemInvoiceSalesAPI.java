/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import com.purnama.PJM_Server.model.transactional.ItemInvoiceSales;
import com.purnama.PJM_Server.service.InvoiceSalesService;
import com.purnama.PJM_Server.service.ItemInvoiceSalesService;
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
@RequestMapping("/api/v1/iteminvoicesales")
public class ItemInvoiceSalesAPI {
    
    private final ItemInvoiceSalesService iteminvoicesalesService;
    
    private final InvoiceSalesService invoicesalesService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"invoiceid"})
    public ResponseEntity<?> getItemInvoiceSalesList(
            @RequestParam(value="invoiceid") int invoiceid) {
        
        InvoiceSales invoicesales = invoicesalesService.findById(invoiceid).get();
        
        List<ItemInvoiceSales> ls = iteminvoicesalesService.findByInvoiceSales(invoicesales);
        
        return ResponseEntity.ok(ls);
    }
    
}