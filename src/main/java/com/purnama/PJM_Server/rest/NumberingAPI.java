/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Menu;
import com.purnama.PJM_Server.model.nontransactional.Numbering;
import com.purnama.PJM_Server.model.pagination.NumberingPagination;
import com.purnama.PJM_Server.service.MenuService;
import com.purnama.PJM_Server.service.NumberingService;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/api/v1/numberings")
public class NumberingAPI {
    
    private final NumberingService numberingService;
    
    private final MenuService menuService;
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword", "menuid"})
    public ResponseEntity<?> getNumberingList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword,
            @RequestParam(value="menuid") int menuid) {
        
        Menu menu = menuService.findById(menuid).get();
        
        Page<Numbering> ls = numberingService.findByMenuAndNameContaining(menu, keyword, page, itemperpage);

        NumberingPagination numberingpagination = new NumberingPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(numberingpagination);
    }
    
    @GetMapping(value = "", 
            headers = "Accept=application/json", params = {"menuid", "status"})
    public ResponseEntity<?> getNumberingList(
            @RequestParam(value="menuid") int menuid,
            @RequestParam(value="status") boolean status) {
        
        Menu menu = menuService.findById(menuid).get();
        
        List<Numbering> ls = numberingService.findByMenuAndStatus(menu, status);
        
        return ResponseEntity.ok(ls);
    }
    
    @PostMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> saveNumbering(HttpServletRequest httpRequest,
            @RequestBody Numbering numbering){
        
        numbering.setName(numbering.getName().toUpperCase());
        numbering.setPrefix(numbering.getPrefix().toUpperCase());
        
        numbering.setStart(1);
        numbering.setCurrent(1);
        
        numbering.setLastmodified(LocalDateTime.now());
        
        try{
            numberingService.save(numbering);

            return ResponseEntity.ok(numbering);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Name '" + numbering.getName() +"' and prefix '"+ numbering.getPrefix() +"'already exist");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
