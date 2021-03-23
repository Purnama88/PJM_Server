/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.combine.ItemModel;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.model.nontransactional.Model;
import com.purnama.PJM_Server.repository.ItemModelRepository;
import com.purnama.PJM_Server.repository.ItemRepository;
import com.purnama.PJM_Server.repository.ModelRepository;
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
public class ItemModelService {
    private final ItemModelRepository itemmodelRepository;
    private final ItemRepository itemRepository;
    private final ModelRepository modelRepository;
    
    public List<ItemModel> findAll(){
        return itemmodelRepository.findAll();
    }
    
    public Optional<ItemModel> findById(Integer id){
        return itemmodelRepository.findById(id);
    }
    
    public ItemModel save(ItemModel itemmodel){
        return itemmodelRepository.save(itemmodel);
    }
    
    public void deleteById(Integer id){
        itemmodelRepository.deleteById(id);
    }
    
    public List<ItemModel> findByItem(int id){
        Item item = itemRepository.findById(id).get();
        
        return itemmodelRepository.findByItem(item);
    }
    
    public List<ItemModel> findByModel(int id){
        Model model = modelRepository.findById(id).get();
        
        return itemmodelRepository.findByModel(model);
    }
}
