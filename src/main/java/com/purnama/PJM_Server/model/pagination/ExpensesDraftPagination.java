/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.draft.ExpensesDraft;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ExpensesDraftPagination extends Pagination{
    private List<ExpensesDraft> expensesdrafts;
    
    public ExpensesDraftPagination(int totalpages, List<ExpensesDraft> expensesdrafts){
        this.totalpages = totalpages;
        this.expensesdrafts = expensesdrafts;
    }
}
