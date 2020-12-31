/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.SellPrice;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.service.SellPriceService;
import com.purnama.PJM_Server.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1/sellprices")
public class SellPriceAPI {
    private final ItemService itemService;
    
    private final SellPriceService sellpriceService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"itemid"})
    public ResponseEntity<?> getSellPriceList(
            @RequestParam(value="itemid") int itemid) {
        
        Item item = itemService.findById(itemid).get();
        
        List<SellPrice> ls = sellpriceService.findByItem(item);
        
        return ResponseEntity.ok(ls);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json", params = {"itemid"})
    public ResponseEntity<?> deleteSellPriceList(
            @RequestParam(value="itemid") int itemid) {
        
        Item item = itemService.findById(itemid).get();
        
        List<SellPrice> ls = sellpriceService.findByItem(item);
        for(SellPrice sellprice : ls){
            sellpriceService.deleteById(sellprice.getId());
        }
        
        return ResponseEntity.ok(ls);
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveSellPriceList(
            @RequestBody List<SellPrice> sellpricelist) {
        
        SellPrice oldsellprice = sellpricelist.get(0);
        
        Item item = oldsellprice.getItem();
        
        List<SellPrice> oldls = sellpriceService.findByItem(item);
        for(SellPrice sellprice : oldls){
            sellpriceService.deleteById(sellprice.getId());
        }
        
        for(SellPrice sellprice : sellpricelist){
            sellpriceService.save(sellprice);
        }
        
        return ResponseEntity.ok(sellpricelist);
    }
}
