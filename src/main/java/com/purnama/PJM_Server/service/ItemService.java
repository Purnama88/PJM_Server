/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.model.nontransactional.Label;
import com.purnama.PJM_Server.repository.ItemRepository;
import com.purnama.PJM_Server.repository.LabelRepository;
import com.purnama.PJM_Server.util.GenericSpesification;
import com.purnama.PJM_Server.util.SearchCriteria;
import com.purnama.PJM_Server.util.SearchOperation;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final LabelRepository labelRepository;
    
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
    
    public Optional<Item> findById(Integer id){
        return itemRepository.findById(id);
    }
    
    public Item save(Item item){
        return itemRepository.save(item);
    }
    
    public void deleteById(Integer id){
        itemRepository.deleteById(id);
    }
    
    public Page<Item> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return itemRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<Item> findByCode(String code){
        return itemRepository.findByCode(code);
    }
    
    public Page<Item> findItemsWithPredicate(int page, int size){
        Label label = labelRepository.findById(1).get();
        
        GenericSpesification genericSpesification = new GenericSpesification<Item>();
        genericSpesification.add(new SearchCriteria("code", "48500", SearchOperation.MATCH));
        genericSpesification.add(new SearchCriteria("description", "gear box", SearchOperation.MATCH));
        genericSpesification.add(new SearchCriteria("label", label.getId(), SearchOperation.EQUAL));

        
        
        return itemRepository.findAll(genericSpesification, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
}