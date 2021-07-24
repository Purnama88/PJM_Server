/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.Delivery;
import com.purnama.PJM_Server.model.transactional.ItemDelivery;
import com.purnama.PJM_Server.repository.ItemDeliveryRepository;
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
public class ItemDeliveryService {
    private final ItemDeliveryRepository itemdeliveryRepository;
    
    public List<ItemDelivery> findAll(){
        return itemdeliveryRepository.findAll();
    }
    
    public Optional<ItemDelivery> findById(Integer id){
        return itemdeliveryRepository.findById(id);
    }
    
    public ItemDelivery save(ItemDelivery itemdelivery){
        return itemdeliveryRepository.save(itemdelivery);
    }
    
    public List<ItemDelivery> saveAll(List<ItemDelivery> itemdeliveries){
        return itemdeliveryRepository.saveAll(itemdeliveries);
    }
    
    public void deleteById(Integer id){
        itemdeliveryRepository.deleteById(id);
    }
    
    public List<ItemDelivery> findByDelivery(Delivery delivery){
        return itemdeliveryRepository.findByDelivery(delivery, Sort.by(Sort.Direction.ASC, "id"));
    }
}
