/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Chat;
import com.example.alma.models.User;
import com.example.alma.repositories.ChatRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */

@Service
public class ChatServiceImpl implements ChatServiceInterface{
    
    @Autowired
    ChatRepository chatRepository;     
    
 
     public int saveChat(Chat c){
        chatRepository.save(c);
      return c.getChatId();          
     }    
    
    public List<Chat> getChatList(){
        return chatRepository.findAll();
    } 
    
    
    public Chat findChatById(int chatId){
        return chatRepository.findByChatId(chatId);
    }  
    
    
    public List<User> findUser1IdList(Integer user1Id){
        return chatRepository.findByUser1Id(user1Id);
    } 
    
    public List<Chat> findUser1IdAndUser2Id(User user1Id, User user2Id){
        return chatRepository.findByUser1IdAndUser2Id(user1Id, user2Id);
    }   
    
     public List<Chat> findUser1Id(User user1Id){
        return chatRepository.findByUser1Id(user1Id);
     }
     
     public List<Chat> findUser2Id(User user2Id){
        return chatRepository.findByUser2Id(user2Id);
     }

    public boolean deleteChat(int id){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }      
    
    
    
}