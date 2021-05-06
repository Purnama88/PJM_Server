/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.pagination.PartnerPagination;
import com.purnama.PJM_Server.service.PartnerService;
import java.time.LocalDateTime;
import java.util.List;
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
@RequestMapping("/api/v1/partners")
public class PartnerAPI {
    
    private final PartnerService partnerService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"keyword"})
    public ResponseEntity<?> getItemList(
            @RequestParam(value="keyword") String keyword) {
        
        List<Partner> ls = partnerService.findByCodeContainingOrNameContaining(keyword, keyword);
        
        return ResponseEntity.ok(ls);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getPartnerList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Partner> ls = partnerService.findByCodeContainingOrNameContainingOrContactnameContaining(keyword, keyword, keyword, page, itemperpage);
        
        PartnerPagination partner_pagination = new PartnerPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(partner_pagination);
    }
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"type", "status"})
    public ResponseEntity getPartnerList(@RequestParam(value="type") String type,
            @RequestParam(value="status") boolean status){
        
        switch (type) {
            case "customer":
                return ResponseEntity.ok(partnerService.findByCustomerAndStatus(true, status));
            case "supplier":
                return ResponseEntity.ok(partnerService.findBySupplierAndStatus(true, status));
            case "nontrade":
                return ResponseEntity.ok(partnerService.findByNontradeAndStatus(true, status));
            default:
                return ResponseEntity.ok(partnerService.findByStatus(status));
        }
        
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getPartner(@PathVariable("id") int id) {
        return ResponseEntity.ok(partnerService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> savePartner(HttpServletRequest httpRequest,
            @RequestBody Partner partner){
        
        partner.setCode(partner.getCode().toUpperCase());
        partner.setCreateddate(LocalDateTime.now());
        partner.setLastmodified(LocalDateTime.now());
        
        try{
            partnerService.save(partner);

            return ResponseEntity.ok(partner);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + partner.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updatePartner(HttpServletRequest httpRequest,
            @RequestBody Partner partner){
        
        Partner temp = partnerService.findById(partner.getId()).get();
        
        temp.setName(partner.getName());
        temp.setContactname(partner.getContactname());
        temp.setAddress(partner.getAddress());
        temp.setPhonenumber(partner.getPhonenumber());
        temp.setPhonenumber2(partner.getPhonenumber2());
        temp.setMobilenumber(partner.getMobilenumber());
        temp.setMobilenumber2(partner.getMobilenumber2());
        temp.setFaxnumber(partner.getFaxnumber());
        temp.setFaxnumber2(partner.getFaxnumber2());
        temp.setEmail(partner.getEmail());
        temp.setEmail2(partner.getEmail2());
        temp.setMaximumdiscount(partner.getMaximumdiscount());
        temp.setMaximumbalance(partner.getMaximumbalance());
        temp.setPaymentdue(partner.getPaymentdue());
        temp.setCustomer(partner.isCustomer());
        temp.setSupplier(partner.isSupplier());
        temp.setNontrade(partner.isNontrade());
        temp.setStatus(partner.isStatus());
        temp.setNote(partner.getNote());
        
        try{
            partnerService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e);
        }
        
    }
    
    
}
