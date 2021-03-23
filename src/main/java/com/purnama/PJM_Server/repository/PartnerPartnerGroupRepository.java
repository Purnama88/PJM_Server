/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.combine.PartnerPartnerGroup;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.nontransactional.PartnerGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author p_cor
 */
public interface PartnerPartnerGroupRepository extends JpaRepository<PartnerPartnerGroup, Integer>{
    
    List<PartnerPartnerGroup> findByPartner(Partner partner);
    
    List<PartnerPartnerGroup> findByPartnergroup(PartnerGroup partnergroup);
}
