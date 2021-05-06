/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import com.purnama.PJM_Server.repository.InvoiceSalesRepository;
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
public class InvoiceSalesService {
    
    private final InvoiceSalesRepository invoicesalesRepository;
    
    public List<InvoiceSales> findAll(){
        return invoicesalesRepository.findAll();
    }
    
    public Optional<InvoiceSales> findById(Integer id){
        return invoicesalesRepository.findById(id);
    }
    
    public InvoiceSales save(InvoiceSales invoicesales){
        return invoicesalesRepository.save(invoicesales);
    }
    
    public void deleteById(Integer id){
        invoicesalesRepository.deleteById(id);
    }
    
    public Page<InvoiceSales> findByNumberContaining(String draftid, int page, int size){
        return invoicesalesRepository.findByNumberContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
}
