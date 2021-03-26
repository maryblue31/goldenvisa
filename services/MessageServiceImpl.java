/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Chat;
import com.example.alma.models.Message;
import com.example.alma.repositories.MessageRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */
@Service
public class MessageServiceImpl implements MessageServiceInterface{
    
    @Autowired
    MessageRepository messageRepository;
 
    public Message getByMessageId(int messageId){
        return messageRepository.findByMessageId(messageId);
    }
    
    public List<Message> getByConversationIdList(Chat conversationId){
         return messageRepository.findByConversationId(conversationId);
    }    

    public int saveMessage(Message m){
        messageRepository.save(m);
      return m.getMessageId();          
    }    
    
    public List<Message> getMessageList(){
         return messageRepository.findAll();
    }    

    public boolean deleteMessage(int id){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
 
    
    

 