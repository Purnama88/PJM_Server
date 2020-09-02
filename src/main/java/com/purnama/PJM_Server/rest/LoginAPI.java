/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Login;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.security.JwtUtil;
import com.purnama.PJM_Server.service.UserService;
import com.purnama.PJM_Server.service.WarehouseService;
import java.util.Optional;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginAPI {
    
    private final UserService userService;
    
    private final WarehouseService warehouseService;
    
    @RequestMapping("/warehouse/activelist")
    public ResponseEntity findAll(ServletResponse response){
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        
        return ResponseEntity.ok(warehouseService.findByStatusTrue());
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST,
            headers = "Accept=application/json")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, 
            @RequestBody Login login) throws Exception{
        
        String username = login.getUsername();
        String password = login.getPassword();
        
        Optional<User> user_op = userService.findByUsernameAndPassword(username, password);
        Optional<Warehouse> warehouse_op = warehouseService.findById(login.getWarehouseid());   
        
        if(!warehouse_op.isPresent()){
            return ResponseEntity.badRequest().body("Invalid Log In");
        }
        else if(!user_op.isPresent()){
            return ResponseEntity.badRequest().body("Invalid Username or Password");
        }
        else if(!user_op.get().isStatus()){
            return ResponseEntity.badRequest().body("User is inactive");
        }
        else if(!warehouse_op.get().isStatus()){
                return ResponseEntity.badRequest().body("Warehouse is inactive");
        }
        else{
            Warehouse warehouse = warehouse_op.get();
            User user = user_op.get();
            
            boolean contain = false;
            
            for(Warehouse temp : user.getWarehouses()){
                if(temp.getId() == warehouse.getId()){
                    contain = true;
                }
            }
            
            if(contain){
                String token = JwtUtil.generateToken(user.getId(), warehouse.getId());
                response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

                return ResponseEntity.ok(user);
            }
            else{
                return ResponseEntity.badRequest().body("You do not have privilege to access this warehouse");
            }
        }
    }
    
}
