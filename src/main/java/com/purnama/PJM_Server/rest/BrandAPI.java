/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Brand;
import com.purnama.PJM_Server.model.pagination.BrandPagination;
import com.purnama.PJM_Server.service.BrandService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/brands")
public class BrandAPI {
    
    private final BrandService brandService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getBrandList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Brand> ls = brandService.findByNameContaining(keyword, page, itemperpage);
        
        BrandPagination brand_pagination = new BrandPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(brand_pagination);
    }
    
    @GetMapping(value="", 
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getBrandList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(brandService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(brandService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getBrand(@PathVariable("id") int id) {
        return ResponseEntity.ok(brandService.findById(id));
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateBrand(HttpServletRequest httpRequest,
            @RequestBody Brand brand){
        
        Brand temp = brandService.findById(brand.getId()).get();
        temp.setDescription(brand.getDescription());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(brand.getName());
        temp.setNote(brand.getNote());
        temp.setStatus(brand.isStatus());
        
        try{
            brandService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + brand.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveBrand(HttpServletRequest httpRequest,
            @RequestBody Brand brand){
        
        brand.setCode(brand.getCode().toUpperCase());
        brand.setCreateddate(LocalDateTime.now());
        brand.setLastmodified(LocalDateTime.now());
        
        try{
            brandService.save(brand);

            return ResponseEntity.ok(brand);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + brand.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    
}