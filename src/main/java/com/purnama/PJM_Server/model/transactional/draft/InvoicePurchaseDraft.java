/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional.draft;

import com.purnama.PJM_Server.model.ExternalInvoiceDraft;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Entity
@Data
@Table(name="invoicepurchasedraft")
public class InvoicePurchaseDraft extends ExternalInvoiceDraft{
    
}
