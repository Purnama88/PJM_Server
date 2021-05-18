/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import com.purnama.PJM_Server.service.ReturnPurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1/returnpurchases")
public class ReturnPurchaseAPI {
    
    private final ReturnPurchaseService returnpurchaseService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getReturnPurchaseList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ReturnPurchase> ls = returnpurchaseService.findByNumberContaining(keyword, page, itemperpage);

        Pagination<ReturnPurchase> returnpurchasepagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(returnpurchasepagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getReturnPurchase(@PathVariable("id") int id) {
        return ResponseEntity.ok(returnpurchaseService.findById(id));
    }
}
