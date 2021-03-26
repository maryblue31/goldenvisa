/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.controllers;

import com.example.alma.models.Property;
import com.example.alma.models.User;
import com.example.alma.services.PropertyServiceInterface;
import com.example.alma.services.UserServiceInterface;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author alex
 */
@Controller
public class DefaultController {
    
    
    @Autowired
    PropertyServiceInterface propertyServiceInterface; 
    
    @Autowired
    UserServiceInterface userServiceInterface;     
    
    @GetMapping("/")
    public String showIndex(ModelMap mm) {
        
         int properties = propertyServiceInterface.getProperties().size();
         int lawyers = userServiceInterface.getLawyers().size();
         int customers = userServiceInterface.getUsers().size();
         
         List<Property> result = propertyServiceInterface.getRecentProperties();
         List<Property> expensive = propertyServiceInterface.getExpensiveProperties();
         
          mm.addAttribute("data", result);       
          mm.addAttribute("properties", properties);       
          mm.addAttribute("expensive", expensive);       
          mm.addAttribute("lawyers", lawyers);       
          mm.addAttribute("customers", customers);       
        
        return "index";
    }    
    
}
