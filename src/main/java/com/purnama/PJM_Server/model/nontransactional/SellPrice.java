/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.nontransactional;

import com.purnama.PJM_Server.model.Price;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author p_cor
 */
@Entity
@Table(name="sellprice")
public class SellPrice extends Price{
    
}
