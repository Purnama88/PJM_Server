/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.model.pagination.ReturnPurchaseDraftPagination;
import com.purnama.PJM_Server.service.ReturnPurchaseDraftService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
public class ReturnPurchaseDraftAPI {
    
    private final ReturnPurchaseDraftService returnpurchasedraftService;
    
    
    @RequestMapping(value = "/api/returnpurchasedraft/list", method = RequestMethod.GET, 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getReturnPurchaseDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ReturnPurchaseDraft> ls = returnpurchasedraftService.findByDraftidContaining(keyword, page, itemperpage);
        
        ReturnPurchaseDraftPagination returnpurchasedraft_pagination = new ReturnPurchaseDraftPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(returnpurchasedraft_pagination);
    }
    
//    @RequestMapping(value = "/api/returnpurchasedraft/save", method = RequestMethod.POST,
//            headers = "Accept=application/json")
//    public ResponseEntity<?> saveReturnPurchaseDraft(HttpServletRequest httpRequest,
//            @RequestBody ReturnPurchaseDraft returnpurchasedraft){
//        
//        returnpurchasedraft.setCode(returnpurchasedraft.getCode().toUpperCase());
//        
//        returnpurchasedraft.setLastmodified(LocalDateTime.now());
//        
//        try{
//            returnpurchasedraftService.save(returnpurchasedraft);
//
//            return ResponseEntity.ok(returnpurchasedraft);
//        }
//        catch(DataIntegrityViolationException e){
//            return ResponseEntity.badRequest().body("Code '" + returnpurchasedraft.getCode() +"' already exist");
//        }
//        catch(Exception e){
//            return ResponseEntity.badRequest().body(e);
//        }
//    }
    
    @RequestMapping(value = "/api/returnpurchasedraft/get", method = RequestMethod.GET,
            headers = "Accept=application/json", params = {"id"})
    public ResponseEntity<?> getReturnPurchaseDraft(@RequestParam(value="id") int id) {
        return ResponseEntity.ok(returnpurchasedraftService.findById(id));
    }
}