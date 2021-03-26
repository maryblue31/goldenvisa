/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.RequiredDocuments;
import java.util.List;

/**
 *
 * @author gianalex
 */

public interface RequiredDocumentsServiceInterface {

    public boolean saveRequiredDocument(RequiredDocuments d);    
    
    public List<RequiredDocuments> getRequiredDocuments();    

    public boolean deleteRequiredDocument(int id);     
    
    
    
}