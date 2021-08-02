/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.ReturnSales;
import com.purnama.PJM_Server.model.transactional.ItemReturnSales;
import com.purnama.PJM_Server.service.ReturnSalesService;
import com.purnama.PJM_Server.service.ItemReturnSalesService;
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
@RequestMapping("/api/v1/itemreturnsales")
public class ItemReturnSalesAPI {
    
    private final ItemReturnSalesService itemreturnsalesService;
    
    private final ReturnSalesService returnsalesService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"returnid"})
    public ResponseEntity<?> getItemReturnSalesList(
            @RequestParam(value="returnid") int returnid) {
        
        ReturnSales returnsales = returnsalesService.findById(returnid).get();
        
        List<ItemReturnSales> ls = itemreturnsalesService.findByReturnSales(returnsales);
        
        return ResponseEntity.ok(ls);
    }
    
}