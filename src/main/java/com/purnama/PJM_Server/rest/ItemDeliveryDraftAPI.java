/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.DeliveryDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemDeliveryDraft;
import com.purnama.PJM_Server.service.DeliveryDraftService;
import com.purnama.PJM_Server.service.ItemDeliveryDraftService;
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
@RequestMapping("/api/v1/itemdeliverydrafts")
public class ItemDeliveryDraftAPI {
    
    private final ItemDeliveryDraftService itemdeliverydraftService;
    
    private final DeliveryDraftService deliverydraftService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"deliverydraftid"})
    public ResponseEntity<?> getItemDeliveryDraftList(
            @RequestParam(value="deliverydraftid") int deliverydraftid) {
        
        DeliveryDraft deliverydraft = deliverydraftService.findById(deliverydraftid).get();
        
        List<ItemDeliveryDraft> ls = itemdeliverydraftService.findByDeliverydraft(deliverydraft);
        
        return ResponseEntity.ok(ls);
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemDeliveryDraftList(
            @RequestBody List<ItemDeliveryDraft> itemdeliverydraftlist) {
        
        itemdeliverydraftService.saveAll(itemdeliverydraftlist);
        
        return ResponseEntity.ok(itemdeliverydraftlist);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteItemDeliveryDraftList(
            @RequestBody List<ItemDeliveryDraft> itemdeliverydraftlist){
        
        for(ItemDeliveryDraft itemdeliverydraft : itemdeliverydraftlist){
            if(itemdeliverydraft.getId() != 0){
                itemdeliverydraftService.deleteById(itemdeliverydraft.getId());
            }
            else{
                
            }
        }
        
        return ResponseEntity.ok("");
    }
}
