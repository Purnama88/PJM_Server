/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional.draft;

import com.purnama.PJM_Server.model.InternalItemInvoiceDraft;
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
@Table(name="itemdeliverydraft")
public class ItemDeliveryDraft extends InternalItemInvoiceDraft{
    
    @Column(name="quantity", columnDefinition="varchar(50)")
    private String quantity;
    
    @Column(name="remark", columnDefinition="varchar(100)")
    private String remark;
    
    @ManyToOne
    @JoinColumn(name = "deliveryid")
    private DeliveryDraft deliverydraft;
    
}
