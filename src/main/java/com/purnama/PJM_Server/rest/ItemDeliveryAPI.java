/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.Delivery;
import com.purnama.PJM_Server.model.transactional.ItemDelivery;
import com.purnama.PJM_Server.service.DeliveryService;
import com.purnama.PJM_Server.service.ItemDeliveryService;
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
@RequestMapping("/api/v1/itemdeliveries")
public class ItemDeliveryAPI {
    
    private final ItemDeliveryService itemdeliveryService;
    
    private final DeliveryService deliveryService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"deliveryid"})
    public ResponseEntity<?> getItemDeliveryList(
            @RequestParam(value="deliveryid") int deliveryid) {
        
        Delivery delivery = deliveryService.findById(deliveryid).get();
        
        List<ItemDelivery> ls = itemdeliveryService.findByDelivery(delivery);
        
        return ResponseEntity.ok(ls);
    }
    
}
