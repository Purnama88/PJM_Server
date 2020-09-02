/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Role;
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
public interface RoleRepository extends JpaRepository<Role, Integer>, PagingAndSortingRepository<Role, Integer> {
    
    Page<Role> findByNameContaining(String name, Pageable pageable);
    
    List<Role> findByStatusTrue(Sort sort);
    
    List<Role> findByStatusFalse(Sort sort);
    
    Optional<Role> findByName(String Name);
}
