/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.nontransactional.Menu;
import com.purnama.PJM_Server.model.nontransactional.Numbering;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author p_cor
 */
public interface NumberingRepository extends JpaRepository<Numbering, Integer>{
    
    Page<Numbering> findByMenuAndNameContaining(Menu menu, String name, Pageable pageable);
    
    List<Numbering> findByMenuAndStatus(Menu menu, boolean status, Sort sort);
}
