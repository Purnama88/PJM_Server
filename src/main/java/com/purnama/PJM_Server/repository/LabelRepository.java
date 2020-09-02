/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Label;
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
public interface LabelRepository extends JpaRepository<Label, Integer>, PagingAndSortingRepository<Label, Integer> {
    
    Page<Label> findByCodeContainingOrNameContaining(String code, String name, Pageable pageable);
    
    Optional<Label> findByCode(String code);
    
    List<Label> findByStatusTrue(Sort sort);
    
    List<Label> findByStatusFalse(Sort sort);
}
