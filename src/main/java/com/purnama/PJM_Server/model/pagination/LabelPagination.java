/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Label;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class LabelPagination extends Pagination{
    private List<Label> labels;
    
    public LabelPagination(int totalpages, List<Label> labels){
        this.totalpages = totalpages;
        this.labels = labels;
    }
}
