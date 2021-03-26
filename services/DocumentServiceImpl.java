/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Document;
import com.example.alma.repositories.DocumentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */


@Service
public class DocumentServiceImpl implements DocumentServiceInterface {
    
    @Autowired
    DocumentRepository documentRepository;
    
    @Override
    public boolean saveDocument(Document d) {
        documentRepository.save(d);
      return true;         
    }    
    
    @Override
    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }    

    @Override
    public boolean deleteDocument(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
