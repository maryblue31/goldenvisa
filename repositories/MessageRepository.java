/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.repositories;

import com.example.alma.models.Chat;
import com.example.alma.models.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gianalex
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    
    public Message findByMessageId(int messageId);
    
    public List<Message> findByConversationId(Chat chatId);

    
}