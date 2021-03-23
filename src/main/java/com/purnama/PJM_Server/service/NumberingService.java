/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Menu;
import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.repository.NumberingRepository;
import com.purnama.PJM_Server.util.GenericSpecificationsBuilder;
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
public class NumberingService {
    
    private final NumberingRepository numberingRepository;
    
    public List<Numbering> findAll(){
        return numberingRepository.findAll();
    }
    
    public Optional<Numbering> findById(Integer id){
        return numberingRepository.findById(id);
    }
    
    public Numbering save(Numbering numbering){
        return numberingRepository.save(numbering);
    }
    
    public void deleteById(Integer id){
        numberingRepository.deleteById(id);
    }
    
    public Page<Numbering> findByNamePrefixMenuWithPredicate(String keyword, int menuid, int page, int size){
        GenericSpecificationsBuilder genericSpesification = new GenericSpecificationsBuilder<Numbering>();
        genericSpesification.with("name", SearchOperation.MATCH, true, keyword);
        genericSpesification.with("prefix", SearchOperation.MATCH, true, keyword);
        genericSpesification.with("menu", SearchOperation.EQUAL, false, menuid);
        
        return numberingRepository.findAll(genericSpesification.build(), PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public List<Numbering> findByMenuAndStatus(Menu menu, boolean status){
        return numberingRepository.findByMenuAndStatus(menu, status, Sort.by(Sort.Direction.ASC, "name"));
    }
}