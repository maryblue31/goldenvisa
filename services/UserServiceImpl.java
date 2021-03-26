/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Role;
import com.example.alma.models.User;
import com.example.alma.repositories.UserRepository;
import com.example.alma.repositories.UserRepositoryPaging;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */
@Service
public class UserServiceImpl implements UserServiceInterface {
    
    
    @Autowired
    UserRepository userRepository;   
    
    @Autowired
    UserRepositoryPaging userRepositoryPaging;    
    

    @Override
    public boolean saveUser(User u) {
        userRepository.save(u);
      return true;  
        
      
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public List<User> getLawyers() {
        return userRepository.findByRequiredDocumentsUploadedNotNullAndLawyerinfoIdNotNull();
    } 
    
    
    @Override
    public User findUser(int id) {
        return userRepository.getOne(id);
    } 
    
    @Override
    public User findUserById(int userId) {
        return userRepository.findByUserId(userId);
    }    

    @Override
    public boolean deleteUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String checkIfUsernameExists(String name){
         User tempdev = userRepository.findByUsername(name);
        if (tempdev == null) {
            return null;
        }
        return "Username already exists. Please enter a different username.";
    }
    
    @Override
    public User findUsername(String name){
         User tempdev = userRepository.findByUsername(name);
        if (tempdev == null) {
            return null;
        }
        return tempdev;
    }    
    
     @Override
    public User checkIfUsernameAnPasswordExists(String username, String password){
         User tempdev = userRepository.findByUsernameAndPassword(username,password);
        if (tempdev == null) {
            return null;
        }
        return tempdev;
    }   
    
    
     @Override
    public String checkIfEmailExists(String email){
         User tempdev = userRepository.findByEmail(email);
        if (tempdev == null) {
            return null;
        }
        return "Email already exists. Please enter a different email.";
    } 
    
    @Override
    public List<User> findByRoleId(Role roleId){
        return userRepository.findByRoleId(roleId);
    }
    
    
}
