/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
import com.purnama.PJM_Server.repository.ItemGroupRepository;
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
public class ItemGroupService {
    private final ItemGroupRepository itemgroupRepository;
    
    public List<ItemGroup> findAll(){
        return itemgroupRepository.findAll();
    }
    
    public Optional<ItemGroup> findById(Integer id){
        return itemgroupRepository.findById(id);
    }
    
    public ItemGroup save(ItemGroup itemgroup){
        return itemgroupRepository.save(itemgroup);
    }
    
    public void deleteById(Integer id){
        itemgroupRepository.deleteById(id);
    }
    
    public Page<ItemGroup> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return itemgroupRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<ItemGroup> findByCode(String code){
        return itemgroupRepository.findByCode(code);
    }
    
    public List<ItemGroup> findByStatusTrue(){
        return itemgroupRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<ItemGroup> findByStatusFalse(){
        return itemgroupRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
}