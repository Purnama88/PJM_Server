/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Partner;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface PartnerRepository extends JpaRepository<Partner, Integer>, PagingAndSortingRepository<Partner, Integer> {
    
    Page<Partner> findByCodeContainingOrNameContainingOrContactnameContaining(String code, String name, String contactname, Pageable pageable);
    
    List<Partner> findByCodeContainingOrNameContaining(String code, String name, Sort sort);
    
    List<Partner> findByStatus(boolean status, Sort sort);
    
    List<Partner> findByCustomerAndStatus (boolean customer, boolean status, Sort sort);
    
    List<Partner> findBySupplierAndStatus (boolean supplier, boolean status, Sort sort);
    
    List<Partner> findByNontradeAndStatus (boolean nontrade, boolean status, Sort sort);
    
    Optional<Partner> findByCode(String code);
}
