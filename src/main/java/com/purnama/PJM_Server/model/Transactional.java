/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

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
public class Transactional extends Nontransactional{
    
    @ManyToOne
    @JoinColumn(name="warehouseid")
    protected Warehouse warehouse;
    
    @ManyToOne
    @JoinColumn(name="userid")
    protected User user;
    
    @Column(name="number", columnDefinition="varchar(25)")
    protected String number;
    
    @Column(name="invoicedate")
    protected LocalDateTime invoicedate;
    
    @Column(name="printed")
    protected int printed;
    
    @Column(name="draftid", columnDefinition="varchar(25)")
    protected String draftid;
    
    @Column(name="usercode", columnDefinition="varchar(25)")
    protected String usercode;
    
    @Column(name="warehousecode", columnDefinition="varchar(25)")
    protected String warehousecode;
}
