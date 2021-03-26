/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Application;
import com.example.alma.models.User;
import java.util.List;
/**
 *
 * @author gianalex
 */
public interface ApplicationServiceInterface {
 
    public int saveApplication(Application a);    
    
    public List<Application> getApplications(); 
    
    public Application findApplication(int id); 
    
    public Application findApplicationById(int applicationId);
    
    public Application findApplicationByUserId(User userId);
    
     public List<Application> getApplicationsByStatus(int status);

    public boolean deleteApplication(int id);
    
    
    
    
}
