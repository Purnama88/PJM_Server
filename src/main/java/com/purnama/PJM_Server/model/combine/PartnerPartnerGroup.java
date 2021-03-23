/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.combine;

import com.purnama.PJM_Server.model.nontransactional.Partner;
import com.purnama.PJM_Server.model.nontransactional.PartnerGroup;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Entity
@Data
@Table(name="partnerpartnergroup",
        uniqueConstraints = {@UniqueConstraint(columnNames = 
                {"partnerid", "partnergroupid"})})

public class PartnerPartnerGroup implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="partnerid")
    private Partner partner;
    
    @ManyToOne
    @JoinColumn(name="partnergroupid")
    private PartnerGroup partnergroup;
}
