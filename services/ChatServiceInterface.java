/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Chat;
import com.example.alma.models.User;
import java.util.List;

/**
 *
 * @author gianalex
 */
public interface ChatServiceInterface {
    
     public int saveChat(Chat c);    
    
    public List<Chat> getChatList(); 
    
    
    public List<User> findUser1IdList(Integer user1Id);
    
    public List<Chat> findUser1Id(User user1Id);
    
    public List<Chat> findUser2Id(User user2Id);
    
    public List<Chat> findUser1IdAndUser2Id(User user1Id, User user2Id);
    
    public Chat findChatById(int chatId);
    

    public boolean deleteChat(int id);   
    
}

