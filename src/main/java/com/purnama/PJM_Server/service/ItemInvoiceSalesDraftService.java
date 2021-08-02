/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.InvoiceSalesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemInvoiceSalesDraft;
import com.purnama.PJM_Server.repository.ItemInvoiceSalesDraftRepository;
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
public class ItemInvoiceSalesDraftService {
    private final ItemInvoiceSalesDraftRepository iteminvoicesalesdraftRepository;
    
    public List<ItemInvoiceSalesDraft> findAll(){
        return iteminvoicesalesdraftRepository.findAll();
    }
    
    public Optional<ItemInvoiceSalesDraft> findById(Integer id){
        return iteminvoicesalesdraftRepository.findById(id);
    }
    
    public ItemInvoiceSalesDraft save(ItemInvoiceSalesDraft iteminvoicesalesdraft){
        return iteminvoicesalesdraftRepository.save(iteminvoicesalesdraft);
    }
    
    public List<ItemInvoiceSalesDraft> saveAll(List<ItemInvoiceSalesDraft> iteminvoicesalesdrafts){
        return iteminvoicesalesdraftRepository.saveAll(iteminvoicesalesdrafts);
    }
    
    public void deleteAll(List<ItemInvoiceSalesDraft> iteminvoicesalesdrafts){
        iteminvoicesalesdraftRepository.deleteAll(iteminvoicesalesdrafts);
    }
    
    public void deleteById(Integer id){
        iteminvoicesalesdraftRepository.deleteById(id);
    }
    
    public List<ItemInvoiceSalesDraft> findByInvoicesalesdraft(InvoiceSalesDraft invoicesalesdraft){
        return iteminvoicesalesdraftRepository.findByInvoicesalesdraft(invoicesalesdraft, Sort.by(Sort.Direction.ASC, "id"));
    }
    
    public Long deleteByInvoicesalesdraft(InvoiceSalesDraft invoicesalesdraft){
        return iteminvoicesalesdraftRepository.deleteByInvoicesalesdraft(invoicesalesdraft);
    }
}
