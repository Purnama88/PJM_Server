/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.ReturnSales;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ReturnSalesPagination extends Pagination{
    private List<ReturnSales> returnsales;
    
    public ReturnSalesPagination(int totalpages, List<ReturnSales> returnsales){
        this.totalpages = totalpages;
        this.returnsales = returnsales;
    }
}
