/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import com.purnama.PJM_Server.model.transactional.ItemInvoiceSales;
import com.purnama.PJM_Server.repository.ItemInvoiceSalesRepository;
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
public class ItemInvoiceSalesService {
    private final ItemInvoiceSalesRepository iteminvoicesalesRepository;
    
    public List<ItemInvoiceSales> findAll(){
        return iteminvoicesalesRepository.findAll();
    }
    
    public Optional<ItemInvoiceSales> findById(Integer id){
        return iteminvoicesalesRepository.findById(id);
    }
    
    public ItemInvoiceSales save(ItemInvoiceSales iteminvoicesales){
        return iteminvoicesalesRepository.save(iteminvoicesales);
    }
    
    public List<ItemInvoiceSales> saveAll(List<ItemInvoiceSales> iteminvoicesaleslist){
        return iteminvoicesalesRepository.saveAll(iteminvoicesaleslist);
    }
    
    public void deleteById(Integer id){
        iteminvoicesalesRepository.deleteById(id);
    }
    
    public List<ItemInvoiceSales> findByInvoiceSales(InvoiceSales invoicesales){
        return iteminvoicesalesRepository.findByInvoicesales(invoicesales, Sort.by(Sort.Direction.ASC, "id"));
    }
}
