/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.ReturnSales;
import com.purnama.PJM_Server.model.transactional.ItemReturnSales;
import com.purnama.PJM_Server.repository.ItemReturnSalesRepository;
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
public class ItemReturnSalesService {
    private final ItemReturnSalesRepository itemreturnsalesRepository;
    
    public List<ItemReturnSales> findAll(){
        return itemreturnsalesRepository.findAll();
    }
    
    public Optional<ItemReturnSales> findById(Integer id){
        return itemreturnsalesRepository.findById(id);
    }
    
    public ItemReturnSales save(ItemReturnSales itemreturnsales){
        return itemreturnsalesRepository.save(itemreturnsales);
    }
    
    public List<ItemReturnSales> saveAll(List<ItemReturnSales> itemreturnsales){
        return itemreturnsalesRepository.saveAll(itemreturnsales);
    }
    
    public void deleteById(Integer id){
        itemreturnsalesRepository.deleteById(id);
    }
    
    public List<ItemReturnSales> findByReturnSales(ReturnSales returnsales){
        return itemreturnsalesRepository.findByReturnsales(returnsales, Sort.by(Sort.Direction.ASC, "id"));
    }
}
