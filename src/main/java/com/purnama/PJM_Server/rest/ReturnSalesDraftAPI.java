/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import com.purnama.PJM_Server.model.pagination.ReturnSalesDraftPagination;
import com.purnama.PJM_Server.service.ReturnSalesDraftService;
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
public class ReturnSalesDraftAPI {
    
    private final ReturnSalesDraftService returnsalesdraftService;
    
    
    @RequestMapping(value = "/api/returnsalesdraft/list", method = RequestMethod.GET, 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getReturnSalesDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ReturnSalesDraft> ls = returnsalesdraftService.findByDraftidContaining(keyword, page, itemperpage);
        
        ReturnSalesDraftPagination returnsalesdraft_pagination = new ReturnSalesDraftPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(returnsalesdraft_pagination);
    }
    
//    @RequestMapping(value = "/api/returnsalesdraft/save", method = RequestMethod.POST,
//            headers = "Accept=application/json")
//    public ResponseEntity<?> saveReturnSalesDraft(HttpServletRequest httpRequest,
//            @RequestBody ReturnSalesDraft returnsalesdraft){
//        
//        returnsalesdraft.setCode(returnsalesdraft.getCode().toUpperCase());
//        
//        returnsalesdraft.setLastmodified(LocalDateTime.now());
//        
//        try{
//            returnsalesdraftService.save(returnsalesdraft);
//
//            return ResponseEntity.ok(returnsalesdraft);
//        }
//        catch(DataIntegrityViolationException e){
//            return ResponseEntity.badRequest().body("Code '" + returnsalesdraft.getCode() +"' already exist");
//        }
//        catch(Exception e){
//            return ResponseEntity.badRequest().body(e);
//        }
//    }
    
    @RequestMapping(value = "/api/returnsalesdraft/get", method = RequestMethod.GET,
            headers = "Accept=application/json", params = {"id"})
    public ResponseEntity<?> getReturnSalesDraft(@RequestParam(value="id") int id) {
        return ResponseEntity.ok(returnsalesdraftService.findById(id));
    }
}