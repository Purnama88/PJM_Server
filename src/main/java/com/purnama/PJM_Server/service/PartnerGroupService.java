/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.PartnerGroup;
import com.purnama.PJM_Server.repository.PartnerGroupRepository;
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
public class PartnerGroupService {
    private final PartnerGroupRepository partnergroupRepository;
    
    public List<PartnerGroup> findAll(){
        return partnergroupRepository.findAll();
    }
    
    public Optional<PartnerGroup> findById(Integer id){
        return partnergroupRepository.findById(id);
    }
    
    public PartnerGroup save(PartnerGroup partnergroup){
        return partnergroupRepository.save(partnergroup);
    }
    
    public void deleteById(Integer id){
        partnergroupRepository.deleteById(id);
    }
    
    public Page<PartnerGroup> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return partnergroupRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<PartnerGroup> findByCode(String code){
        return partnergroupRepository.findByCode(code);
    }
    
    public List<PartnerGroup> findByStatusTrue(){
        return partnergroupRepository.findByStatusTrue(Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<PartnerGroup> findByStatusFalse(){
        return partnergroupRepository.findByStatusFalse(Sort.by(Sort.Direction.ASC, "name"));
    }
    
}