/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Item;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ItemPagination extends Pagination{
    private List<Item> items;
    
    public ItemPagination(int totalpages, List<Item> items){
        this.totalpages = totalpages;
        this.items = items;
    }
}
