/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.combine.PartnerPartnerGroup;
import com.purnama.PJM_Server.service.PartnerPartnerGroupService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/api/v1/partnerpartnergroups")
public class PartnerPartnerGroupAPI {
    
    private final PartnerPartnerGroupService partnerpartnergroupService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"partnerid"})
    public ResponseEntity<?> getPartnerPartnerGroupListByPartner(
            @RequestParam(value="partnerid") int partnerid) {
        
        List<PartnerPartnerGroup> ls = partnerpartnergroupService.findByPartner(partnerid);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"partnergroupid"})
    public ResponseEntity<?> getPartnerPartnerGroupListByPartnerGroup(
            @RequestParam(value="partnergroupid") int partnergroupid) {
        
        List<PartnerPartnerGroup> ls = partnerpartnergroupService.findByPartnerGroup(partnergroupid);
        
        return ResponseEntity.ok(ls);
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> savePartnerPartnerGroup(HttpServletRequest httpRequest,
            @RequestBody PartnerPartnerGroup partnerpartnergroup){
        
        try{
            partnerpartnergroupService.save(partnerpartnergroup);

            return ResponseEntity.ok(partnerpartnergroup);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Partner already exist in group");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @DeleteMapping(value = "",
            headers = "Accept=application/json", params = {"id"})
    public ResponseEntity<?> deletePartnerPartnerGroup(
            @RequestParam(value="id") int id){
        
        partnerpartnergroupService.deleteById(id);
        
        return ResponseEntity.ok("");
    }
}