/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemInvoicePurchaseDraft;
import com.purnama.PJM_Server.repository.ItemInvoicePurchaseDraftRepository;
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
public class ItemInvoicePurchaseDraftService {
    private final ItemInvoicePurchaseDraftRepository iteminvoicepurchasedraftRepository;
    
    public List<ItemInvoicePurchaseDraft> findAll(){
        return iteminvoicepurchasedraftRepository.findAll();
    }
    
    public Optional<ItemInvoicePurchaseDraft> findById(Integer id){
        return iteminvoicepurchasedraftRepository.findById(id);
    }
    
    public ItemInvoicePurchaseDraft save(ItemInvoicePurchaseDraft iteminvoicepurchasedraft){
        return iteminvoicepurchasedraftRepository.save(iteminvoicepurchasedraft);
    }
    
    public List<ItemInvoicePurchaseDraft> saveAll(List<ItemInvoicePurchaseDraft> iteminvoicepurchasedrafts){
        return iteminvoicepurchasedraftRepository.saveAll(iteminvoicepurchasedrafts);
    }
    
    public void deleteById(Integer id){
        iteminvoicepurchasedraftRepository.deleteById(id);
    }
    
    public List<ItemInvoicePurchaseDraft> findByInvoicepurchasedraft(InvoicePurchaseDraft invoicepurchasedraft){
        return iteminvoicepurchasedraftRepository.findByInvoicepurchasedraft(invoicepurchasedraft, Sort.by(Sort.Direction.ASC, "id"));
    }
    
    public Long deleteByInvoicepurchasedraft(InvoicePurchaseDraft invoicepurchasedraft){
        return iteminvoicepurchasedraftRepository.deleteByInvoicepurchasedraft(invoicepurchasedraft);
    }
}
