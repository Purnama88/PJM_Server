/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.nontransactional.User;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class UserPagination extends Pagination{
    
    private List<User> users;
    
    public UserPagination(int totalpages, List<User> users){
        this.totalpages = totalpages;
        this.users = users;
    }
}
