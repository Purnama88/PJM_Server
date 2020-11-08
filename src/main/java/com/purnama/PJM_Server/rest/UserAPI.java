/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.pagination.UserPagination;
import com.purnama.PJM_Server.service.UserService;
import com.purnama.PJM_Server.util.GlobalFunctions;
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
@RequestMapping("/api/v1/users")
public class UserAPI {
    
    private final UserService userService;
    
    @GetMapping(value = "",  
            headers = "Accept=application/json", params = {"itemperpage", "page", "keyword"})
    public ResponseEntity<?> getUserList(
            @RequestParam(value="itemperpage") int itemperpage,
            @RequestParam(value="page") int page,
            @RequestParam(value="keyword") String keyword) {
        
        Page<User> ls = userService.findByCodeContainingOrNameContaining(keyword, keyword, page, itemperpage);
        
        UserPagination user_pagination = new UserPagination(ls.getTotalPages(), ls.getContent());
        
        return ResponseEntity.ok(user_pagination);
    }
    
    @GetMapping(value = "/{id}",
            headers = "Accept=application/json")
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.findById(id));
    }
    
    @PostMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> saveUser(HttpServletRequest httpRequest,
            @RequestBody User user){
        
        user.setUsername(user.getUsername().toLowerCase());
        
        user.setCode(user.getCode().toUpperCase());
        user.setCreateddate(LocalDateTime.now());
        user.setLastmodified(LocalDateTime.now());
        
        try{
            String password = user.getPassword();
            user.setPassword(GlobalFunctions.encrypt(password));
        }
        catch(DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body("Code '" + user.getCode() +"' or Username '"+ user.getUsername() +"' already exist");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
        
        try{
            userService.save(user);

            return ResponseEntity.ok(user);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e);
        }
    }
    
    @PutMapping(value = "", 
            headers = "Accept=application/json")
    public ResponseEntity<?> updateUser(HttpServletRequest httpRequest,
            @RequestBody User user){
        
        User temp = userService.findById(user.getId()).get();
        
        temp.setMaximumdiscount(user.getMaximumdiscount());
        temp.setName(user.getName());
        temp.setEmail(user.getEmail());
        temp.setRole(user.getRole());
        temp.setWarehouses(user.getWarehouses());
        temp.setStatus(user.isStatus());
        temp.setNote(user.getNote());
        
        try{
            userService.save(temp);

            return ResponseEntity.ok(temp);
        }
        catch(Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e);
        }
        
    }
}
