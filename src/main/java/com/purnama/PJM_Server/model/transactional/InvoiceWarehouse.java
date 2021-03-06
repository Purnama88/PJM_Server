/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional;

import com.purnama.PJM_Server.model.InternalInvoice;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import javax.persistence.Column;
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
@Table(name="invoicewarehouse")
public class InvoiceWarehouse extends InternalInvoice{
    
    @ManyToOne
    @JoinColumn(name="destinationid")
    private Warehouse destination;
    
    @Column(name="destinationcode")
    private String destinationcode;
    
    @Column(name="shippingnumber")
    private String shippingnumber;
}
