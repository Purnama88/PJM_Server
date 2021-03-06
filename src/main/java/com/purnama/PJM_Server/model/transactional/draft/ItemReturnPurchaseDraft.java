/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional.draft;

import com.purnama.PJM_Server.model.ExternalItemInvoiceDraft;
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
@Table(name="itemreturnpurchasedraft")
public class ItemReturnPurchaseDraft extends ExternalItemInvoiceDraft{
    
    @ManyToOne
    @JoinColumn(name = "returnid")
    private ReturnPurchaseDraft returnpurchasedraft;
    
}