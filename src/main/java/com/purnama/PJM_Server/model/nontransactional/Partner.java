/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.nontransactional;

import com.purnama.PJM_Server.model.Nontransactional;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author p_cor
 */

@Entity
@Data
@Table(name="partner")
public class Partner extends Nontransactional{
    
    @Column(name="code", unique=true, columnDefinition="varchar(25)")
    private String code;
    
    @Column(name="name", columnDefinition="varchar(50)")
    private String name;
    
    @Column(name="contactname", columnDefinition="varchar(50)")
    private String contactname;
    
    @Column(name="address")
    private String address;
    
    @Column(name="phonenumber", columnDefinition="varchar(25)")
    private String phonenumber;
    
    @Column(name="phonenumber2", columnDefinition="varchar(25)")
    private String phonenumber2;
    
    @Column(name="faxnumber", columnDefinition="varchar(25)")
    private String faxnumber;
    
    @Column(name="faxnumber2", columnDefinition="varchar(25)")
    private String faxnumber2;
    
    @Column(name="mobilenumber", columnDefinition="varchar(25)")
    private String mobilenumber;
    
    @Column(name="mobilenumber2", columnDefinition="varchar(25)")
    private String mobilenumber2;
    
    @Column(name="email", columnDefinition="varchar(50)")
    private String email;
    
    @Column(name="email2", columnDefinition="varchar(50)")
    private String email2;
    
    @Column(name="balance", columnDefinition="Decimal(16, 4)")
    private double balance;
    
    @Column(name="maximumdiscount", columnDefinition="Decimal(16, 4)")
    private double maximumdiscount;
    
    @Column(name="maximumbalance", columnDefinition="Decimal(16, 4)")
    private double maximumbalance;
    
    @Column(name="paymentdue")
    private int paymentdue;
    
    @Column(name="customer", nullable = false, columnDefinition="boolean default true")
    private boolean customer;
    
    @Column(name="supplier", nullable = false, columnDefinition="boolean default true")
    private boolean supplier;
    
    @Column(name="nontrade", nullable = false, columnDefinition="boolean default true")
    private boolean nontrade;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "itempartnergroup", 
        joinColumns = { @JoinColumn(name = "itemid") }, 
        inverseJoinColumns = { @JoinColumn(name = "partnergroupid") }
    )
    Set<PartnerGroup> partnergroups = new HashSet<>();
    
}
