/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.model.pagination.ItemPagination;
import com.purnama.PJM_Server.service.ItemService;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/items")
public class ItemAPI {
    
    private final ItemService itemService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"labelid", "keyword"})
    public ResponseEntity<?> getItemList(
            @RequestParam(value="labelid") int labelid,
            @RequestParam(value="keyword") String keyword) {
        
        List<Item> ls = itemService.findByCodeNameLabelWithPredicate(keyword, labelid);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getItemList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Item> ls = itemService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        ItemPagination item_pagination = new ItemPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(item_pagination);
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getItem(@PathVariable("id") int id) {
        return ResponseEntity.ok(itemService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItem(HttpServletRequest httpRequest,
            @RequestBody Item item){
        
        item.setCode(item.getCode().toUpperCase());
        item.setCreateddate(LocalDateTime.now());
        item.setLastmodified(LocalDateTime.now());
        
        try{
            itemService.save(item);

            return ResponseEntity.ok(item);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + item.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateItem(HttpServletRequest httpRequest,
            @RequestBody Item item){
        
        Item temp = itemService.findById(item.getId()).get();
        
        temp.setName(item.getName());
        temp.setBulkbuyprice(item.isBulkbuyprice());
        temp.setBulksellprice(item.isBulksellprice());
        temp.setBuyprice(item.getBuyprice());
        temp.setSellprice(item.getSellprice());
        temp.setDescription(item.getDescription());
        temp.setCost(item.getCost());
        temp.setLabel(item.getLabel());
        temp.setStatus(item.isStatus());
        temp.setNote(item.getNote());
        
        try{
            itemService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e);
        }
        
    }
    
}
