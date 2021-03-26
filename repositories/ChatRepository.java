/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.repositories;

import com.example.alma.models.Chat;
import com.example.alma.models.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gianalex
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
    
    public Chat findByChatId(int chatId);
    
    public List<User> findByUser1Id(Integer user1Id);
    
    public List<Chat> findByUser1Id(User user1Id);
    
    public List<Chat> findByUser2Id(User user2Id);
    
    
    public List<Chat> findByUser1IdAndUser2Id(User user1Id, User user2Id);
    
}