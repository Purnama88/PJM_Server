/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 * @param <V>
 */
@Data
public class Pagination<V> {
    private int totalpages;
    private List<V> list;
    
    public Pagination(int totalpages, List<V> list){
        this.list = list;
        this.totalpages = totalpages;
    }
}
