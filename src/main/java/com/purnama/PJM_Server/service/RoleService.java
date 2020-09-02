/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Role;
import com.purnama.PJM_Server.repository.RoleRepository;
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
public class RoleService {
    
    private final RoleRepository roleRepository;
    
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
    
    public Optional<Role> findById(Integer id){
        return roleRepository.findById(id);
    }
    
    public Role save(Role role){
        return roleRepository.save(role);
    }
    
    public void deleteById(Integer id){
        roleRepository.deleteById(id);
    }
    
    public Page<Role> findByNameContaining(String name, int page, int size){
        return roleRepository.findByNameContaining(name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public List<Role> findByStatusTrue(){
        return roleRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Role> findByStatusFalse(){
        return roleRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public Optional<Role> findByName(String code){
        return roleRepository.findByName(code);
    }
}
