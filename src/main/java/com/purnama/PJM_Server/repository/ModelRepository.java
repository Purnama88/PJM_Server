/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Brand;
import com.purnama.PJM_Server.model.nontransactional.Model;
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
public interface ModelRepository extends JpaRepository<Model, Integer>, PagingAndSortingRepository<Model, Integer> {
    
    Page<Model> findByNameContaining(String name, Pageable pageable);
    
    Optional<Model> findByName(String Name);
    
    List<Model> findByStatusTrue(Sort sort);
    
    List<Model> findByStatusFalse(Sort sort);
    
    List<Model> findByBrandAndStatus(Brand brand, boolean status, Sort sort);
}