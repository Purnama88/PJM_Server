/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Model;
import com.purnama.PJM_Server.model.pagination.ModelPagination;
import com.purnama.PJM_Server.service.ModelService;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/models")
public class ModelAPI {
    
    private final ModelService modelService;
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getModelList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(modelService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(modelService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getModelList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Model> ls = modelService.findByNameContaining(keyword, page, itemperpage);
        
        ModelPagination model_pagination = new ModelPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(model_pagination);
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getModel(@PathVariable("id") int id) {
        return ResponseEntity.ok(modelService.findById(id));
    }
    
    @PostMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> saveModel(HttpServletRequest httpRequest,
            @RequestBody Model model){
        
        model.setCode(model.getCode().toUpperCase());
        
        model.setLastmodified(LocalDateTime.now());
        
        try{
            modelService.save(model);

            return ResponseEntity.ok(model);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + model.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> updateModel(HttpServletRequest httpRequest,
            @RequestBody Model model){
        
        Model temp = modelService.findById(model.getId()).get();
        temp.setDescription(model.getDescription());
        temp.setBrand(model.getBrand());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(model.getName());
        temp.setNote(model.getNote());
        temp.setStatus(model.isStatus());
        
        try{
            modelService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + model.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}