/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemExpensesDraft;
import com.purnama.PJM_Server.repository.ItemExpensesDraftRepository;
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
public class ItemExpensesDraftService {
    private final ItemExpensesDraftRepository itemexpensesdraftRepository;
    
    public List<ItemExpensesDraft> findAll(){
        return itemexpensesdraftRepository.findAll();
    }
    
    public Optional<ItemExpensesDraft> findById(Integer id){
        return itemexpensesdraftRepository.findById(id);
    }
    
    public ItemExpensesDraft save(ItemExpensesDraft brand){
        return itemexpensesdraftRepository.save(brand);
    }
    
    public void deleteById(Integer id){
        itemexpensesdraftRepository.deleteById(id);
    }
    
    public List<ItemExpensesDraft> findByExpensesdraft(ExpensesDraft expensesdraft){
        return itemexpensesdraftRepository.findByExpensesdraft(expensesdraft, Sort.by(Sort.Direction.ASC, "id"));
    }
}
