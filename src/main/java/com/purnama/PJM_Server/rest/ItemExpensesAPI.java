/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.transactional.Expenses;
import com.purnama.PJM_Server.model.transactional.ItemExpenses;
import com.purnama.PJM_Server.service.ExpensesService;
import com.purnama.PJM_Server.service.ItemExpensesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/itemexpenses")
public class ItemExpensesAPI {
    
    private final ItemExpensesService itemexpensesService;
    
    private final ExpensesService expensesService;
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"expensesid"})
    public ResponseEntity<?> getItemExpensesList(
            @RequestParam(value="expensesid") int expensesid) {
        
        Expenses expenses = expensesService.findById(expensesid).get();
        
        List<ItemExpenses> ls = itemexpensesService.findByExpenses(expenses);
        
        return ResponseEntity.ok(ls);
    }
    
}