/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.DeliveryDraft;
import com.purnama.PJM_Server.repository.DeliveryDraftRepository;
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
public class DeliveryDraftService {
    
    private final DeliveryDraftRepository deliverydraftRepository;
    
    public List<DeliveryDraft> findAll(){
        return deliverydraftRepository.findAll();
    }
    
    public Optional<DeliveryDraft> findById(Integer id){
        return deliverydraftRepository.findById(id);
    }
    
    public DeliveryDraft save(DeliveryDraft deliverydraft){
        return deliverydraftRepository.save(deliverydraft);
    }
    
    public void deleteById(Integer id){
        deliverydraftRepository.deleteById(id);
    }
    
    public Page<DeliveryDraft> findByDraftidContaining(String draftid, int page, int size){
        return deliverydraftRepository.findByDraftidContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "transactiondate")));
    }
    
}