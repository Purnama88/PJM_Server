/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.InvoicePurchase;
import com.purnama.PJM_Server.model.transactional.ItemInvoicePurchase;
import com.purnama.PJM_Server.repository.ItemInvoicePurchaseRepository;
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
public class ItemInvoicePurchaseService {
    private final ItemInvoicePurchaseRepository iteminvoicepurchaseRepository;
    
    public List<ItemInvoicePurchase> findAll(){
        return iteminvoicepurchaseRepository.findAll();
    }
    
    public Optional<ItemInvoicePurchase> findById(Integer id){
        return iteminvoicepurchaseRepository.findById(id);
    }
    
    public ItemInvoicePurchase save(ItemInvoicePurchase iteminvoicepurchase){
        return iteminvoicepurchaseRepository.save(iteminvoicepurchase);
    }
    
    public List<ItemInvoicePurchase> saveAll(List<ItemInvoicePurchase> iteminvoicepurchase){
        return iteminvoicepurchaseRepository.saveAll(iteminvoicepurchase);
    }
    
    public void deleteById(Integer id){
        iteminvoicepurchaseRepository.deleteById(id);
    }
    
    public List<ItemInvoicePurchase> findByInvoicePurchase(InvoicePurchase invoicepurchase){
        return iteminvoicepurchaseRepository.findByInvoicepurchase(invoicepurchase, Sort.by(Sort.Direction.ASC, "id"));
    }
}
