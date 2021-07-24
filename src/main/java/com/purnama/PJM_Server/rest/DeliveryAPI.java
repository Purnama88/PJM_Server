/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.Delivery;
import com.purnama.PJM_Server.model.transactional.ItemDelivery;
import com.purnama.PJM_Server.model.transactional.draft.DeliveryDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemDeliveryDraft;
import com.purnama.PJM_Server.service.DeliveryDraftService;
import com.purnama.PJM_Server.service.DeliveryService;
import com.purnama.PJM_Server.service.ItemDeliveryDraftService;
import com.purnama.PJM_Server.service.ItemDeliveryService;
import com.purnama.PJM_Server.service.NumberingService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/v1/deliveries")
public class DeliveryAPI {
    
    private final DeliveryService deliveryService;
    private final DeliveryDraftService deliverydraftService;
    private final ItemDeliveryService itemdeliveryService;
    private final ItemDeliveryDraftService itemdeliverydraftService;
    private final NumberingService numberingService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getDeliveryList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Delivery> ls = deliveryService.findByNumberContaining(keyword, page, itemperpage);

        Pagination<Delivery> deliverypagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(deliverypagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getDelivery(@PathVariable("id") int id) {
        return ResponseEntity.ok(deliveryService.findById(id));
    }
    
    @PostMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> postDelivery(@PathVariable("id") int id) {
        DeliveryDraft deliverydraft = deliverydraftService.findById(id).get();
        
        List<ItemDeliveryDraft> itemdeliverydrafts = itemdeliverydraftService.findByDeliverydraft(deliverydraft);
        
        Numbering numbering = deliverydraft.getNumbering();
        
        if(numbering == null){
            return ResponseEntity.badRequest().body("Numbering is not valid");
        }
        else if(deliverydraft.getDestination().isEmpty()){
            return ResponseEntity.badRequest().body("Destination is not valid");
        }
        else if(itemdeliverydrafts.isEmpty()){
            return ResponseEntity.badRequest().body("Invoice is empty");
        }
        
//        if(!deliverydraft.getLastmodifiedby().isDateforward()){
//            if(deliverydraft.getDate().getTime().getDate() > new Date().getDate()){
//                return ResponseEntity.badRequest().body("You are not allowed to change date forward");
//            }
//        }
//        
//        if(!deliverydraft.getLastmodifiedby().isDatebackward()){
//            if(deliverydraft.getDate().getTime().getDate() < new Date().getDate()){
//                return ResponseEntity.badRequest().body("You are not allowed to change date backward");
//            }
//        }
        
        Delivery delivery = new Delivery();
        delivery.setCreateddate(LocalDateTime.now());
        delivery.setDestination(deliverydraft.getDestination());
        delivery.setDraftid(deliverydraft.getDraftid());
        delivery.setInvoicedate(deliverydraft.getInvoicedate());
        delivery.setLastmodified(LocalDateTime.now());
        delivery.setNote(deliverydraft.getNote());
        delivery.setNumber(numbering.toString());
        delivery.setPrinted(0);
        delivery.setStatus(true);
        delivery.setUser(deliverydraft.getUser());
        delivery.setVehiclecode(deliverydraft.getVehiclecode());
        delivery.setVehicletype(deliverydraft.getVehicletype());
        delivery.setWarehouse(deliverydraft.getWarehouse());
        delivery.setWarehousecode(deliverydraft.getWarehouse().getCode());
        
        deliveryService.save(delivery);
        
        List<ItemDelivery> itemdeliveries = new ArrayList<>();
        for(ItemDeliveryDraft itemdeliverydraft : itemdeliverydrafts){
            ItemDelivery itemdelivery = new ItemDelivery();
            
            itemdelivery.setDelivery(delivery);
            itemdelivery.setDescription(itemdeliverydraft.getDescription());
            itemdelivery.setQuantity(itemdeliverydraft.getQuantity());
            itemdelivery.setRemark(itemdeliverydraft.getRemark());
            
            itemdeliveries.add(itemdelivery);
        }
        
        itemdeliveryService.saveAll(itemdeliveries);
        
        itemdeliverydraftService.deleteAll(itemdeliverydrafts);
        
        deliverydraftService.deleteById(id);
        
        numbering.setCurrent(numbering.getCurrent() + 1);
        
        if(numbering.getCurrent() != numbering.getEnd()){
            
        }
        else{
            numbering.setStatus(false);
        }
        
        numberingService.save(numbering);
        
        return ResponseEntity.ok(delivery.getId());
    }
}
