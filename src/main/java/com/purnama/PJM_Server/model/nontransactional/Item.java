/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.nontransactional;

import com.purnama.PJM_Server.model.Nontransactional;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="item_",
        uniqueConstraints = {@UniqueConstraint(columnNames = 
                {"code", "label_id"})})

public class Item extends Nontransactional{
    
    @Column(name="code", columnDefinition="varchar(25)")
    private String code;
    
    @Column(name="name", columnDefinition="varchar(50)")
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name="cost", columnDefinition="Decimal(16,4)")
    private double cost;
    
    @ManyToOne
    @JoinColumn(name="label_id")
    private Label label;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "item_itemgroup_", 
        joinColumns = { @JoinColumn(name = "item_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "itemgroup_id") }
    )
    Set<ItemGroup> itemgroups = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "item_model_", 
        joinColumns = { @JoinColumn(name = "item_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "model_id") }
    )
    Set<ItemGroup> models = new HashSet<>();
    
}
