/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.rest;

import com.purnama.PJM_Server.model.nontransactional.Currency;
import com.purnama.PJM_Server.model.nontransactional.Menu;
import com.purnama.PJM_Server.model.nontransactional.Role;
import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.model.nontransactional.Warehouse;
import com.purnama.PJM_Server.service.CurrencyService;
import com.purnama.PJM_Server.service.MenuService;
import com.purnama.PJM_Server.service.RoleService;
import com.purnama.PJM_Server.service.UserService;
import com.purnama.PJM_Server.service.WarehouseService;
import com.purnama.PJM_Server.util.GlobalFunctions;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author p_cor
 */

@RestController
@Slf4j
@RequiredArgsConstructor
public class InitalizationAPI {
    
    private final UserService userService;
    private final MenuService menuService;
    private final WarehouseService warehouseService;
    private final RoleService roleService;
    private final CurrencyService currencyService;
    
    @RequestMapping("/initMenu")
    public ResponseEntity<?> initMenu(){
        for(int i = 0; i < Menu.MENU_NAMES.length; i++){
            Menu menu = new Menu();
            menu.setId(i+1);
            menu.setName(Menu.MENU_NAMES[i]);
            menu.setNote("");
            menu.setLastmodified(LocalDateTime.now());
            menu.setCreateddate(LocalDateTime.now());
            menuService.save(menu);
        }
        
        return ResponseEntity.ok("ok");
    }
    
    @RequestMapping("/init")
    public ResponseEntity<?> init() throws Exception{
        Role role = new Role();
        role.setName("SUPERADMIN");
        role.setNote("");
        role.setLastmodified(LocalDateTime.now());
        role.setCreateddate(LocalDateTime.now());
        
        roleService.save(role);
        
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress("Jl. Petojo");
        warehouse.setCode("ENA");
        warehouse.setLastmodified(LocalDateTime.now());
        warehouse.setCreateddate(LocalDateTime.now());
        warehouse.setName("EnamA");
        warehouse.setNote("");
        
        warehouseService.save(warehouse);
        
        User user = new User();
        user.setCode("PRN");
        user.setEmail("emai.email@email.com");
        user.setLastmodified(LocalDateTime.now());
        user.setCreateddate(LocalDateTime.now());
        user.setMaximumdiscount(100);
        user.setName("purnama");
        user.setNote("");
        user.setPassword(GlobalFunctions.encrypt("purnama"));
        user.setRole(role);
        user.setUsername("purnama");
        
        Set<Warehouse> warehouses = new HashSet<>();
        warehouses.add(warehouse);
        user.setWarehouses(warehouses);
        
        userService.save(user);
//        
//        Currency currency = new Currency();
//        currency.setCode("IDR");
//        currency.setDefault_currency(true);
//        currency.setDescription("Indonesian Rupiah");
//        currency.setLastmodified(LocalDateTime.now());
//        currency.setName("Rupiah");
//        currency.setNote("");
//        
//        currencyService.save(currency);
        
        return ResponseEntity.ok("OK");
    }
    
}
