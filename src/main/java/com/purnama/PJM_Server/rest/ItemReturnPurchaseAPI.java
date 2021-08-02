/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import com.purnama.PJM_Server.model.transactional.ItemReturnPurchase;
import com.purnama.PJM_Server.service.ReturnPurchaseService;
import com.purnama.PJM_Server.service.ItemReturnPurchaseService;
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
@RequestMapping("/api/v1/itemreturnpurchases")
public class ItemReturnPurchaseAPI {
    
    private final ItemReturnPurchaseService itemreturnpurchaseService;
    
    private final ReturnPurchaseService returnpurchaseService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"returnid"})
    public ResponseEntity<?> getItemReturnPurchaseList(
            @RequestParam(value="returnid") int returnid) {
        
        ReturnPurchase returnpurchase = returnpurchaseService.findById(returnid).get();
        
        List<ItemReturnPurchase> ls = itemreturnpurchaseService.findByReturnPurchase(returnpurchase);
        
        return ResponseEntity.ok(ls);
    }
    
}