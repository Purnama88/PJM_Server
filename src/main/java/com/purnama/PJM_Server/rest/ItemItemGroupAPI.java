/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.combine.ItemItemGroup;
import com.purnama.PJM_Server.service.ItemItemGroupService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/itemitemgroups")
public class ItemItemGroupAPI {
    
    private final ItemItemGroupService itemitemgroupService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemid"})
    public ResponseEntity<?> getItemItemGroupListByItem(
            @RequestParam(value="itemid") int itemid) {
        
        List<ItemItemGroup> ls = itemitemgroupService.findByItem(itemid);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemgroupid"})
    public ResponseEntity<?> getItemItemGroupListByItemGroup(
            @RequestParam(value="itemgroupid") int itemgroupid) {
        
        List<ItemItemGroup> ls = itemitemgroupService.findByItemGroup(itemgroupid);
        
        return ResponseEntity.ok(ls);
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemItemGroup(HttpServletRequest httpRequest,
            @RequestBody ItemItemGroup itemitemgroup){
        
        try{
            itemitemgroupService.save(itemitemgroup);

            return ResponseEntity.ok(itemitemgroup);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Item already exist in group");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json", params = {"id"})
    public ResponseEntity<?> deleteItemItemGroup(
            @RequestParam(value="id") int id){
        
        itemitemgroupService.deleteById(id);
        
        return ResponseEntity.ok("");
    }
}