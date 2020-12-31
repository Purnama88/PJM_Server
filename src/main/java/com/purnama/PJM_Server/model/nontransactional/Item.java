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
@Table(name="item",
        uniqueConstraints = {@UniqueConstraint(columnNames = 
                {"code", "labelid"})})

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
    @JoinColumn(name="labelid")
    private Label label;
    
    @Column(name="sellprice")
    private double sellprice;
    
    @Column(name="buyprice")
    private double buyprice;
    
    @Column(name="bulkbuyprice", nullable = false, columnDefinition="boolean default false")
    private boolean bulksellprice;
    
    @Column(name="bulksellprice", nullable = false, columnDefinition="boolean default false")
    private boolean bulkbuyprice;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "itemitemgroup", 
        joinColumns = { @JoinColumn(name = "itemid") }, 
        inverseJoinColumns = { @JoinColumn(name = "itemgroupid") }
    )
    Set<ItemGroup> itemgroups = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "itemmodel", 
        joinColumns = { @JoinColumn(name = "itemid") }, 
        inverseJoinColumns = { @JoinColumn(name = "modelid") }
    )
    Set<ItemGroup> models = new HashSet<>();
    
}
