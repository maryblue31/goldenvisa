/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Lawyerinfo;
import com.example.alma.repositories.LawyerinfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */
@Service
public class LawyerinfoServiceImpl implements LawyerinfoServiceInterface {
    
    
    @Autowired
    LawyerinfoRepository lawyerinfoRepository;    
    
    
    @Override
    public int saveLawyerinfo(Lawyerinfo l){
         lawyerinfoRepository.save(l);
      return l.getLawyerinfoId();          
    }   
    
    
    @Override
    public List<Lawyerinfo> getLawyerinfoList(){
                return lawyerinfoRepository.findAll();
    }
    
    
    @Override
    public Lawyerinfo findLawyerinfo(int id){
         return lawyerinfoRepository.getOne(id);
    }
    
    
    @Override
    public boolean deleteLawyerinfo(int id){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
}

