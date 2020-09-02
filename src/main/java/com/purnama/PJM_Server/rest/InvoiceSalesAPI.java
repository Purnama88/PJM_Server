/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import com.purnama.PJM_Server.service.InvoiceSalesService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class InvoiceSalesAPI {
    
    private final InvoiceSalesService invoicesalesService;
    
    @RequestMapping("/invoicesales/list")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(invoicesalesService.findAll());
    }
    
    @RequestMapping("/invoicesales/add")
    public ResponseEntity add(){
        InvoiceSales invoicesales = new InvoiceSales();
        invoicesales.setCurrency(null);
        invoicesales.setCurrencycode("");
        invoicesales.setCurrencyname("");
        invoicesales.setDiscount(0.0);
        invoicesales.setDraftid("");
        invoicesales.setDuedate(LocalDateTime.now());
        invoicesales.setLastmodified(LocalDateTime.now());
        invoicesales.setStatus(true);
        
        return ResponseEntity.ok(invoicesalesService.save(invoicesales));
    }
}
