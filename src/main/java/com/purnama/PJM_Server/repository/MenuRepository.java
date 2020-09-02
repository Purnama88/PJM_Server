/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Menu;
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
public interface MenuRepository extends JpaRepository<Menu, Integer>, PagingAndSortingRepository<Menu, Integer> {
    
    Page<Menu> findByNameContaining(String name, Pageable pageable);
    
    List<Menu> findByStatusTrue(Sort sort);
    
    List<Menu> findByStatusFalse(Sort sort);
}
