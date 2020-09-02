/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.nontransactional;

import com.purnama.PJM_Server.model.Nontransactional;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="itemwarehouse", uniqueConstraints = {@UniqueConstraint(columnNames={"itemid", "warehouseid"})})
public class ItemWarehouse extends Nontransactional{
    
    @ManyToOne
    @JoinColumn(name="itemid")
    private Item item;
    
    @ManyToOne
    @JoinColumn(name="warehouseid")
    private Warehouse warehouse;
    
    @Column(name="stock", columnDefinition="Decimal(16,4)")
    private double stock;
}
