/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Role;
import java.util.List;

/**
 *
 * @author alex
 */
public interface RoleServiceInterface {
    
    public List<Role> getRolesWithoutAdmin();   
    
    public Role findByRolename(String rolename);
    
    public List<Role> getRoles();
    
}
