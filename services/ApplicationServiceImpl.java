/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Application;
import com.example.alma.models.User;
import com.example.alma.repositories.ApplicationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */

@Service
public class ApplicationServiceImpl implements ApplicationServiceInterface {
    
    
    @Autowired
    ApplicationRepository applicationRepository;   
    
    
    @Override
    public int saveApplication(Application a) {
        applicationRepository.save(a);
      return a.getApplicationId();        
    } 
    
    
    @Override
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    } 
      
    
    @Override
    public Application findApplication(int id) {
        return applicationRepository.getOne(id);
    }
    
    @Override
    public Application findApplicationById(int applicationId) {
        return applicationRepository.findByApplicationId(applicationId);
    }
    
    @Override
    public Application findApplicationByUserId(User userId) {
        return applicationRepository.findByUserId(userId);
    }    
    
    @Override
    public List<Application> getApplicationsByStatus(int status){
          
          return applicationRepository.findAllByStatus(status);
     }
    

    @Override
    public boolean deleteApplication(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
     

}