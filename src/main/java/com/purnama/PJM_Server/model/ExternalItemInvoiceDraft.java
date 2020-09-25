/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

import com.purnama.PJM_Server.model.nontransactional.Item;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ExternalItemInvoiceDraft implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    protected int id;
    
    @ManyToOne
    @JoinColumn(name="itemid")
    protected Item item;
    
    @Column(name="code", columnDefinition="varchar(25)")
    protected String code;
    
    @Column(name="description", columnDefinition="varchar(150)")
    protected String description;
    
    @Column(name="quantity", columnDefinition="Decimal(16, 4)")
    protected double quantity;
    
    @Column(name="price", columnDefinition="Decimal(16, 4)")
    protected double price;
    
    @Column(name="discount", columnDefinition="Decimal(16, 4)")
    protected double discount;
    
    @Column(name="box", columnDefinition="varchar(25)")
    protected String box;
}

