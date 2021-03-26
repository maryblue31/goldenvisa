/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.repositories;

import com.example.alma.models.Application;
import com.example.alma.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gianalex
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>{
 
    public Application findByApplicationId(int applicationId);
    
    public List<Application> findAllByStatus(int status);
    
    public Application findByUserId (User userId);
    
    
}