/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.pagination.WarehousePagination;
import com.purnama.PJM_Server.service.WarehouseService;
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
@RequestMapping("/api/v1/warehouses")
public class WarehouseAPI {
    
    private final WarehouseService warehouseService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getWarehouseList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Warehouse> ls = warehouseService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        WarehousePagination warehouse_pagination = new WarehousePagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(warehouse_pagination);
    }
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getWarehouseList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(warehouseService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(warehouseService.findByStatusTrue());
        }
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getWarehouse(@PathVariable("id") int id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveWarehouse(HttpServletRequest httpRequest,
            @RequestBody Warehouse warehouse){
        
        warehouse.setCode(warehouse.getCode().toUpperCase());
        warehouse.setCreateddate(LocalDateTime.now());
        warehouse.setLastmodified(LocalDateTime.now());
        
        try{
            warehouseService.save(warehouse);

            return ResponseEntity.ok(warehouse);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + warehouse.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateWarehouse(HttpServletRequest httpRequest,
            @RequestBody Warehouse warehouse){
        
        Warehouse temp = warehouseService.findById(warehouse.getId()).get();
        temp.setAddress(warehouse.getAddress());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(warehouse.getName());
        temp.setNote(warehouse.getNote());
        temp.setStatus(warehouse.isStatus());
        
        try{
            warehouseService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + warehouse.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
