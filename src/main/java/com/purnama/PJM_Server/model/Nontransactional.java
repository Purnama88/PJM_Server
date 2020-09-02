/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
@MappedSuperclass
public class Nontransactional implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    protected int id;
    
    @Column(name="status", nullable = false, columnDefinition="boolean default true")
    protected boolean status = true;
    
    @Column(name="note")
    protected String note;
    
    @Column(name="lastmodified")
    protected LocalDateTime lastmodified;
}
