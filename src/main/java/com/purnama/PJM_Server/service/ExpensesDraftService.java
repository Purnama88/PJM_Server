/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import com.purnama.PJM_Server.repository.ExpensesDraftRepository;
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
public class ExpensesDraftService {
    
    private final ExpensesDraftRepository expensesdraftRepository;
    
    public List<ExpensesDraft> findAll(){
        return expensesdraftRepository.findAll();
    }
    
    public Optional<ExpensesDraft> findById(Integer id){
        return expensesdraftRepository.findById(id);
    }
    
    public ExpensesDraft save(ExpensesDraft expensesdraft){
        return expensesdraftRepository.save(expensesdraft);
    }
    
    public void deleteById(Integer id){
        expensesdraftRepository.deleteById(id);
    }
    
    public Page<ExpensesDraft> findByDraftidContaining(String draftid, int page, int size){
        return expensesdraftRepository.findByDraftidContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
    
}