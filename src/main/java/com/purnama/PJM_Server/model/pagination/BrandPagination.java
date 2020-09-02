/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Brand;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class BrandPagination extends Pagination{
    private List<Brand> brands;
    
    public BrandPagination(int totalpages, List<Brand> brands){
        this.totalpages = totalpages;
        this.brands = brands;
    }
}
