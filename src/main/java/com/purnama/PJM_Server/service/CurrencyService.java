/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.repository.CurrencyRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */

@Service
@RequiredArgsConstructor
public class CurrencyService {
    
    private final CurrencyRepository currencyRepository;
    
    public List<Currency> findAll(){
        return currencyRepository.findAll();
    }
    
    public Optional<Currency> findById(Integer id){
        return currencyRepository.findById(id);
    }
    
    public Currency save(Currency currency){
        return currencyRepository.save(currency);
    }
    
    public void deleteById(Integer id){
        currencyRepository.deleteById(id);
    }
    
    public Page<Currency> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return currencyRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<Currency> findByCode(String code){
        return currencyRepository.findByCode(code);
    }
    
    public Optional<Currency> findByDefaultcurrencyTrue(){
        return currencyRepository.findByDefaultcurrencyTrue();
    }
    
    public List<Currency> findByStatus(boolean status){
        return currencyRepository.findByStatus(status, Sort.by(Sort.Direction.ASC, "name"));
    }
}
