/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.combine.ItemModel;
import com.purnama.PJM_Server.service.ItemModelService;
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
@RequestMapping("/api/v1/itemmodels")
public class ItemModelAPI {
    
    private final ItemModelService itemmodelService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemid"})
    public ResponseEntity<?> getItemModelListByItem(
            @RequestParam(value="itemid") int itemid) {
        
        List<ItemModel> ls = itemmodelService.findByItem(itemid);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"modelid"})
    public ResponseEntity<?> getItemModelListByModel(
            @RequestParam(value="modelid") int modelid) {
        
        List<ItemModel> ls = itemmodelService.findByModel(modelid);
        
        return ResponseEntity.ok(ls);
    }
}
