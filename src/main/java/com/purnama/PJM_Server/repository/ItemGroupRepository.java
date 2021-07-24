/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
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
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Integer>, PagingAndSortingRepository<ItemGroup, Integer> {
    
    Page<ItemGroup> findByCodeContainingOrNameContaining(String code, String name, Pageable pageable);
    
    List<ItemGroup> findByCodeContainingOrNameContaining(String code, String name, Sort sort);
    
    List<ItemGroup> findByStatusTrue(Sort sort);
    
    List<ItemGroup> findByStatusFalse(Sort sort);
    
    Optional<ItemGroup> findByCode(String code);
}
