/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import com.purnama.PJM_Server.repository.ReturnSalesDraftRepository;
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
public class ReturnSalesDraftService {
    
    private final ReturnSalesDraftRepository returnsalesdraftRepository;
    
    public List<ReturnSalesDraft> findAll(){
        return returnsalesdraftRepository.findAll();
    }
    
    public Optional<ReturnSalesDraft> findById(Integer id){
        return returnsalesdraftRepository.findById(id);
    }
    
    public ReturnSalesDraft save(ReturnSalesDraft returnsalesdraft){
        return returnsalesdraftRepository.save(returnsalesdraft);
    }
    
    public void deleteById(Integer id){
        returnsalesdraftRepository.deleteById(id);
    }
    
    public Page<ReturnSalesDraft> findByDraftidContaining(String draftid, int page, int size){
        return returnsalesdraftRepository.findByDraftidContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "transactiondate")));
    }
    
}