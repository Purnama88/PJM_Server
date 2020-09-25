/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.DeliveryDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemDeliveryDraft;
import com.purnama.PJM_Server.repository.ItemDeliveryDraftRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */
@Service
@RequiredArgsConstructor
public class ItemDeliveryDraftService {
    private final ItemDeliveryDraftRepository itemdeliverydraftRepository;
    
    public List<ItemDeliveryDraft> findAll(){
        return itemdeliverydraftRepository.findAll();
    }
    
    public Optional<ItemDeliveryDraft> findById(Integer id){
        return itemdeliverydraftRepository.findById(id);
    }
    
    public ItemDeliveryDraft save(ItemDeliveryDraft brand){
        return itemdeliverydraftRepository.save(brand);
    }
    
    public void deleteById(Integer id){
        itemdeliverydraftRepository.deleteById(id);
    }
    
    public List<ItemDeliveryDraft> findByDeliverydraft(DeliveryDraft deliverydraft){
        return itemdeliverydraftRepository.findByDeliverydraft(deliverydraft, Sort.by(Sort.Direction.ASC, "id"));
    }
}
