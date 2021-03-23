/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.util;

/**
 *
 * @author p_cor
 */
public class SearchCriteria {

    private String key;
    private Object value;
    private SearchOperation operation;
    private boolean oroperation;


    public SearchCriteria(String key, SearchOperation operation, boolean oroperation, Object value) {
        this.key = key;
        this.value = value;
        this.operation = operation;
        this.oroperation = oroperation;
    }

    public boolean isOroperation() {
        return oroperation;
    }

    public void setOroperation(boolean oroperation) {
        this.oroperation = oroperation;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }
}