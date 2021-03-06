/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnPurchaseDraft;
import com.purnama.PJM_Server.repository.ItemReturnPurchaseDraftRepository;
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
public class ItemReturnPurchaseDraftService {
    private final ItemReturnPurchaseDraftRepository itemreturnpurchasedraftRepository;
    
    public List<ItemReturnPurchaseDraft> findAll(){
        return itemreturnpurchasedraftRepository.findAll();
    }
    
    public Optional<ItemReturnPurchaseDraft> findById(Integer id){
        return itemreturnpurchasedraftRepository.findById(id);
    }
    
    public ItemReturnPurchaseDraft save(ItemReturnPurchaseDraft brand){
        return itemreturnpurchasedraftRepository.save(brand);
    }
    
    public void deleteById(Integer id){
        itemreturnpurchasedraftRepository.deleteById(id);
    }
    
    public List<ItemReturnPurchaseDraft> findByReturnpurchasedraft(ReturnPurchaseDraft returnpurchasedraft){
        return itemreturnpurchasedraftRepository.findByReturnpurchasedraft(returnpurchasedraft, Sort.by(Sort.Direction.ASC, "id"));
    }
}
