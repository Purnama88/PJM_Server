/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Brand;
import com.purnama.PJM_Server.repository.BrandRepository;
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
public class BrandService {
    
    private final BrandRepository brandRepository;
    
    public List<Brand> findAll(){
        return brandRepository.findAll();
    }
    
    public Optional<Brand> findById(Integer id){
        return brandRepository.findById(id);
    }
    
    public Brand save(Brand brand){
        return brandRepository.save(brand);
    }
    
    public void deleteById(Integer id){
        brandRepository.deleteById(id);
    }
    
    public Page<Brand> findByNameContaining(String name, int page, int size){
        return brandRepository.findByNameContaining(name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public List<Brand> findByStatusFalse(){
        return brandRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Brand> findByStatusTrue(){
        return brandRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
}
