/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.Expenses;
import com.purnama.PJM_Server.repository.ExpensesRepository;
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
public class ExpensesService {
    
    private final ExpensesRepository expensesRepository;
    
    public List<Expenses> findAll(){
        return expensesRepository.findAll();
    }
    
    public Optional<Expenses> findById(Integer id){
        return expensesRepository.findById(id);
    }
    
    public Expenses save(Expenses expenses){
        return expensesRepository.save(expenses);
    }
    
    public void deleteById(Integer id){
        expensesRepository.deleteById(id);
    }
    
    public Page<Expenses> findByNumberContaining(String draftid, int page, int size){
        return expensesRepository.findByNumberContaining(draftid, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "invoicedate")));
    }
}
