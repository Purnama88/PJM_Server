/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.repository.ReturnPurchaseDraftRepository;
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
public class ReturnPurchaseDraftService {
    
    private final ReturnPurchaseDraftRepository returnpurchasedraftRepository;
    
    public List<ReturnPurchaseDraft> findAll(){
        return returnpurchasedraftRepository.findAll();
    }
    
    public Optional<ReturnPurchaseDraft> findById(Integer id){
        return returnpurchasedraftRepository.findById(id);
    }
    
    public ReturnPurchaseDraft save(ReturnPurchaseDraft returnpurchasedraft){
        return returnpurchasedraftRepository.save(returnpurchasedraft);
    }
    
    public void deleteById(Integer id){
        returnpurchasedraftRepository.deleteById(id);
    }
    
    public Page<ReturnPurchaseDraft> findByDraftidContaining(String draftid, int page, int size){
        return returnpurchasedraftRepository.findByDraftidContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "transactiondate")));
    }
    
}