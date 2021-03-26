/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.dto.FilterDTO;
import com.example.alma.models.Property;
import com.example.alma.repositories.PropertyRepository;
import com.example.alma.repositories.PropertyRepositoryPaging;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */
@Service
public class PropertyServiceImpl implements PropertyServiceInterface {
    
    @Autowired
    PropertyRepository propertyRepository;
    
    
    @Autowired
    PropertyRepositoryPaging propertyRepositoryPaging;    
    
    @Override
    public int saveProperty(Property p) {
        propertyRepository.save(p);
      return p.getPropertyId();         
    } 
    
    
    @Override
    public List<Property> getProperties() {
        return propertyRepository.findAll();
    }
    
    
    @Override
    public List<Property> getRecentProperties() {
        return propertyRepository.findTop6ByOrderByDatetimeUploadedDesc();
    } 
    @Override
    public List<Property> getRecentTwoProperties() {
        return propertyRepository.findTop2ByOrderByDatetimeUploadedDesc();
    } 
    
    @Override
    public List<Property> getExpensiveProperties() {
        return propertyRepository.findTop4ByOrderByPriceDesc();
    } 



    @Override
    public Page<Property> getPages(Pageable pageable) {
      
       return propertyRepositoryPaging.findAll(pageable);
    }
    
    
    @Override
    public Property findProperty(int id) {
        return propertyRepository.getOne(id);
    }
    
    @Override
    public Property findPropertyById(int id) {
        return propertyRepository.findByPropertyId(id);
    }   
    

    @Override
    public boolean deleteProperty(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 
    
    @Override
    public List<Property> searchPropertyByFilter(FilterDTO filterDTO,int i) {

       
        List <Property> result= new ArrayList();

        
switch (i) {
  case 1:
         result= propertyRepository.findProperties(Long.parseLong(filterDTO.getMaxPrice().replaceAll("\\D+","")),
                Long.parseLong(filterDTO.getMinPrice().replaceAll("\\D+","")),
                Integer.parseInt(filterDTO.getMinBeds()),Integer.parseInt(filterDTO.getMinBathroom()),
                filterDTO.getPropType().toString());   
    break;
  case 2:
         result= propertyRepository.findPropertiesMinArea(Long.parseLong(filterDTO.getMaxPrice().replaceAll("\\D+","")),
                Long.parseLong(filterDTO.getMinPrice().replaceAll("\\D+","")),
                Integer.parseInt(filterDTO.getMinBeds()),Integer.parseInt(filterDTO.getMinBathroom()),
                filterDTO.getPropType().toString(),Integer.parseInt(filterDTO.getMinArea()));    
    break;
  case 3:
         result= propertyRepository.findPropertiesMaxArea(Long.parseLong(filterDTO.getMaxPrice().replaceAll("\\D+","")),
                Long.parseLong(filterDTO.getMinPrice().replaceAll("\\D+","")),
                Integer.parseInt(filterDTO.getMinBeds()),Integer.parseInt(filterDTO.getMinBathroom()),
                filterDTO.getPropType().toString(),Integer.parseInt(filterDTO.getMaxArea()));  
    break;
    case 4:
         result= propertyRepository.findPropertiesAll(Long.parseLong(filterDTO.getMaxPrice().replaceAll("\\D+","")),
                Long.parseLong(filterDTO.getMinPrice().replaceAll("\\D+","")),
                Integer.parseInt(filterDTO.getMinBeds()),Integer.parseInt(filterDTO.getMinBathroom()),
                filterDTO.getPropType().toString(),Integer.parseInt(filterDTO.getMinArea()),Integer.parseInt(filterDTO.getMaxArea())); 
    break;
}

        
        return result;
    }    
    
}
