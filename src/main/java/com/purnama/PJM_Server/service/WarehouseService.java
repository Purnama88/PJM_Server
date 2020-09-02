/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.repository.WarehouseRepository;
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
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    
    public List<Warehouse> findAll(){
        return warehouseRepository.findAll();
    }
    
    public Optional<Warehouse> findById(Integer id){
        return warehouseRepository.findById(id);
    }
    
    public Warehouse save(Warehouse warehouse){
        return warehouseRepository.save(warehouse);
    }
    
    public void deleteById(Integer id){
        warehouseRepository.deleteById(id);
    }
    
    public Page<Warehouse> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return warehouseRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<Warehouse> findByCode(String code){
        return warehouseRepository.findByCode(code);
    }
    
    public List<Warehouse> findByStatusTrue(){
        return warehouseRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Warehouse> findByStatusFalse(){
        return warehouseRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
}