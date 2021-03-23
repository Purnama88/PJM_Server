/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.combine.PartnerPartnerGroup;
import com.purnama.PJM_Server.service.PartnerPartnerGroupService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/v1/partnerpartners")
public class PartnerPartnerGroupAPI {
    
    private final PartnerPartnerGroupService partnerpartnerService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"partnerid"})
    public ResponseEntity<?> getPartnerPartnerGroupListByPartner(
            @RequestParam(value="partnerid") int partnerid) {
        
        List<PartnerPartnerGroup> ls = partnerpartnerService.findByPartner(partnerid);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"partnergroupid"})
    public ResponseEntity<?> getPartnerPartnerGroupListByPartnerGroup(
            @RequestParam(value="partnergroupid") int partnergroupid) {
        
        List<PartnerPartnerGroup> ls = partnerpartnerService.findByPartnerGroup(partnergroupid);
        
        return ResponseEntity.ok(ls);
    }
}