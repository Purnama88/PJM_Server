/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional.draft;

import com.purnama.PJM_Server.model.InternalInvoiceDraft;
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
@Table(name="invoicewarehousedraft")
public class InvoiceWarehouseDraft extends InternalInvoiceDraft{
    
    @ManyToOne
    @JoinColumn(name="destinationid")
    private Warehouse destination;
    
    @Column(name="destinationcode", columnDefinition="varchar(25)")
    private String destinationcode;
    
    @Column(name="shippingnumber", columnDefinition="varchar(25)")
    private String shippingnumber;
    
}
