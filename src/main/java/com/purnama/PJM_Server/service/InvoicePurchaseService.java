/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.InvoicePurchase;
import com.purnama.PJM_Server.repository.InvoicePurchaseRepository;
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
public class InvoicePurchaseService {
    
    private final InvoicePurchaseRepository invoicepurchaseRepository;
    
    public List<InvoicePurchase> findAll(){
        return invoicepurchaseRepository.findAll();
    }
    
    public Optional<InvoicePurchase> findById(Integer id){
        return invoicepurchaseRepository.findById(id);
    }
    
    public InvoicePurchase save(InvoicePurchase invoicepurchase){
        return invoicepurchaseRepository.save(invoicepurchase);
    }
    
    public void deleteById(Integer id){
        invoicepurchaseRepository.deleteById(id);
    }
    
    public Page<InvoicePurchase> findByNumberContaining(String draftid, int page, int size){
        return invoicepurchaseRepository.findByNumberContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
}