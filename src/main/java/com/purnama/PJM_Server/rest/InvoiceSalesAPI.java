/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import com.purnama.PJM_Server.model.transactional.ItemInvoiceSales;
import com.purnama.PJM_Server.model.transactional.draft.InvoiceSalesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemInvoiceSalesDraft;
import com.purnama.PJM_Server.service.InvoiceSalesDraftService;
import com.purnama.PJM_Server.service.InvoiceSalesService;
import com.purnama.PJM_Server.service.ItemInvoiceSalesDraftService;
import com.purnama.PJM_Server.service.ItemInvoiceSalesService;
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
@RequestMapping("/api/v1/invoicesales")
public class InvoiceSalesAPI {
    
    private final InvoiceSalesService invoicesalesService;
    private final ItemInvoiceSalesService iteminvoicesalesService;
    private final InvoiceSalesDraftService invoicesalesdraftService;
    private final ItemInvoiceSalesDraftService iteminvoicesalesdraftService;
    private final NumberingService numberingService;
    private final PartnerService partnerService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getInvoiceSalesList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<InvoiceSales> ls = invoicesalesService.findByNumberContaining(keyword, page, itemperpage);

        Pagination<InvoiceSales> invoicesalespagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(invoicesalespagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getInvoiceSales(@PathVariable("id") int id) {
        return ResponseEntity.ok(invoicesalesService.findById(id));
    }
    
    @PostMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> addInvoiceSales(@PathVariable("id") int id) {
        InvoiceSalesDraft invoicesalesdraft = invoicesalesdraftService.findById(id).get();
        
        List<ItemInvoiceSalesDraft> iteminvoicesalesdrafts = iteminvoicesalesdraftService.findByInvoicesalesdraft(invoicesalesdraft);
        
        Numbering numbering = invoicesalesdraft.getNumbering();
        
        if(numbering == null){
            return ResponseEntity.badRequest().body("Numbering is not valid");
        }
        else if(invoicesalesdraft.getCurrency() == null){
            return ResponseEntity.badRequest().body("Currency is not valid");
        }
        else if(invoicesalesdraft.getPartner() == null){
            return ResponseEntity.badRequest().body("Partner is not valid");
        }
        else if(iteminvoicesalesdrafts.isEmpty()){
            return ResponseEntity.badRequest().body("Invoice is empty");
        }
        else if(invoicesalesdraft.getDuedate().isBefore(invoicesalesdraft.getInvoicedate()) || invoicesalesdraft.getDuedate().isEqual(invoicesalesdraft.getInvoicedate())){
            return ResponseEntity.badRequest().body("Due date is set before invoice date");
        }
        else if(invoicesalesdraft.getRate() <= 0){
            return ResponseEntity.badRequest().body("Rate cannot be lower than 1");
        }
        
        InvoiceSales invoicesales = new InvoiceSales();
        invoicesales.setCreateddate(LocalDateTime.now());
        invoicesales.setCurrency(invoicesalesdraft.getCurrency());
        invoicesales.setCurrencycode(invoicesalesdraft.getCurrency().getCode());
        invoicesales.setCurrencyname(invoicesalesdraft.getCurrency().getName());
        invoicesales.setDiscount(invoicesalesdraft.getDiscount());
        invoicesales.setDraftid(invoicesalesdraft.getDraftid());
        invoicesales.setDuedate(invoicesalesdraft.getDuedate());
        invoicesales.setFreight(invoicesalesdraft.getFreight());
        invoicesales.setInvoicedate(invoicesalesdraft.getInvoicedate());
        invoicesales.setLastmodified(LocalDateTime.now());
        invoicesales.setNote(invoicesalesdraft.getNote());
        invoicesales.setNumber(numbering.toString());
        invoicesales.setPaid(0);
        invoicesales.setPartner(invoicesalesdraft.getPartner());
        invoicesales.setPartneraddress(invoicesalesdraft.getPartner().getAddress());
        invoicesales.setPartnercode(invoicesalesdraft.getPartner().getCode());
        invoicesales.setPartnername(invoicesalesdraft.getPartner().getName());
        invoicesales.setPrinted(0);
        invoicesales.setRate(invoicesalesdraft.getRate());
        invoicesales.setRounding(invoicesalesdraft.getRounding());
        invoicesales.setStatus(true);
        invoicesales.setTax(invoicesalesdraft.getTax());
        invoicesales.setSubtotal(invoicesalesdraft.getSubtotal());
        invoicesales.setUser(invoicesalesdraft.getUser());
        invoicesales.setUsercode(invoicesalesdraft.getUser().getCode());
        invoicesales.setWarehouse(invoicesalesdraft.getWarehouse());
        invoicesales.setWarehousecode(invoicesalesdraft.getWarehouse().getCode());
        
        invoicesalesService.save(invoicesales);
        
        List<ItemInvoiceSales> iteminvoicesaleslist = new ArrayList<>();
        for(ItemInvoiceSalesDraft iteminvoicesalesdraft : iteminvoicesalesdrafts){
            ItemInvoiceSales iteminvoicesales = new ItemInvoiceSales();
            iteminvoicesales.setQuantity(iteminvoicesalesdraft.getQuantity());
            iteminvoicesales.setPrice(iteminvoicesalesdraft.getPrice());
            iteminvoicesales.setDiscount(iteminvoicesalesdraft.getDiscount());
            iteminvoicesales.setDescription(iteminvoicesalesdraft.getDescription());
            iteminvoicesales.setInvoicesales(invoicesales);
            iteminvoicesales.setBox(iteminvoicesalesdraft.getBox());
            iteminvoicesales.setItem(iteminvoicesalesdraft.getItem());
            iteminvoicesaleslist.add(iteminvoicesales);
        }
        
        iteminvoicesalesService.saveAll(iteminvoicesaleslist);
        
        Partner partner = invoicesales.getPartner();
        partner.setBalance(partner.getBalance() - invoicesales.getTotaldefaultcurrency());
        
        partnerService.save(partner);
        
        numbering.setCurrent(numbering.getCurrent()+1);
        
        if(numbering.getCurrent() != numbering.getEnd()){
            
        }
        else{
            numbering.setStatus(false);
        }
        
        numberingService.save(numbering);
        
        iteminvoicesalesdraftService.deleteAll(iteminvoicesalesdrafts);
        
        invoicesalesdraftService.deleteById(id);
        
        return ResponseEntity.ok(invoicesales.getId());
    }
}
