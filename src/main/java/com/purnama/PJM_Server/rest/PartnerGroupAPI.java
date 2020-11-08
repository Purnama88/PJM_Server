/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.PartnerGroup;
import com.purnama.PJM_Server.model.pagination.PartnerGroupPagination;
import com.purnama.PJM_Server.service.PartnerGroupService;
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
@RequestMapping("/api/v1/partnergroups")
public class PartnerGroupAPI {
    
    private final PartnerGroupService partnergroupService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getPartnerGroupList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<PartnerGroup> ls = partnergroupService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        PartnerGroupPagination partnergroup_pagination = new PartnerGroupPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(partnergroup_pagination);
    }
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getPartnerGroupList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(partnergroupService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(partnergroupService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getPartnerGroup(@PathVariable("id") int id) {
        return ResponseEntity.ok(partnergroupService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> savePartnerGroup(HttpServletRequest httpRequest,
            @RequestBody PartnerGroup partnergroup){
        
        partnergroup.setCode(partnergroup.getCode().toUpperCase());
        partnergroup.setCreateddate(LocalDateTime.now());
        partnergroup.setLastmodified(LocalDateTime.now());
        
        try{
            partnergroupService.save(partnergroup);

            return ResponseEntity.ok(partnergroup);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + partnergroup.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updatePartnerGroup(HttpServletRequest httpRequest,
            @RequestBody PartnerGroup partnergroup){
        
        PartnerGroup temp = partnergroupService.findById(partnergroup.getId()).get();
        temp.setDescription(partnergroup.getDescription());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(partnergroup.getName());
        temp.setNote(partnergroup.getNote());
        temp.setStatus(partnergroup.isStatus());
        
        try{
            partnergroupService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + partnergroup.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
}
