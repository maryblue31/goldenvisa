/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Lawyerinfo;
import java.util.List;

/**
 *
 * @author gianalex
 */
public interface LawyerinfoServiceInterface {

    public int saveLawyerinfo(Lawyerinfo l);    
    
    public List<Lawyerinfo> getLawyerinfoList(); 
    
    public Lawyerinfo findLawyerinfo(int id); 

    public boolean deleteLawyerinfo(int id);



    
}
