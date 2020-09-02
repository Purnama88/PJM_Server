/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.Role;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class RolePagination  extends Pagination{
    private List<Role> roles;
    
    public RolePagination(int totalpages, List<Role> roles){
        this.totalpages = totalpages;
        this.roles = roles;
    }
}
