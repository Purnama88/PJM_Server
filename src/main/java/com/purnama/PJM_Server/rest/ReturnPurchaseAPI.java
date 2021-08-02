/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import com.purnama.PJM_Server.model.transactional.ItemReturnPurchase;
import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnPurchaseDraft;
import com.purnama.PJM_Server.service.ReturnPurchaseDraftService;
import com.purnama.PJM_Server.service.ItemReturnPurchaseDraftService;
import com.purnama.PJM_Server.service.ItemReturnPurchaseService;
import com.purnama.PJM_Server.service.NumberingService;
import com.purnama.PJM_Server.service.PartnerService;
import com.purnama.PJM_Server.service.ReturnPurchaseService;
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
@RequestMapping("/api/v1/returnpurchases")
public class ReturnPurchaseAPI {
    
    private final ReturnPurchaseService returnpurchaseService;
    private final ItemReturnPurchaseService itemreturnpurchaseService;
    private final ReturnPurchaseDraftService returnpurchasedraftService;
    private final ItemReturnPurchaseDraftService itemreturnpurchasedraftService;
    private final NumberingService numberingService;
    private final PartnerService partnerService;
    
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
    
    @PostMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> addReturnPurchase(@PathVariable("id") int id) {
        ReturnPurchaseDraft returnpurchasedraft = returnpurchasedraftService.findById(id).get();
        
        List<ItemReturnPurchaseDraft> itemreturnpurchasedrafts = itemreturnpurchasedraftService.findByReturnpurchasedraft(returnpurchasedraft);
        
        Numbering numbering = returnpurchasedraft.getNumbering();
        
        if(numbering == null){
            return ResponseEntity.badRequest().body("Numbering is not valid");
        }
        else if(returnpurchasedraft.getCurrency() == null){
            return ResponseEntity.badRequest().body("Currency is not valid");
        }
        else if(returnpurchasedraft.getPartner() == null){
            return ResponseEntity.badRequest().body("Partner is not valid");
        }
        else if(itemreturnpurchasedrafts.isEmpty()){
            return ResponseEntity.badRequest().body("Return is empty");
        }
        else if(returnpurchasedraft.getDuedate().isBefore(returnpurchasedraft.getInvoicedate()) || returnpurchasedraft.getDuedate().isEqual(returnpurchasedraft.getInvoicedate())){
            return ResponseEntity.badRequest().body("Due date is set before return date");
        }
        else if(returnpurchasedraft.getRate() <= 0){
            return ResponseEntity.badRequest().body("Rate cannot be lower than 1");
        }
        
        ReturnPurchase returnpurchase = new ReturnPurchase();
        returnpurchase.setCreateddate(LocalDateTime.now());
        returnpurchase.setCurrency(returnpurchasedraft.getCurrency());
        returnpurchase.setCurrencycode(returnpurchasedraft.getCurrency().getCode());
        returnpurchase.setCurrencyname(returnpurchasedraft.getCurrency().getName());
        returnpurchase.setDiscount(returnpurchasedraft.getDiscount());
        returnpurchase.setDraftid(returnpurchasedraft.getDraftid());
        returnpurchase.setDuedate(returnpurchasedraft.getDuedate());
        returnpurchase.setFreight(returnpurchasedraft.getFreight());
        returnpurchase.setInvoicedate(returnpurchasedraft.getInvoicedate());
        returnpurchase.setLastmodified(LocalDateTime.now());
        returnpurchase.setNote(returnpurchasedraft.getNote());
        returnpurchase.setNumber(numbering.toString());
        returnpurchase.setPaid(0);
        returnpurchase.setPartner(returnpurchasedraft.getPartner());
        returnpurchase.setPartneraddress(returnpurchasedraft.getPartner().getAddress());
        returnpurchase.setPartnercode(returnpurchasedraft.getPartner().getCode());
        returnpurchase.setPartnername(returnpurchasedraft.getPartner().getName());
        returnpurchase.setPrinted(0);
        returnpurchase.setRate(returnpurchasedraft.getRate());
        returnpurchase.setRounding(returnpurchasedraft.getRounding());
        returnpurchase.setStatus(true);
        returnpurchase.setTax(returnpurchasedraft.getTax());
        returnpurchase.setSubtotal(returnpurchasedraft.getSubtotal());
        returnpurchase.setUser(returnpurchasedraft.getUser());
        returnpurchase.setUsercode(returnpurchasedraft.getUser().getCode());
        returnpurchase.setWarehouse(returnpurchasedraft.getWarehouse());
        returnpurchase.setWarehousecode(returnpurchasedraft.getWarehouse().getCode());
        
        returnpurchaseService.save(returnpurchase);
        
        List<ItemReturnPurchase> itemreturnpurchaselist = new ArrayList<>();
        for(ItemReturnPurchaseDraft itemreturnpurchasedraft : itemreturnpurchasedrafts){
            ItemReturnPurchase itemreturnpurchase = new ItemReturnPurchase();
            itemreturnpurchase.setQuantity(itemreturnpurchasedraft.getQuantity());
            itemreturnpurchase.setPrice(itemreturnpurchasedraft.getPrice());
            itemreturnpurchase.setDiscount(itemreturnpurchasedraft.getDiscount());
            itemreturnpurchase.setDescription(itemreturnpurchasedraft.getDescription());
            itemreturnpurchase.setReturnpurchase(returnpurchase);
            itemreturnpurchase.setBox(itemreturnpurchasedraft.getBox());
            itemreturnpurchase.setItem(itemreturnpurchasedraft.getItem());
            itemreturnpurchaselist.add(itemreturnpurchase);
        }
        
        itemreturnpurchaseService.saveAll(itemreturnpurchaselist);
        
        Partner partner = returnpurchase.getPartner();
        partner.setBalance(partner.getBalance() - returnpurchase.getTotaldefaultcurrency());
        
        partnerService.save(partner);
        
        numbering.setCurrent(numbering.getCurrent()+1);
        
        if(numbering.getCurrent() != numbering.getEnd()){
            
        }
        else{
            numbering.setStatus(false);
        }
        
        numberingService.save(numbering);
        
        itemreturnpurchasedraftService.deleteAll(itemreturnpurchasedrafts);
        
        returnpurchasedraftService.deleteById(id);
        
        return ResponseEntity.ok(returnpurchase.getId());
    }
}
