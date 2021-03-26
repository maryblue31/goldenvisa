/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.repositories;

import com.example.alma.models.Role;
import com.example.alma.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alex
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  
    
    public User findByUsername(String username);
    
    public User findByUserId(int userId);
    
    public User findByEmail(String email);

    public User findByUsernameAndPassword(String username,String password);
    
     public List<User> findByRequiredDocumentsUploadedNotNullAndLawyerinfoIdNotNull();
     
     public List<User> findByRoleId(Role roleId);
}
