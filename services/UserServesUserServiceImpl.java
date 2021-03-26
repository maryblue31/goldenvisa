/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.UserServesUser;
import com.example.alma.repositories.UserServesUserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */

@Service
public class UserServesUserServiceImpl implements UserServesUserServiceInterface {
    
    
    @Autowired
    UserServesUserRepository userServesUserRepository;   
    
    

    @Override
    public boolean saveUserServesUser(UserServesUser u) {
        userServesUserRepository.save(u);
      return true;  
              
    }

    @Override
    public List<UserServesUser> getUserServesUserList() {
        return userServesUserRepository.findAll();
    }
    
    

    
    @Override
    public UserServesUser findUserServesUser(int id) {
        return userServesUserRepository.getOne(id);
    }   

    @Override
    public boolean deleteUserServesUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
}
