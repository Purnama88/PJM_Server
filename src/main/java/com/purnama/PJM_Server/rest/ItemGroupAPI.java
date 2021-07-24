/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.service.ItemGroupService;
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
@RequestMapping("/api/v1/itemgroups")
public class ItemGroupAPI {
    
    private final ItemGroupService itemgroupService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getItemGroupList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ItemGroup> ls = itemgroupService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        Pagination<ItemGroup> itemgrouppagination = new Pagination<>(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(itemgrouppagination);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"keyword"})
    public ResponseEntity<?> getItemGroupList(
            @RequestParam(value="keyword") String keyword) {
        
        List<ItemGroup> ls = itemgroupService.findByCodeContainingOrNameContaining(keyword, keyword);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getItemGroupList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(itemgroupService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(itemgroupService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getItemGroup(@PathVariable("id") int id) {
        return ResponseEntity.ok(itemgroupService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveItemGroup(HttpServletRequest httpRequest,
            @RequestBody ItemGroup itemgroup){
        
        itemgroup.setCode(itemgroup.getCode().toUpperCase());
        itemgroup.setCreateddate(LocalDateTime.now());
        itemgroup.setLastmodified(LocalDateTime.now());
        
        try{
            itemgroupService.save(itemgroup);

            return ResponseEntity.ok(itemgroup);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + itemgroup.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateItemGroup(HttpServletRequest httpRequest,
            @RequestBody ItemGroup itemgroup){
        
        ItemGroup temp = itemgroupService.findById(itemgroup.getId()).get();
        temp.setDescription(itemgroup.getDescription());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(itemgroup.getName());
        temp.setNote(itemgroup.getNote());
        temp.setStatus(itemgroup.isStatus());
        
        try{
            itemgroupService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + itemgroup.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
}
