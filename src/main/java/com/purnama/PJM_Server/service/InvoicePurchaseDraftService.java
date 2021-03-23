/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import com.purnama.PJM_Server.repository.InvoicePurchaseDraftRepository;
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
public class InvoicePurchaseDraftService {
    
    private final InvoicePurchaseDraftRepository invoicepurchasedraftRepository;
    
    public List<InvoicePurchaseDraft> findAll(){
        return invoicepurchasedraftRepository.findAll();
    }
    
    public Optional<InvoicePurchaseDraft> findById(Integer id){
        return invoicepurchasedraftRepository.findById(id);
    }
    
    public InvoicePurchaseDraft save(InvoicePurchaseDraft invoicepurchasedraft){
        return invoicepurchasedraftRepository.save(invoicepurchasedraft);
    }
    
    public void deleteById(Integer id){
        invoicepurchasedraftRepository.deleteById(id);
    }
    
    public Page<InvoicePurchaseDraft> findByDraftidContaining(String draftid, int page, int size){
        return invoicepurchasedraftRepository.findByDraftidContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
    
}