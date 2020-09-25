/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface ExpensesDraftRepository extends JpaRepository<ExpensesDraft, Integer>, PagingAndSortingRepository<ExpensesDraft, Integer> {
    
    Page<ExpensesDraft> findByDraftidContaining(String draftid, Pageable pageable);
}
