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
@Table(name="warehouse")
public class Warehouse extends Nontransactional{
    
    @Column(name="code", unique=true, columnDefinition="varchar(25)")
    private String code;
    
    @Column(name="name", columnDefinition="varchar(50)")
    private String name;
    
    @Column(name="address")
    private String address;
    
//    @ManyToMany(mappedBy = "warehouses", fetch = FetchType.LAZY)
//    private Set<User> users = new HashSet<User>();
}
