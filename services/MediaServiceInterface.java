/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Media;
import java.util.List;

/**
 *
 * @author alex
 */
public interface MediaServiceInterface {


    public int saveMedia(Media m);    
    
    public List<Media> getMediaList(); 
    
    public Media findMedia(int id); 

    public boolean deleteMedia(int id);

    
}
