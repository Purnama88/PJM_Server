/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.draft.DeliveryDraft;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.DeliveryDraftService;
import com.purnama.PJM_Server.service.ItemDeliveryDraftService;
import com.purnama.PJM_Server.util.IdGenerator;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/deliverydrafts")
public class DeliveryDraftAPI {
    
    private final DeliveryDraftService deliverydraftService;
    private final ItemDeliveryDraftService itemdeliverydraftService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getDeliveryDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<DeliveryDraft> ls = deliverydraftService.findByDraftidContaining(keyword, page, itemperpage);

        Pagination<DeliveryDraft> deliverydraftpagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(deliverydraftpagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getDeliveryDraft(@PathVariable("id") int id) {
        return ResponseEntity.ok(deliverydraftService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveDeliveryDraft(HttpServletRequest httpRequest) {
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        User user = new User();
        user.setId(JwtUtil.parseToken(header.substring(7)));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(JwtUtil.parseToken2(header.substring(7)));
        
        DeliveryDraft deliverydraft = new DeliveryDraft();
        deliverydraft.setDraftid(IdGenerator.generateId());
        deliverydraft.setDestination("");
        deliverydraft.setVehiclecode("");
        deliverydraft.setVehicletype("");
        deliverydraft.setInvoicedate(LocalDateTime.now());
        deliverydraft.setWarehouse(warehouse);
        deliverydraft.setNote("");
        deliverydraft.setUser(user);
        deliverydraft.setCreateddate(LocalDateTime.now());
        deliverydraft.setLastmodified(LocalDateTime.now());
        
        deliverydraftService.save(deliverydraft);
        
        return ResponseEntity.ok(deliverydraft.getId());
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateDeliveryDraft(HttpServletRequest httpRequest,
            @RequestBody DeliveryDraft deliverydraft){
        
        try{
            deliverydraftService.save(deliverydraft);

            return ResponseEntity.ok(deliverydraft);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Id '" + deliverydraft.getId() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @DeleteMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteDeliveryDraft(@PathVariable("id") int id){
        DeliveryDraft deliverydraft = deliverydraftService.findById(id).get();
        
        itemdeliverydraftService.deleteByDeliverydraft(deliverydraft);
        
        deliverydraftService.deleteById(deliverydraft.getId());
        
        return ResponseEntity.ok("");
    }
}