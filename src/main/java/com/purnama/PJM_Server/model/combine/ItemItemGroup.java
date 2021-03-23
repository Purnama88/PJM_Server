/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.combine;

import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="itemitemgroup",
        uniqueConstraints = {@UniqueConstraint(columnNames = 
                {"itemgroupid", "itemid"})})
public class ItemItemGroup implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="itemgroupid")
    private ItemGroup itemgroup;
    
    @ManyToOne
    @JoinColumn(name="itemid")
    private Item item;
}
