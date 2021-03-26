/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Role;
import com.example.alma.models.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author alex
 */
public interface UserServiceInterface {
    

    public boolean saveUser(User u);
   
   public List<User> getUsers();
   
   public List<User> getLawyers();
   
   public User findUser(int id) ;
   
   public User findUserById(int userId);
   
   
   public boolean deleteUser(int id);  
   
   public String checkIfUsernameExists(String name);

   public User checkIfUsernameAnPasswordExists(String username, String password);
 
   public User findUsername(String name);
   
   public String checkIfEmailExists(String email);
   
   public List<User> findByRoleId(Role roleId);
    
}
