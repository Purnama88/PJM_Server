/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
@MappedSuperclass
public class InternalInvoice extends Transactional{
    
}
