/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Chat;
import com.example.alma.models.Message;
import java.util.List;

/**
 *
 * @author gianalex
 */
public interface MessageServiceInterface {
 
    public Message getByMessageId(int messageId);
    
    public List<Message> getByConversationIdList(Chat conversationId);   
    
    public int saveMessage(Message m);    
    
    public List<Message> getMessageList();    

    public boolean deleteMessage(int id);    
    
}
