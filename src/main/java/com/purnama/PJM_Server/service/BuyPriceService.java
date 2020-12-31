/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.BuyPrice;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.repository.BuyPriceRepository;
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
public class BuyPriceService {
    
    private final BuyPriceRepository buypriceRepository;
    
    public List<BuyPrice> findAll(){
        return buypriceRepository.findAll();
    }
    
    public Optional<BuyPrice> findById(Integer id){
        return buypriceRepository.findById(id);
    }
    
    public BuyPrice save(BuyPrice buyprice){
        return buypriceRepository.save(buyprice);
    }
    
    public void deleteById(Integer id){
        buypriceRepository.deleteById(id);
    }
    
    public List<BuyPrice> findByItem(Item item){
        return buypriceRepository.findByItem(item, Sort.by(Sort.Direction.ASC, "quantity"));
    }
}
