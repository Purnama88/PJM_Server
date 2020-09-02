/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.nontransactional;

import com.purnama.PJM_Server.model.Nontransactional;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Entity
@Data
@Table(name="user")
public class User extends Nontransactional{
    
    @Column(name="username", unique=true, columnDefinition="varchar(25)")
    private String username;
    
    @Column(name="password", columnDefinition="varchar(255)")
    private String password;
    
    @Column(name="name", columnDefinition="varchar(50)")
    private String name;
    
    @Column(name="code", unique=true, columnDefinition="varchar(25)")
    private String code;
    
    @Column(name="maximumdiscount", columnDefinition="Decimal(9, 4)")
    private double maximumdiscount;
    
    @Column(name="email", columnDefinition="varchar(50)")
    private String email;
  
    @ManyToOne
    @JoinColumn(name="role")
    private Role role;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "userwarehouse", 
        joinColumns = { @JoinColumn(name = "userid") }, 
        inverseJoinColumns = { @JoinColumn(name = "warehouseid",
                nullable = false)})
    private Set<Warehouse> warehouses = new HashSet<>();
}
