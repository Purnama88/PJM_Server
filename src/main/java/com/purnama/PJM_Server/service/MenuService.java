/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Menu;
import com.purnama.PJM_Server.repository.MenuRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    
    public List<Menu> findAll(){
        return menuRepository.findAll();
    }
    
    public Optional<Menu> findById(Integer id){
        return menuRepository.findById(id);
    }
    
    public Menu save(Menu menu){
        return menuRepository.save(menu);
    }
    
    public void deleteById(Integer id){
        menuRepository.deleteById(id);
    }
    
    public Page<Menu> findByNameContaining(String name, int page, int size){
        return menuRepository.findByNameContaining(name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public List<Menu> findByStatusTrue(){
        return menuRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Menu> findByStatusFalse(){
        return menuRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
}
