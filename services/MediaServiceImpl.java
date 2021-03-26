/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Media;
import com.example.alma.repositories.MediaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alex
 */

@Service
public class MediaServiceImpl implements MediaServiceInterface {
    
    @Autowired
    MediaRepository mediaRepository;
    
    @Override
    public int saveMedia(Media m) {
        mediaRepository.save(m);
      return m.getMediaId();        
    } 
    
    
    @Override
    public List<Media> getMediaList() {
        return mediaRepository.findAll();
    } 
      
    
    @Override
    public Media findMedia(int id) {
        return mediaRepository.getOne(id);
    }

    @Override
    public boolean deleteMedia(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } 

}