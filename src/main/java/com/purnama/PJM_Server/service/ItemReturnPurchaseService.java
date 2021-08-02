/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.ItemReturnPurchase;
import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import com.purnama.PJM_Server.repository.ItemReturnPurchaseRepository;
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
public class ItemReturnPurchaseService {
    private final ItemReturnPurchaseRepository itemreturnpurchaseRepository;
    
    public List<ItemReturnPurchase> findAll(){
        return itemreturnpurchaseRepository.findAll();
    }
    
    public Optional<ItemReturnPurchase> findById(Integer id){
        return itemreturnpurchaseRepository.findById(id);
    }
    
    public ItemReturnPurchase save(ItemReturnPurchase itemreturnpurchase){
        return itemreturnpurchaseRepository.save(itemreturnpurchase);
    }
    
    public List<ItemReturnPurchase> saveAll(List<ItemReturnPurchase> itemreturnpurchaselist){
        return itemreturnpurchaseRepository.saveAll(itemreturnpurchaselist);
    }
    
    public void deleteById(Integer id){
        itemreturnpurchaseRepository.deleteById(id);
    }
    
    public List<ItemReturnPurchase> findByReturnPurchase(ReturnPurchase returnpurchase){
        return itemreturnpurchaseRepository.findByReturnpurchase(returnpurchase, Sort.by(Sort.Direction.ASC, "id"));
    }
}
