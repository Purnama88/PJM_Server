/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.Formula;

/**
 *
 * @author p_cor
 */

@Data
@MappedSuperclass
public class ExternalInvoice extends Transactional{
    
    @Column(name="duedate")
    protected LocalDateTime duedate;
    
    @Column(name="currencycode")
    protected String currencycode;
    
    @Column(name="currencyname")
    protected String currencyname;
    
    @Column(name="partnercode")
    protected String partnercode;
    
    @Column(name="partnername")
    protected String partnername;
    
    @Column(name="partneraddress")
    protected String partneraddress;
    
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
    
    @Column(name="paid", columnDefinition="Decimal(16,4)")
    protected double paid;
    
    @Column(name="rate", columnDefinition="Decimal(16,4)")
    protected double rate;
    
    @ManyToOne
    @JoinColumn(name="partnerid")
    protected Partner partner;
    
    @ManyToOne
    @JoinColumn(name="currencyid")
    protected Currency currency;
    
    @Formula("subtotal - discount - rounding + freight + tax - paid")
    protected double remaining;
    
    @Formula("subtotal - discount - rounding + freight")
    protected double totalbeforetax;
    
    @Formula("subtotal - discount - rounding + freight + tax")
    protected double totalaftertax;
    
    @Formula("(subtotal - discount - rounding + freight + tax) * rate")
    protected double totaldefaultcurrency;
}
