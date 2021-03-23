/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.combine.ItemItemGroup;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
import com.purnama.PJM_Server.repository.ItemItemGroupRepository;
import com.purnama.PJM_Server.repository.ItemRepository;
import com.purnama.PJM_Server.repository.ItemGroupRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */
@Service
@RequiredArgsConstructor
public class ItemItemGroupService {
    private final ItemItemGroupRepository itemitemgroupRepository;
    private final ItemRepository itemRepository;
    private final ItemGroupRepository itemgroupRepository;
    
    public List<ItemItemGroup> findAll(){
        return itemitemgroupRepository.findAll();
    }
    
    public Optional<ItemItemGroup> findById(Integer id){
        return itemitemgroupRepository.findById(id);
    }
    
    public ItemItemGroup save(ItemItemGroup itemitemgroup){
        return itemitemgroupRepository.save(itemitemgroup);
    }
    
    public void deleteById(Integer id){
        itemitemgroupRepository.deleteById(id);
    }
    
    public List<ItemItemGroup> findByItem(int id){
        Item item = itemRepository.findById(id).get();
        
        return itemitemgroupRepository.findByItem(item);
    }
    
    public List<ItemItemGroup> findByItemGroup(int id){
        ItemGroup itemgroup = itemgroupRepository.findById(id).get();
        
        return itemitemgroupRepository.findByItemgroup(itemgroup);
    }
}