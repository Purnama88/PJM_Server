/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional;

import com.purnama.PJM_Server.model.ExternalItemInvoice;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Entity
@Data
@Table(name="iteminvoicesales")
public class ItemInvoiceSales extends ExternalItemInvoice{
    @ManyToOne
    @JoinColumn(name = "invoiceid")
    private InvoiceSales invoicesales;
}
