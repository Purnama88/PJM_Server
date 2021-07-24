/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.transactional.draft.ReturnSalesDraft;
import com.purnama.PJM_Server.model.transactional.draft.ItemReturnSalesDraft;
import com.purnama.PJM_Server.repository.ItemReturnSalesDraftRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ItemReturnSalesDraftService {
    private final ItemReturnSalesDraftRepository itemreturnsalesdraftRepository;
    
    public List<ItemReturnSalesDraft> findAll(){
        return itemreturnsalesdraftRepository.findAll();
    }
    
    public Optional<ItemReturnSalesDraft> findById(Integer id){
        return itemreturnsalesdraftRepository.findById(id);
    }
    
    public ItemReturnSalesDraft save(ItemReturnSalesDraft itemreturnsalesdraft){
        return itemreturnsalesdraftRepository.save(itemreturnsalesdraft);
    }
    
    public List<ItemReturnSalesDraft> saveAll(List<ItemReturnSalesDraft> itemreturnsalesdrafts){
        return itemreturnsalesdraftRepository.saveAll(itemreturnsalesdrafts);
    }
    
    public void deleteById(Integer id){
        itemreturnsalesdraftRepository.deleteById(id);
    }
    
    public List<ItemReturnSalesDraft> findByReturnsalesdraft(ReturnSalesDraft returnsalesdraft){
        return itemreturnsalesdraftRepository.findByReturnsalesdraft(returnsalesdraft, Sort.by(Sort.Direction.ASC, "id"));
    }
    
    public Long deleteByReturnsalesdraft(ReturnSalesDraft returnsalesdraft){
        return itemreturnsalesdraftRepository.deleteByReturnsalesdraft(returnsalesdraft);
    }
}
