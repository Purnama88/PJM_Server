/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.InvoicePurchase;
import com.purnama.PJM_Server.model.transactional.ItemInvoicePurchase;
import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemInvoicePurchaseDraft;
import com.purnama.PJM_Server.service.InvoicePurchaseDraftService;
import com.purnama.PJM_Server.service.InvoicePurchaseService;
import com.purnama.PJM_Server.service.ItemInvoicePurchaseDraftService;
import com.purnama.PJM_Server.service.ItemInvoicePurchaseService;
import com.purnama.PJM_Server.service.NumberingService;
import com.purnama.PJM_Server.service.PartnerService;
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
@RequestMapping("/api/v1/invoicepurchases")
public class InvoicePurchaseAPI {
    
    private final InvoicePurchaseService invoicepurchaseService;
    private final ItemInvoicePurchaseService iteminvoicepurchaseService;
    private final InvoicePurchaseDraftService invoicepurchasedraftService;
    private final ItemInvoicePurchaseDraftService iteminvoicepurchasedraftService;
    private final NumberingService numberingService;
    private final PartnerService partnerService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getInvoicePurchaseList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<InvoicePurchase> ls = invoicepurchaseService.findByNumberContaining(keyword, page, itemperpage);

        Pagination<InvoicePurchase> invoicepurchasepagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(invoicepurchasepagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getInvoicePurchase(@PathVariable("id") int id) {
        return ResponseEntity.ok(invoicepurchaseService.findById(id));
    }
    
    @PostMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> addInvoicePurchase(@PathVariable("id") int id) {
        InvoicePurchaseDraft invoicepurchasedraft = invoicepurchasedraftService.findById(id).get();
        
        List<ItemInvoicePurchaseDraft> iteminvoicepurchasedrafts = iteminvoicepurchasedraftService.findByInvoicepurchasedraft(invoicepurchasedraft);
        
        Numbering numbering = invoicepurchasedraft.getNumbering();
        
        if(numbering == null){
            return ResponseEntity.badRequest().body("Numbering is not valid");
        }
        else if(invoicepurchasedraft.getCurrency() == null){
            return ResponseEntity.badRequest().body("Currency is not valid");
        }
        else if(invoicepurchasedraft.getPartner() == null){
            return ResponseEntity.badRequest().body("Partner is not valid");
        }
        else if(iteminvoicepurchasedrafts.isEmpty()){
            return ResponseEntity.badRequest().body("Invoice is empty");
        }
        else if(invoicepurchasedraft.getDuedate().isBefore(invoicepurchasedraft.getInvoicedate()) || invoicepurchasedraft.getDuedate().isEqual(invoicepurchasedraft.getInvoicedate())){
            return ResponseEntity.badRequest().body("Due date is set before invoice date");
        }
        else if(invoicepurchasedraft.getRate() <= 0){
            return ResponseEntity.badRequest().body("Rate cannot be lower than 1");
        }
        
        InvoicePurchase invoicepurchase = new InvoicePurchase();
        invoicepurchase.setCreateddate(LocalDateTime.now());
        invoicepurchase.setCurrency(invoicepurchasedraft.getCurrency());
        invoicepurchase.setCurrencycode(invoicepurchasedraft.getCurrency().getCode());
        invoicepurchase.setCurrencyname(invoicepurchasedraft.getCurrency().getName());
        invoicepurchase.setDiscount(invoicepurchasedraft.getDiscount());
        invoicepurchase.setDraftid(invoicepurchasedraft.getDraftid());
        invoicepurchase.setDuedate(invoicepurchasedraft.getDuedate());
        invoicepurchase.setFreight(invoicepurchasedraft.getFreight());
        invoicepurchase.setInvoicedate(invoicepurchasedraft.getInvoicedate());
        invoicepurchase.setLastmodified(LocalDateTime.now());
        invoicepurchase.setNote(invoicepurchasedraft.getNote());
        invoicepurchase.setNumber(numbering.toString());
        invoicepurchase.setPaid(0);
        invoicepurchase.setPartner(invoicepurchasedraft.getPartner());
        invoicepurchase.setPartneraddress(invoicepurchasedraft.getPartner().getAddress());
        invoicepurchase.setPartnercode(invoicepurchasedraft.getPartner().getCode());
        invoicepurchase.setPartnername(invoicepurchasedraft.getPartner().getName());
        invoicepurchase.setPrinted(0);
        invoicepurchase.setRate(invoicepurchasedraft.getRate());
        invoicepurchase.setRounding(invoicepurchasedraft.getRounding());
        invoicepurchase.setStatus(true);
        invoicepurchase.setTax(invoicepurchasedraft.getTax());
        invoicepurchase.setSubtotal(invoicepurchasedraft.getSubtotal());
        invoicepurchase.setUser(invoicepurchasedraft.getUser());
        invoicepurchase.setUsercode(invoicepurchasedraft.getUser().getCode());
        invoicepurchase.setWarehouse(invoicepurchasedraft.getWarehouse());
        invoicepurchase.setWarehousecode(invoicepurchasedraft.getWarehouse().getCode());
        
        invoicepurchaseService.save(invoicepurchase);
        
        List<ItemInvoicePurchase> iteminvoicepurchaselist = new ArrayList<>();
        for(ItemInvoicePurchaseDraft iteminvoicepurchasedraft : iteminvoicepurchasedrafts){
            ItemInvoicePurchase iteminvoicepurchase = new ItemInvoicePurchase();
            iteminvoicepurchase.setQuantity(iteminvoicepurchasedraft.getQuantity());
            iteminvoicepurchase.setPrice(iteminvoicepurchasedraft.getPrice());
            iteminvoicepurchase.setDiscount(iteminvoicepurchasedraft.getDiscount());
            iteminvoicepurchase.setDescription(iteminvoicepurchasedraft.getDescription());
            iteminvoicepurchase.setInvoicepurchase(invoicepurchase);
            iteminvoicepurchase.setBox(iteminvoicepurchasedraft.getBox());
            iteminvoicepurchase.setItem(iteminvoicepurchasedraft.getItem());
            iteminvoicepurchaselist.add(iteminvoicepurchase);
        }
        
        iteminvoicepurchaseService.saveAll(iteminvoicepurchaselist);
        
        Partner partner = invoicepurchase.getPartner();
        partner.setBalance(partner.getBalance() - invoicepurchase.getTotaldefaultcurrency());
        
        partnerService.save(partner);
        
        numbering.setCurrent(numbering.getCurrent()+1);
        
        if(numbering.getCurrent() != numbering.getEnd()){
            
        }
        else{
            numbering.setStatus(false);
        }
        
        numberingService.save(numbering);
        
        iteminvoicepurchasedraftService.deleteAll(iteminvoicepurchasedrafts);
        
        invoicepurchasedraftService.deleteById(id);
        
        return ResponseEntity.ok(invoicepurchase.getId());
    }
}

