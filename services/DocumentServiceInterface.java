/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Document;
import java.util.List;

/**
 *
 * @author gianalex
 */
public interface DocumentServiceInterface {
    public boolean saveDocument(Document d);    
    
    public List<Document> getDocuments();    

    public boolean deleteDocument(int id);      
}
