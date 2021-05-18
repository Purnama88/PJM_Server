/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Role;
import com.purnama.PJM_Server.model.pagination.Pagination;
import com.purnama.PJM_Server.service.RoleService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("/api/v1/roles")
public class RoleAPI {
    
    private final RoleService roleService;
    
    @GetMapping(value="",
            headers = "Accept=application/json", params = {"status"})
    public ResponseEntity getRoleList(@RequestParam(value="status") boolean status){
        if(status){
            return ResponseEntity.ok(roleService.findByStatusTrue());
        }
        else{
            return ResponseEntity.ok(roleService.findByStatusFalse());
        }
    }
    
    @GetMapping(value = "",
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getRoleList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<Role> ls = roleService.findByNameContaining(keyword, page, itemperpage);
        
        Pagination<Role> rolepagination = new Pagination<>(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(rolepagination);
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getRole(@PathVariable("id") int id) {
        return ResponseEntity.ok(roleService.findById(id));
    }
    
    @PostMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> saveRole(HttpServletRequest httpRequest,
            @RequestBody Role role){
        
        role.setName(role.getName().toUpperCase());
        role.setCreateddate(LocalDateTime.now());
        role.setLastmodified(LocalDateTime.now());
        
        try{
            roleService.save(role);

            return ResponseEntity.ok(role);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Name '" + role.getName() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> updateRole(HttpServletRequest httpRequest,
            @RequestBody Role role){
        
        Role temp = roleService.findById(role.getId()).get();
        temp.setLastmodified(LocalDateTime.now());
        temp.setName(role.getName());
        temp.setNote(role.getNote());
        temp.setStatus(role.isStatus());
        
        try{
            roleService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Name '" + role.getName() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
