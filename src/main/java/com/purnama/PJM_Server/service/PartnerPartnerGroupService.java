/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.combine.PartnerPartnerGroup;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.nontransactional.PartnerGroup;
import com.purnama.PJM_Server.repository.PartnerPartnerGroupRepository;
import com.purnama.PJM_Server.repository.PartnerRepository;
import com.purnama.PJM_Server.repository.PartnerGroupRepository;
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
public class PartnerPartnerGroupService {
    private final PartnerPartnerGroupRepository partnerpartnergroupRepository;
    private final PartnerRepository partnerRepository;
    private final PartnerGroupRepository partnergroupRepository;
    
    public List<PartnerPartnerGroup> findAll(){
        return partnerpartnergroupRepository.findAll();
    }
    
    public Optional<PartnerPartnerGroup> findById(Integer id){
        return partnerpartnergroupRepository.findById(id);
    }
    
    public PartnerPartnerGroup save(PartnerPartnerGroup partnerpartnergroup){
        return partnerpartnergroupRepository.save(partnerpartnergroup);
    }
    
    public void deleteById(Integer id){
        partnerpartnergroupRepository.deleteById(id);
    }
    
    public List<PartnerPartnerGroup> findByPartner(int id){
        Partner partner = partnerRepository.findById(id).get();
        
        return partnerpartnergroupRepository.findByPartner(partner);
    }
    
    public List<PartnerPartnerGroup> findByPartnerGroup(int id){
        PartnerGroup partnergroup = partnergroupRepository.findById(id).get();
        
        return partnerpartnergroupRepository.findByPartnergroup(partnergroup);
    }
}