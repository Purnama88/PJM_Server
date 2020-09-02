/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Brand;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface BrandRepository extends JpaRepository<Brand, Integer>, PagingAndSortingRepository<Brand, Integer> {
    
    Page<Brand> findByNameContaining(String name, Pageable pageable);
    
    List<Brand> findByStatusFalse(Sort sort);
    
    List<Brand> findByStatusTrue(Sort sort);
}