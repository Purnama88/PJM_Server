/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.pagination.CurrencyPagination;
import com.purnama.PJM_Server.service.CurrencyService;
import java.time.LocalDateTime;
import java.util.Optional;
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
@RequestMapping("/api/v1/currencies")
public class CurrencyAPI {
    
    private final CurrencyService currencyService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getCurrencyList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Currency> ls = currencyService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        CurrencyPagination currency_pagination = new CurrencyPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(currency_pagination);
    }
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getCurrencyList(@RequestParam(value="status") boolean status){
        return ResponseEntity.ok(currencyService.findByStatus(status));
        
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"defaultcurrency"})
    public ResponseEntity<?> getDefaultCurrency(@RequestParam(value="defaultcurrency") boolean defaultcurrency) {
        return ResponseEntity.ok(currencyService.findByDefaultcurrencyTrue());
    }
    
    @GetMapping(value = "/{id}", 
            headers = "Accept=application/json")
    public ResponseEntity<?> getCurrency(@PathVariable("id") int id) {
        return ResponseEntity.ok(currencyService.findById(id));
    }
    
    @PostMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> saveCurrency(HttpServletRequest httpRequest,
            @RequestBody Currency currency){
        
        currency.setCode(currency.getCode().toUpperCase());
        currency.setCreateddate(LocalDateTime.now());
        currency.setLastmodified(LocalDateTime.now());
        
        if(currency.isDefaultcurrency()){
            if(!currency.isStatus()){
                return ResponseEntity.badRequest().body("Default currency must be active");
            }
            else{
                Optional<Currency> opt = currencyService.findByDefaultcurrencyTrue();
                
                if(opt.isPresent()){
                    Currency defcurrency = opt.get();
                    defcurrency.setDefaultcurrency(false);
                    currencyService.save(defcurrency);
                }
            }
        }
        
        try{
            currencyService.save(currency);

            return ResponseEntity.ok(currency);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + currency.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "",
            headers = "Accept=application/json")
    public ResponseEntity<?> updateCurrency(HttpServletRequest httpRequest,
            @RequestBody Currency currency){
        
        Currency temp = currencyService.findById(currency.getId()).get();
        temp.setDescription(currency.getDescription());
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(currency.getName());
        temp.setNote(currency.getNote());
        temp.setStatus(currency.isStatus());
        temp.setDefaultcurrency(currency.isDefaultcurrency());
        
        
        if(temp.isDefaultcurrency()){
            if(!temp.isStatus()){
                return ResponseEntity.badRequest().body("Default currency must be active");
            }
            else{
                Optional<Currency> opt = currencyService.findByDefaultcurrencyTrue();
                
                if(opt.isPresent()){
                    Currency defcurrency = opt.get();
                    defcurrency.setDefaultcurrency(false);
                    currencyService.save(defcurrency);
                }
            }
        }
        
        try{
            currencyService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + currency.getCode() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
}
