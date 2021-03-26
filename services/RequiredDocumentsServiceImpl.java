/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.RequiredDocuments;
import com.example.alma.repositories.RequiredDocumentsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */

@Service
public class RequiredDocumentsServiceImpl implements RequiredDocumentsServiceInterface {
 
    @Autowired
    RequiredDocumentsRepository requiredDocumentsRepository;
    
    @Override
    public boolean saveRequiredDocument(RequiredDocuments d) {
        requiredDocumentsRepository.save(d);
      return true;         
    }    
    
    @Override
    public List<RequiredDocuments> getRequiredDocuments() {
        return requiredDocumentsRepository.findAll();
    }    

    @Override
    public boolean deleteRequiredDocument(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
