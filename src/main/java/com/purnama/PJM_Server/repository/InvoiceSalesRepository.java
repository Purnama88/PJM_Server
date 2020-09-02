/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author p_cor
 */
public interface InvoiceSalesRepository extends JpaRepository<InvoiceSales, Integer> {
    
}
