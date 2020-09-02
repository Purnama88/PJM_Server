/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.draft.InvoicePurchaseDraft;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class InvoicePurchaseDraftPagination extends Pagination{
    private List<InvoicePurchaseDraft> invoicepurchasedrafts;
    
    public InvoicePurchaseDraftPagination(int totalpages, List<InvoicePurchaseDraft> invoicepurchasedrafts){
        this.totalpages = totalpages;
        this.invoicepurchasedrafts = invoicepurchasedrafts;
    }
}
