/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.BuyPrice;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.service.BuyPriceService;
import com.purnama.PJM_Server.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/v1/buyprices")
public class BuyPriceAPI {
    
    private final ItemService itemService;
    
    private final BuyPriceService buypriceService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"itemid"})
    public ResponseEntity<?> getBuyPriceList(
            @RequestParam(value="itemid") int itemid) {
        
        Item item = itemService.findById(itemid).get();
        
        List<BuyPrice> ls = buypriceService.findByItem(item);
        
        return ResponseEntity.ok(ls);
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json", params = {"itemid"})
    public ResponseEntity<?> deleteBuyPriceList(
            @RequestParam(value="itemid") int itemid) {
        
        Item item = itemService.findById(itemid).get();
        
        List<BuyPrice> ls = buypriceService.findByItem(item);
        for(BuyPrice sellprice : ls){
            buypriceService.deleteById(sellprice.getId());
        }
        
        return ResponseEntity.ok(ls);
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveBuyPriceList(
            @RequestBody List<BuyPrice> buypricelist) {
        
        BuyPrice oldbuyprice = buypricelist.get(0);
        
        Item item = oldbuyprice.getItem();
        
        List<BuyPrice> oldls = buypriceService.findByItem(item);
        for(BuyPrice buyprice : oldls){
            buypriceService.deleteById(buyprice.getId());
        }
        
        for(BuyPrice buyprice : buypricelist){
            buypriceService.save(buyprice);
        }
        
        return ResponseEntity.ok(buypricelist);
    }
}
