/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.nontransactional;

import com.purnama.PJM_Server.model.Nontransactional;
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
@Table(name="menu")
public class Menu extends Nontransactional{
    
    public static final String [] MENU_NAMES = {
        "Invoice Purchase",
        "Invoice Sales",
        
        "Return Purchase",
        "Return Sales",
        
        "Item Adjustment",
        "Delivery",
        
        "Expenses",
        "Invoice Warehouse",
        
        "Incoming Payment",
        "Outgoing Payment",
        
        "Report",
        
    };
    
    @Column(name="name", columnDefinition="varchar(50)")
    private String name;
    
}
