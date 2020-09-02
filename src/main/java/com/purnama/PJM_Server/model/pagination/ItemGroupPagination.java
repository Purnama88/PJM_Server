/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class ItemGroupPagination extends Pagination{
    
    private List<ItemGroup> itemgroups;
    
    public ItemGroupPagination(int totalpages, List<ItemGroup> itemgroups){
        this.totalpages = totalpages;
        this.itemgroups = itemgroups;
    }
}
