/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ReturnSalesDraftPagination extends Pagination{
    private List<ReturnSalesDraft> returnsalesdrafts;
    
    public ReturnSalesDraftPagination(int totalpages, List<ReturnSalesDraft> returnsalesdrafts){
        this.totalpages = totalpages;
        this.returnsalesdrafts = returnsalesdrafts;
    }
}
