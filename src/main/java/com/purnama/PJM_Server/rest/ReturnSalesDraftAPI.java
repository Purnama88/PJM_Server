/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.CurrencyService;
import com.purnama.PJM_Server.service.ItemReturnSalesDraftService;
import com.purnama.PJM_Server.service.ReturnSalesDraftService;
import com.purnama.PJM_Server.util.IdGenerator;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/returnsalesdrafts")
public class ReturnSalesDraftAPI {
    
    private final ReturnSalesDraftService returnsalesdraftService;
    
    private final ItemReturnSalesDraftService itemreturnsalesdraftService;
    
    private final CurrencyService currencyService;
    
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getReturnSalesDraftList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<ReturnSalesDraft> ls = returnsalesdraftService.findByDraftidContaining(keyword, page, itemperpage);

        Pagination<ReturnSalesDraft> returnsalesdraftpagination = new Pagination<>(ls.getTotalPages(), ls.getContent());

        return ResponseEntity.ok(returnsalesdraftpagination);
       
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getReturnSalesDraft(@PathVariable("id") int id) {
        return ResponseEntity.ok(returnsalesdraftService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveReturnSalesDraft(HttpServletRequest httpRequest) {
        
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        User user = new User();
        user.setId(JwtUtil.parseToken(header.substring(7)));
        Warehouse warehouse = new Warehouse();
        warehouse.setId(JwtUtil.parseToken2(header.substring(7)));
        
        Currency currency = currencyService.findByDefaultcurrencyTrue().get();
        
        ReturnSalesDraft returnsalesdraft = new ReturnSalesDraft();
        returnsalesdraft.setDraftid(IdGenerator.generateId());
        returnsalesdraft.setCreateddate(LocalDateTime.now());
        returnsalesdraft.setInvoicedate(LocalDateTime.now());
        returnsalesdraft.setDuedate(LocalDateTime.now());
        returnsalesdraft.setWarehouse(warehouse);
        returnsalesdraft.setNote("");
        returnsalesdraft.setSubtotal(0);
        returnsalesdraft.setDiscount(0);
        returnsalesdraft.setRounding(0);
        returnsalesdraft.setFreight(0);
        returnsalesdraft.setTax(0);
        returnsalesdraft.setUser(user);
        returnsalesdraft.setLastmodified(LocalDateTime.now());
        returnsalesdraft.setCurrency(currency);
        returnsalesdraft.setRate(1);
        
        returnsalesdraftService.save(returnsalesdraft);
        
        return ResponseEntity.ok(returnsalesdraft.getId());
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateReturnSalesDraft(HttpServletRequest httpRequest,
            @RequestBody ReturnSalesDraft returnsalesdraft){
        
        try{
            returnsalesdraft.setLastmodified(LocalDateTime.now());
            returnsalesdraftService.save(returnsalesdraft);

            return ResponseEntity.ok(returnsalesdraft);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Id '" + returnsalesdraft.getId() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @DeleteMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> deleteReturnSalesDraft(@PathVariable("id") int id){
        ReturnSalesDraft returnsalesdraft = returnsalesdraftService.findById(id).get();
        
        itemreturnsalesdraftService.deleteByReturnsalesdraft(returnsalesdraft);
        
        returnsalesdraftService.deleteById(returnsalesdraft.getId());
        
        return ResponseEntity.ok("");
    }
}