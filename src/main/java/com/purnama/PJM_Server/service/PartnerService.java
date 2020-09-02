/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.repository.PartnerRepository;
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
public class PartnerService {
    private final PartnerRepository partnerRepository;
    
    public List<Partner> findAll(){
        return partnerRepository.findAll();
    }
    
    public Optional<Partner> findById(Integer id){
        return partnerRepository.findById(id);
    }
    
    public Partner save(Partner partner){
        return partnerRepository.save(partner);
    }
    
    public void deleteById(Integer id){
        partnerRepository.deleteById(id);
    }
    
    public Page<Partner> findByCodeContainingOrNameContainingOrContactnameContaining(String code, String name, String contactname, int page, int size){
        return partnerRepository.findByCodeContainingOrNameContainingOrContactnameContaining(code, name, contactname, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "name")));
    }
    
    public Optional<Partner> findByCode(String code){
        return partnerRepository.findByCode(code);
    }
    
    public List<Partner> findByStatus(boolean status){
        return partnerRepository.findByStatus(status, Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Partner> findByCustomerAndStatus(boolean customer, boolean status){
        return partnerRepository.findByCustomerAndStatus(customer, status, Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Partner> findBySupplierAndStatus(boolean supplier, boolean status){
        return partnerRepository.findBySupplierAndStatus(supplier, status, Sort.by(Sort.Direction.ASC, "name"));
    }
    
    public List<Partner> findByNontradeAndStatus(boolean nontrade, boolean status){
        return partnerRepository.findByNontradeAndStatus(nontrade, status, Sort.by(Sort.Direction.ASC, "name"));
    }
}