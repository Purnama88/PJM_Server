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
@Table(name="role")
public class Role extends Nontransactional{
    
    @Column(name="name", unique=true, columnDefinition="varchar(25)")
    private String name;
    
    @Column(name="defaultrole", nullable = false, columnDefinition="boolean default true")
    private boolean defaultrole = true;
    
    @Column(name="dateforward", nullable = false, columnDefinition="boolean default true")
    private boolean dateforward = true;
    
    @Column(name="datebackward", nullable = false, columnDefinition="boolean default true")
    private boolean datebackward = true;
    
    @Column(name="raisebuyprice", nullable = false, columnDefinition="boolean default true")
    private boolean raisebuyprice = true;
    
    @Column(name="raisesellprice", nullable = false, columnDefinition="boolean default true")
    private boolean raisesellprice = true;
    
    @Column(name="lowerbuyprice", nullable = false, columnDefinition="boolean default true")
    private boolean lowerbuyprice = true;
    
    @Column(name="lowersellprice", nullable = false, columnDefinition="boolean default true")
    private boolean lowersellprice = true;
}
