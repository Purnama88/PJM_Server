/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.ReturnPurchase;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ReturnPurchasePagination extends Pagination{
    private List<ReturnPurchase> returnpurchases;
    
    public ReturnPurchasePagination(int totalpages, List<ReturnPurchase> returnpurchases){
        this.totalpages = totalpages;
        this.returnpurchases = returnpurchases;
    }
}
