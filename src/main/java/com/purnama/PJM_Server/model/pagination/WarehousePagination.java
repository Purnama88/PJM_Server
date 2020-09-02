/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */

@Data
public class WarehousePagination extends Pagination{
    
    private List<Warehouse> warehouses;
    
    public WarehousePagination(int totalpages, List<Warehouse> warehouses){
        this.totalpages = totalpages;
        this.warehouses = warehouses;
    }
}
