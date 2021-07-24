/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemExpensesDraft;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface ItemExpensesDraftRepository extends JpaRepository<ItemExpensesDraft, Integer>, PagingAndSortingRepository<ItemExpensesDraft, Integer> { 
    
    List<ItemExpensesDraft> findByExpensesdraft(ExpensesDraft expensesdraft, Sort sort);
    
    Long deleteByExpensesdraft(ExpensesDraft expensesdraft);
}
