/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
@MappedSuperclass
public class InternalInvoiceDraft extends Nontransactional{
    
    @Column(name="draftid", columnDefinition="varchar(50)")
    protected String draftid;
    
    @ManyToOne
    @JoinColumn(name="userid")
    protected User user;
    
    @ManyToOne
    @JoinColumn(name = "numberingid")
    protected Numbering numbering;
    
    @ManyToOne
    @JoinColumn(name = "warehouseid")
    protected Warehouse warehouse;
    
    @Column(name="transactiondate")
    protected LocalDateTime transactiondate;
    
    @Column(name="invoicedate")
    protected LocalDateTime invoicedate;
}
