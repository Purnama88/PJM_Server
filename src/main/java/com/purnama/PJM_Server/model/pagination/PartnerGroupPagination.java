/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.PartnerGroup;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class PartnerGroupPagination extends Pagination{
    
    private List<PartnerGroup> partnergroups;
    
    public PartnerGroupPagination(int totalpages, List<PartnerGroup> partnergroups){
        this.totalpages = totalpages;
        this.partnergroups = partnergroups;
    }
}
