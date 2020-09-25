/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional.draft;

import com.purnama.PJM_Server.model.InternalInvoiceDraft;
import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.Partner;
import java.time.LocalDateTime;
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
@Table(name="expensesdraft")
public class ExpensesDraft extends InternalInvoiceDraft{
    
    @Column(name="invoicedate")
    protected LocalDateTime duedate;
    
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
    
    @Column(name="rate", columnDefinition="Decimal(16, 4)")
    private double rate;
    
    @ManyToOne
    @JoinColumn(name = "currencyid")
    private Currency currency;
    
    @ManyToOne
    @JoinColumn(name = "partnerid")
    private Partner partner;
    
}
