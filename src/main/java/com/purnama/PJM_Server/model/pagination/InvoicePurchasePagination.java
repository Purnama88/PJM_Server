/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.InvoicePurchase;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class InvoicePurchasePagination extends Pagination{
    private List<InvoicePurchase> invoicepurchases;
    
    public InvoicePurchasePagination(int totalpages, List<InvoicePurchase> invoicepurchases){
        this.totalpages = totalpages;
        this.invoicepurchases = invoicepurchases;
    }
}
