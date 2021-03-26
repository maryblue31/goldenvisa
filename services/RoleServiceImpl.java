/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Role;
import com.example.alma.repositories.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */

@Service
public class RoleServiceImpl implements RoleServiceInterface{
    
    @Autowired
    RoleRepository roleRepository;    
    

    @Override
    public List<Role> getRolesWithoutAdmin() {
        return roleRepository.findByRolenameNot("admin");
    }
    
    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }    
    
    
    @Override
    public Role findByRolename(String rolename){
        return roleRepository.findByRolename(rolename);
    }
    
}
