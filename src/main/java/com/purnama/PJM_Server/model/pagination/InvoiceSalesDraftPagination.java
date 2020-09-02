/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.draft.InvoiceSalesDraft;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class InvoiceSalesDraftPagination extends Pagination{
    private List<InvoiceSalesDraft> invoicesalesdrafts;
    
    public InvoiceSalesDraftPagination(int totalpages, List<InvoiceSalesDraft> invoicesalesdrafts){
        this.totalpages = totalpages;
        this.invoicesalesdrafts = invoicesalesdrafts;
    }
}
