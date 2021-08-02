/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnSalesDraft;
import com.purnama.PJM_Server.service.ReturnSalesDraftService;
import com.purnama.PJM_Server.service.ItemReturnSalesDraftService;
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
@RequestMapping("/api/v1/itemreturnsalesdrafts")
public class ItemReturnSalesDraftAPI {
    
    private final ItemReturnSalesDraftService itemreturnsalesdraftService;
    
    private final ReturnSalesDraftService returnsalesdraftService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"returndraftid"})
    public ResponseEntity<?> getItemReturnSalesDraftList(
            @RequestParam(value="returndraftid") int returndraftid) {
        
        ReturnSalesDraft returnsalesdraft = returnsalesdraftService.findById(returndraftid).get();
        
        List<ItemReturnSalesDraft> ls = itemreturnsalesdraftService.findByReturnsalesdraft(returnsalesdraft);
        
        return ResponseEntity.ok(ls);
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemReturnSalesDraftList(
            @RequestBody List<ItemReturnSalesDraft> itemreturnsalesdraftlist) {
        
        itemreturnsalesdraftService.saveAll(itemreturnsalesdraftlist);
        
        return ResponseEntity.ok(itemreturnsalesdraftlist);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteItemReturnSalesDraftList(
            @RequestBody List<ItemReturnSalesDraft> itemreturnsalesdraftlist){
        
        for(ItemReturnSalesDraft itemreturnsalesdraft : itemreturnsalesdraftlist){
            if(itemreturnsalesdraft.getId() != 0){
                itemreturnsalesdraftService.deleteById(itemreturnsalesdraft.getId());
            }
            else{
                
            }
        }
        
        return ResponseEntity.ok("");
    }
}
