/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional.draft;

import com.purnama.PJM_Server.model.InternalInvoiceDraft;
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
@Table(name="deliverydraft")
public class DeliveryDraft extends InternalInvoiceDraft{
    
    @Column(name="destination")
    private String destination;
    
    @Column(name="vehicletype", columnDefinition="varchar(25)")
    private String vehicletype;
    
    @Column(name="vehiclecode", columnDefinition="varchar(25)")
    private String vehiclecode;
    
}
