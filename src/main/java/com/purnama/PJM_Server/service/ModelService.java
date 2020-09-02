/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Model;
import com.purnama.PJM_Server.repository.ModelRepository;
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
public class ModelService {
    
    private final ModelRepository modelRepository;
    
    public List<Model> findAll(){
        return modelRepository.findAll();
    }
    
    public Optional<Model> findById(Integer id){
        return modelRepository.findById(id);
    }
    
    public Model save(Model model){
        return modelRepository.save(model);
    }
    
    public void deleteById(Integer id){
        modelRepository.deleteById(id);
    }
    
    public Page<Model> findByNameContaining(String name, int page, int size){
        return modelRepository.findByNameContaining(name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<Model> findByName(String code){
        return modelRepository.findByName(code);
    }
    
    public List<Model> findByStatusTrue(){
        return modelRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Model> findByStatusFalse(){
        return modelRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
}
