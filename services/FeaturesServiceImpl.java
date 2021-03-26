/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Features;
import com.example.alma.repositories.FeaturesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.alma.models.Property;

/**
 *
 * @author gianalex
 */

@Service
public class FeaturesServiceImpl implements FeaturesServiceInterface {
    
    @Autowired
    FeaturesRepository featuresRepository;
    
    @Override
    public boolean saveFeatures(Features f) {
        featuresRepository.save(f);
      return true;         
    }    
    
    @Override
    public List<Features> getFeaturesList() {
        return featuresRepository.findAll();
    }  
    
     @Override
    public Features findFeaturesById(Property property) {
        return featuresRepository.findByPropertyId(property);
    }   

    @Override
    public boolean deleteFeatures(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    

    
}