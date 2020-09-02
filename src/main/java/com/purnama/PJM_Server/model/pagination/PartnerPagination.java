/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Partner;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class PartnerPagination extends Pagination{
    private List<Partner> partners;
    
    public PartnerPagination(int totalpages, List<Partner> partners){
        this.totalpages = totalpages;
        this.partners = partners;
    }
}
