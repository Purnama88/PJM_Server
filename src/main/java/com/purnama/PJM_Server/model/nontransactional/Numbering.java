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
@Table(name="numbering",
        uniqueConstraints = {@UniqueConstraint(columnNames = 
                {"prefix_", "name", "menuid"})})
public class Numbering extends Nontransactional{
    
    @Column(name="name", columnDefinition="varchar(25)")
    private String name;
    
    @Column(name="prefix_", columnDefinition="varchar(10)")
    private String prefix;
    
    @Column(name="start_")
    private int start;
    
    @Column(name="end_")
    private int end;
    
    @Column(name="current_")
    private int current;
    
    @ManyToOne
    @JoinColumn(name = "menuid")
    private Menu menu;
    
    public int getLength(){
        try{
            return String.valueOf(getEnd()).length();
        }
        catch(Exception e){
            return 1;
        }
    }
    
    public String getFormat(){
        return "%0" + getLength() + "d";
    }
    
    @Override
    public String toString(){
        return getPrefix() + String.format(getFormat(), getCurrent());
    }
}
