/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional;

import com.purnama.PJM_Server.model.InternalInvoice;
import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Formula;

/**
 *
 * @author p_cor
 */
@Entity
@Data
@Table(name="expenses")
public class Expenses extends InternalInvoice{
    
    @Column(name="duedate")
    protected LocalDateTime duedate;
    
    @ManyToOne
    @JoinColumn(name = "currencyid")
    private Currency currency;
    
    @Column(name="rate")
    private double rate;
    
    @Column(name="subtotal", columnDefinition="Decimal(16, 4)")
    private double subtotal;
    
    @Column(name="discount", columnDefinition="Decimal(16, 4)")
    private double discount;
    
    @Column(name="tax", columnDefinition="Decimal(16, 4)")
    private double tax;
    
    @Column(name="freight", columnDefinition="Decimal(16, 4)")
    private double freight;
    
    @Column(name="rounding", columnDefinition="Decimal(16, 4)")
    private double rounding;
    
    @ManyToOne
    @JoinColumn(name = "partnerid")
    private Partner partner;
    
    @Column(name="currencycode")
    private String currencycode;
    
    @Column(name="currencyname")
    private String currencyname;
    
    @Column(name="partnername")
    private String partnername;
    
    @Column(name="partnercode")
    private String partnercode;
    
    @Column(name="partneraddress")
    private String partneraddress;
    
    @Column(name="paid", columnDefinition="Decimal(16, 4)")
    private double paid;
    
    @Formula("subtotal - discount - rounding + freight + tax - paid")
    private double remaining;

    @Formula("subtotal - discount - rounding + freight")
    private double totalbeforetax;
    
    @Formula("subtotal - discount - rounding + freight + tax")
    private double totalaftertax;
    
    @Formula("(subtotal - discount - rounding + freight + tax) * rate")
    private double totaldefaultcurrency;
}
