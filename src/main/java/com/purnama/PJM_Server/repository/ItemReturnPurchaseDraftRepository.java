/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnPurchaseDraft;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface ItemReturnPurchaseDraftRepository extends JpaRepository<ItemReturnPurchaseDraft, Integer>, PagingAndSortingRepository<ItemReturnPurchaseDraft, Integer> { 
    
    List<ItemReturnPurchaseDraft> findByReturnpurchasedraft(ReturnPurchaseDraft returnpurchasedraft, Sort sort);
    
    Long deleteByReturnpurchasedraft(ReturnPurchaseDraft returnpurchasedraft);
}
