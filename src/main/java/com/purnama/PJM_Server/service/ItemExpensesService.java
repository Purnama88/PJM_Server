/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.Expenses;
import com.purnama.PJM_Server.model.transactional.ItemExpenses;
import com.purnama.PJM_Server.repository.ItemExpensesRepository;
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
public class ItemExpensesService {
    private final ItemExpensesRepository itemexpensesRepository;
    
    public List<ItemExpenses> findAll(){
        return itemexpensesRepository.findAll();
    }
    
    public Optional<ItemExpenses> findById(Integer id){
        return itemexpensesRepository.findById(id);
    }
    
    public ItemExpenses save(ItemExpenses itemexpenses){
        return itemexpensesRepository.save(itemexpenses);
    }
    
    public void deleteById(Integer id){
        itemexpensesRepository.deleteById(id);
    }
    
    public List<ItemExpenses> findByExpenses(Expenses expenses){
        return itemexpensesRepository.findByExpenses(expenses, Sort.by(Sort.Direction.ASC, "id"));
    }
}
