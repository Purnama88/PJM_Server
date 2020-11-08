 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.nontransactional.Partner;
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
public class ExternalInvoiceDraft extends Nontransactional{
    
    @Column(name="draftid", columnDefinition="varchar(25)")
    protected String draftid;
    
    @ManyToOne
    @JoinColumn(name="numberingid")
    protected Numbering numbering;
    
    @ManyToOne
    @JoinColumn(name="userid")
    protected User user;
    
    @ManyToOne
    @JoinColumn(name="warehouseid")
    protected Warehouse warehouse;
    
    @ManyToOne
    @JoinColumn(name="currencyid")
    protected Currency currency;
    
    @ManyToOne
    @JoinColumn(name="partnerid")
    protected Partner partner;
    
    @Column(name="invoicedate")
    protected LocalDateTime invoicedate;
    
    @Column(name="duedate")
    protected LocalDateTime duedate;
    
    @Column(name="subtotal", columnDefinition="Decimal(16,4)")
    protected double subtotal;
    
    @Column(name="discount", columnDefinition="Decimal(16,4)")
    protected double discount;
    
    @Column(name="tax", columnDefinition="Decimal(16,4)")
    protected double tax;
    
    @Column(name="freight", columnDefinition="Decimal(16,4)")
    protected double freight;
    
    @Column(name="rounding", columnDefinition="Decimal(16,4)")
    protected double rounding;
    
    @Column(name="rate", columnDefinition="Decimal(16,4)")
    protected double rate;
}
