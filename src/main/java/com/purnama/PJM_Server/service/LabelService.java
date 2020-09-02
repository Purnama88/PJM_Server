/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Label;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.purnama.PJM_Server.repository.LabelRepository;

/**
 *
 * @author p_cor
 */
@Service
@RequiredArgsConstructor
public class LabelService {
    
    private final LabelRepository labelRepository;
    
    public List<Label> findAll(){
        return labelRepository.findAll();
    }
    
    public Optional<Label> findById(Integer id){
        return labelRepository.findById(id);
    }
    
    public Label save(Label label){
        return labelRepository.save(label);
    }
    
    public void deleteById(Integer id){
        labelRepository.deleteById(id);
    }
    
    public Page<Label> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return labelRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<Label> findByCode(String code){
        return labelRepository.findByCode(code);
    }
    
    public List<Label> findByStatusTrue(){
        return labelRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Label> findByStatusFalse(){
        return labelRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
}
