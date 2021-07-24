/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.transactional.InvoicePurchase;
import com.purnama.PJM_Server.model.transactional.ItemInvoicePurchase;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface ItemInvoicePurchaseRepository extends JpaRepository<ItemInvoicePurchase, Integer>, PagingAndSortingRepository<ItemInvoicePurchase, Integer> { 
    List<ItemInvoicePurchase> findByInvoicepurchase(InvoicePurchase invoicepurchase, Sort sort);
}
