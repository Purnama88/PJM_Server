/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Label;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.service.LabelService;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
@RequestMapping("/api/v1/labels")
public class LabelAPI {
    
    private final LabelService labelService;
    
    @GetMapping(value="", headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getLabelList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(labelService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(labelService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getLabelList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Label> ls = labelService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        Pagination<Label> labelpagination = new Pagination<>(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(labelpagination);
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getLabel(@PathVariable("id") int id) {
        return ResponseEntity.ok(labelService.findById(id));
    }
    
    @PostMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> saveLabel(HttpServletRequest httpRequest,
            @RequestBody Label label){
        
        label.setCode(label.getCode().toUpperCase());
        label.setCreateddate(LocalDateTime.now());
        label.setLastmodified(LocalDateTime.now(ZoneId.systemDefault()));
        
        try{
            labelService.save(label);

            return ResponseEntity.ok(label);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + label.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> updateLabel(HttpServletRequest httpRequest,
            @RequestBody Label label){
        
        Label temp = labelService.findById(label.getId()).get();
        temp.setDescription(label.getDescription());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(label.getName());
        temp.setNote(label.getNote());
        temp.setStatus(label.isStatus());
        
        try{
            labelService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + label.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    
}
