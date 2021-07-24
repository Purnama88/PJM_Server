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
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemDeliveryDraftService {
    private final ItemDeliveryDraftRepository itemdeliverydraftRepository;
    
    public List<ItemDeliveryDraft> findAll(){
        return itemdeliverydraftRepository.findAll();
    }
    
    public Optional<ItemDeliveryDraft> findById(Integer id){
        return itemdeliverydraftRepository.findById(id);
    }
    
    public ItemDeliveryDraft save(ItemDeliveryDraft itemdeliverydraft){
        return itemdeliverydraftRepository.save(itemdeliverydraft);
    }

    public List<ItemDeliveryDraft> saveAll(List<ItemDeliveryDraft> itemdeliverydrafts){
        return itemdeliverydraftRepository.saveAll(itemdeliverydrafts);
    }
    
    public void deleteById(Integer id){
        itemdeliverydraftRepository.deleteById(id);
    }
    
    public void deleteAll(List<ItemDeliveryDraft> itemdeliverydrafts){
        itemdeliverydraftRepository.deleteAll(itemdeliverydrafts);
    }
    
    public List<ItemDeliveryDraft> findByDeliverydraft(DeliveryDraft deliverydraft){
        return itemdeliverydraftRepository.findByDeliverydraft(deliverydraft, Sort.by(Sort.Direction.ASC, "id"));
    }
     
    public Long deleteByDeliverydraft(DeliveryDraft deliverydraft){
        return itemdeliverydraftRepository.deleteByDeliverydraft(deliverydraft);
    }
}
