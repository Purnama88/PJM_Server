/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import com.purnama.PJM_Server.repository.ReturnPurchaseRepository;
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
public class ReturnPurchaseService {
    
    private final ReturnPurchaseRepository returnpurchaseRepository;
    
    public List<ReturnPurchase> findAll(){
        return returnpurchaseRepository.findAll();
    }
    
    public Optional<ReturnPurchase> findById(Integer id){
        return returnpurchaseRepository.findById(id);
    }
    
    public ReturnPurchase save(ReturnPurchase returnpurchase){
        return returnpurchaseRepository.save(returnpurchase);
    }
    
    public void deleteById(Integer id){
        returnpurchaseRepository.deleteById(id);
    }
    
    public Page<ReturnPurchase> findByNumberContaining(String draftid, int page, int size){
        return returnpurchaseRepository.findByNumberContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
}
