/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.InvoiceSalesDraft;
import com.purnama.PJM_Server.repository.InvoiceSalesDraftRepository;
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
public class InvoiceSalesDraftService {
    
    private final InvoiceSalesDraftRepository invoicesalesdraftRepository;
    
    public List<InvoiceSalesDraft> findAll(){
        return invoicesalesdraftRepository.findAll();
    }
    
    public Optional<InvoiceSalesDraft> findById(Integer id){
        return invoicesalesdraftRepository.findById(id);
    }
    
    public InvoiceSalesDraft save(InvoiceSalesDraft invoicesalesdraft){
        return invoicesalesdraftRepository.save(invoicesalesdraft);
    }
    
    public void deleteById(Integer id){
        invoicesalesdraftRepository.deleteById(id);
    }
    
    public Page<InvoiceSalesDraft> findByDraftidContaining(String draftid, int page, int size){
        return invoicesalesdraftRepository.findByDraftidContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "transactiondate")));
    }
    
}