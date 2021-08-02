/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemExpensesDraft;
import com.purnama.PJM_Server.service.ExpensesDraftService;
import com.purnama.PJM_Server.service.ItemExpensesDraftService;
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
@RequestMapping("/api/v1/itemexpensesdrafts")
public class ItemExpensesDraftAPI {
    
    private final ItemExpensesDraftService itemexpensesdraftService;
    
    private final ExpensesDraftService expensesdraftService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"expensesdraftid"})
    public ResponseEntity<?> getItemExpensesDraftList(
            @RequestParam(value="expensesdraftid") int expensesdraftid) {
        
        ExpensesDraft expensesdraft = expensesdraftService.findById(expensesdraftid).get();
        
        List<ItemExpensesDraft> ls = itemexpensesdraftService.findByExpensesdraft(expensesdraft);
        
        return ResponseEntity.ok(ls);
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemExpensesDraftList(
            @RequestBody List<ItemExpensesDraft> itemexpensesdraftlist) {
        
        itemexpensesdraftService.saveAll(itemexpensesdraftlist);

        return ResponseEntity.ok(itemexpensesdraftlist);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteItemExpensesDraftList(
            @RequestBody List<ItemExpensesDraft> itemexpensesdraftlist){
        
        for(ItemExpensesDraft itemexpensesdraft : itemexpensesdraftlist){
            if(itemexpensesdraft.getId() != 0){
                itemexpensesdraftService.deleteById(itemexpensesdraft.getId());
            }
            else{
                
            }
        }
        
        return ResponseEntity.ok("");
    }
}
