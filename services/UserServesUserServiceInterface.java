/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.UserServesUser;
import java.util.List;

/**
 *
 * @author gianalex
 */
public interface UserServesUserServiceInterface {
   public boolean saveUserServesUser(UserServesUser u);
   
   public List<UserServesUser> getUserServesUserList();  
   
   public UserServesUser findUserServesUser(int id) ;   
   
   public boolean deleteUserServesUser(int id);     
}
