/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.model.pagination;

import com.purnama.PJM_Server.model.transactional.draft.DeliveryDraft;
import java.util.List;
import lombok.Data;

/**
 *
 * @author p_cor
 */
@Data
public class DeliveryDraftPagination extends Pagination{
    private List<DeliveryDraft> deliverydrafts;
    
    public DeliveryDraftPagination(int totalpages, List<DeliveryDraft> deliverydrafts){
        this.totalpages = totalpages;
        this.deliverydrafts = deliverydrafts;
    }
}
