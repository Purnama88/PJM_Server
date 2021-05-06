/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface ReturnPurchaseRepository extends JpaRepository<ReturnPurchase, Integer>, PagingAndSortingRepository<ReturnPurchase, Integer>  {
    
    Page<ReturnPurchase> findByNumberContaining(String number, Pageable pageable);
    
}
