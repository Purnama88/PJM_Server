/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.InvoiceSales;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class InvoiceSalesPagination extends Pagination{
    private List<InvoiceSales> invoicesales;
    
    public InvoiceSalesPagination(int totalpages, List<InvoiceSales> invoicesales){
        this.totalpages = totalpages;
        this.invoicesales = invoicesales;
    }
}

