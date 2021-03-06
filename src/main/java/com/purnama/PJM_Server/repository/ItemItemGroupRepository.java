/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.repository;

import com.purnama.PJM_Server.model.combine.ItemItemGroup;
import com.purnama.PJM_Server.model.nontransactional.Item;
import com.purnama.PJM_Server.model.nontransactional.ItemGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author p_cor
 */
public interface ItemItemGroupRepository extends JpaRepository<ItemItemGroup, Integer>{
    
    List<ItemItemGroup> findByItem(Item item);
    
    List<ItemItemGroup> findByItemgroup(ItemGroup itemgroup);
}
