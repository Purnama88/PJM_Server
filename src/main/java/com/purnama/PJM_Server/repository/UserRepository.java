/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author p_cor
 */
public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
    
    Optional<User> findByUsernameAndPassword(String username, String password);
    
    Optional<User> findByUsername(String username);
    
    Optional<User> findByCode(String code);
    
    Page<User> findByCodeContainingOrNameContaining(String code, String name, Pageable pageable);
    
}
