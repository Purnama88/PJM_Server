/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.SellPrice;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.repository.SellPriceRepository;
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
public class SellPriceService {
    
    private final SellPriceRepository sellpriceRepository;
    
    public List<SellPrice> findAll(){
        return sellpriceRepository.findAll();
    }
    
    public Optional<SellPrice> findById(Integer id){
        return sellpriceRepository.findById(id);
    }
    
    public SellPrice save(SellPrice sellprice){
        return sellpriceRepository.save(sellprice);
    }
    
    public void deleteById(Integer id){
        sellpriceRepository.deleteById(id);
    }
    
    public List<SellPrice> findByItem(Item item){
        return sellpriceRepository.findByItem(item, Sort.by(Sort.Direction.ASC, "quantity"));
    }
}
