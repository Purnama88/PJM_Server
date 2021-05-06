/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.ReturnSales;
import com.purnama.PJM_Server.repository.ReturnSalesRepository;
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
public class ReturnSalesService {
    
    private final ReturnSalesRepository returnsalesRepository;
    
    public List<ReturnSales> findAll(){
        return returnsalesRepository.findAll();
    }
    
    public Optional<ReturnSales> findById(Integer id){
        return returnsalesRepository.findById(id);
    }
    
    public ReturnSales save(ReturnSales returnsales){
        return returnsalesRepository.save(returnsales);
    }
    
    public void deleteById(Integer id){
        returnsalesRepository.deleteById(id);
    }
    
    public Page<ReturnSales> findByNumberContaining(String draftid, int page, int size){
        return returnsalesRepository.findByNumberContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
}
