/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.Delivery;
import com.purnama.PJM_Server.repository.DeliveryRepository;
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
public class DeliveryService {
    
    private final DeliveryRepository deliveryRepository;
    
    public List<Delivery> findAll(){
        return deliveryRepository.findAll();
    }
    
    public Optional<Delivery> findById(Integer id){
        return deliveryRepository.findById(id);
    }
    
    public Delivery save(Delivery delivery){
        return deliveryRepository.save(delivery);
    }
    
    public void deleteById(Integer id){
        deliveryRepository.deleteById(id);
    }
    
    public Page<Delivery> findByNumberContaining(String draftid, int page, int size){
        return deliveryRepository.findByNumberContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
}
