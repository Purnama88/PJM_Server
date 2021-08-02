/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.InvoiceSalesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemInvoiceSalesDraft;
import com.purnama.PJM_Server.service.InvoiceSalesDraftService;
import com.purnama.PJM_Server.service.ItemInvoiceSalesDraftService;
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
@RequestMapping("/api/v1/iteminvoicesalesdrafts")
public class ItemInvoiceSalesDraftAPI {
    
    private final ItemInvoiceSalesDraftService iteminvoicesalesdraftService;
    
    private final InvoiceSalesDraftService invoicesalesdraftService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"invoicedraftid"})
    public ResponseEntity<?> getItemInvoiceSalesDraftList(
            @RequestParam(value="invoicedraftid") int invoicedraftid) {
        
        InvoiceSalesDraft invoicesalesdraft = invoicesalesdraftService.findById(invoicedraftid).get();
        
        List<ItemInvoiceSalesDraft> ls = iteminvoicesalesdraftService.findByInvoicesalesdraft(invoicesalesdraft);
        
        return ResponseEntity.ok(ls);
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemInvoiceSalesDraftList(
            @RequestBody List<ItemInvoiceSalesDraft> iteminvoicesalesdraftlist) {
        
        iteminvoicesalesdraftService.saveAll(iteminvoicesalesdraftlist);
        
        return ResponseEntity.ok(iteminvoicesalesdraftlist);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteItemInvoiceSalesDraftList(
            @RequestBody List<ItemInvoiceSalesDraft> iteminvoicesalesdraftlist){
        
        for(ItemInvoiceSalesDraft iteminvoicesalesdraft : iteminvoicesalesdraftlist){
            if(iteminvoicesalesdraft.getId() != 0){
                iteminvoicesalesdraftService.deleteById(iteminvoicesalesdraft.getId());
            }
            else{
                
            }
        }
        
        return ResponseEntity.ok("");
    }
}
