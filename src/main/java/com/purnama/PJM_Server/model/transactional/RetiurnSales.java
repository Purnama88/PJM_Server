/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.transactional;

import com.purnama.PJM_Server.model.ExternalInvoice;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author p_cor
 */

@Entity
@Data
@Table(name="returnsales")
public class RetiurnSales extends ExternalInvoice{
    
}
