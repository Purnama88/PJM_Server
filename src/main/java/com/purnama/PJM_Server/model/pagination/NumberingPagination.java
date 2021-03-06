/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class NumberingPagination extends Pagination{
    private List<Numbering> numberings;
    
    public NumberingPagination(int totalpages, List<Numbering> numberings){
        this.totalpages = totalpages;
        this.numberings = numberings;
    }
}
