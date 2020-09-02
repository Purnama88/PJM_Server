/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Menu;
import com.purnama.PJM_Server.model.pagination.MenuPagination;
import com.purnama.PJM_Server.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/api/v1/menus")
public class MenuAPI {
    
    private final MenuService menuService;
    
    @GetMapping(value="", params = {"status"})
    public ResponseEntity getMenuList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(menuService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(menuService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getMenuList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Menu> ls = menuService.findByNameContaining(keyword, page, itemperpage);
        
        MenuPagination menu_pagination = new MenuPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(menu_pagination);
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getMenu(@PathVariable("id") int id) {
        return ResponseEntity.ok(menuService.findById(id));
    }
}