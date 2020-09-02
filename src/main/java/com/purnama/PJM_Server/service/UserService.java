/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.purnama.PJM_Server.service;

import com.purnama.PJM_Server.model.nontransactional.User;
import com.purnama.PJM_Server.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author p_cor
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }
    
    public User save(User user){
        return userRepository.save(user);
    }
    
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    
    public Optional<User> findByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }
    
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    public Optional<User> findByCode(String code){
        return userRepository.findByCode(code);
    }
    
    public Page<User> findByCodeContainingOrNameContaining(String code, String name, int page, int size){
        return userRepository.findByCodeContainingOrNameContaining(code, name, PageRequest.of(page-1, size, Sort.by(Sort.Direction.ASC, "username")));
    }
}
