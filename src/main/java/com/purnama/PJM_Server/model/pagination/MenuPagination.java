/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Menu;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class MenuPagination extends Pagination{
    private List<Menu> menus;
    
    public MenuPagination(int totalpages, List<Menu> menus){
        this.totalpages = totalpages;
        this.menus = menus;
    }
}
