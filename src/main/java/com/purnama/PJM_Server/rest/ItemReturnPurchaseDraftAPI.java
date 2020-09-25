/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnPurchaseDraft;
import com.purnama.PJM_Server.service.ReturnPurchaseDraftService;
import com.purnama.PJM_Server.service.ItemReturnPurchaseDraftService;
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
@RequestMapping("/api/v1/itemreturnpurchasedrafts")
public class ItemReturnPurchaseDraftAPI {
    
    private final ItemReturnPurchaseDraftService itemreturnpurchasedraftService;
    
    private final ReturnPurchaseDraftService returnpurchasedraftService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"returnid"})
    public ResponseEntity<?> getItemReturnPurchaseDraftList(
            @RequestParam(value="returnid") int returnid) {
        
        ReturnPurchaseDraft returnpurchasedraft = returnpurchasedraftService.findById(returnid).get();
        
        List<ItemReturnPurchaseDraft> ls = itemreturnpurchasedraftService.findByReturnpurchasedraft(returnpurchasedraft);
        
        return ResponseEntity.ok(ls);
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemReturnPurchaseDraftList(
            @RequestBody List<ItemReturnPurchaseDraft> itemreturnpurchasedraftlist) {
        
        for(ItemReturnPurchaseDraft itemreturnpurchasedraft : itemreturnpurchasedraftlist){
            itemreturnpurchasedraftService.save(itemreturnpurchasedraft);
        }
        
        return ResponseEntity.ok(itemreturnpurchasedraftlist);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteItemReturnPurchaseDraftList(
            @RequestBody List<ItemReturnPurchaseDraft> itemreturnpurchasedraftlist){
        
        for(ItemReturnPurchaseDraft itemreturnpurchasedraft : itemreturnpurchasedraftlist){
            if(itemreturnpurchasedraft.getId() != 0){
                itemreturnpurchasedraftService.deleteById(itemreturnpurchasedraft.getId());
            }
            else{
                
            }
        }
        
        return ResponseEntity.ok("");
    }
}
