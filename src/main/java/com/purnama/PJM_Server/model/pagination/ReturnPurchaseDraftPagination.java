/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.draft.ReturnPurchaseDraft;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ReturnPurchaseDraftPagination extends Pagination{
    private List<ReturnPurchaseDraft> returnpurchasedrafts;
    
    public ReturnPurchaseDraftPagination(int totalpages, List<ReturnPurchaseDraft> returnpurchasedrafts){
        this.totalpages = totalpages;
        this.returnpurchasedrafts = returnpurchasedrafts;
    }
}
