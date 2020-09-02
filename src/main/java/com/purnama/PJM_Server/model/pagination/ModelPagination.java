/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Model;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ModelPagination extends Pagination{
    private List<Model> models;
    
    public ModelPagination(int totalpages, List<Model> models){
        this.totalpages = totalpages;
        this.models = models;
    }
}
