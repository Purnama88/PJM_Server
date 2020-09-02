/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional;

import com.purnama.PJM_Server.model.InternalInvoice;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Entity
@Data
@Table(name="delivery")
public class Delivery extends InternalInvoice{
    
    @Column(name="destination")
    private String destination;
    
    @Column(name="vehicletype")
    private String vehicletype;
    
    @Column(name="vehiclecode")
    private String vehiclecode;
    
}
