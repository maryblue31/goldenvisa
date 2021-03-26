/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.controllers;

import com.example.alma.models.Chat;
import com.example.alma.models.Message;
import com.example.alma.models.User;
import com.example.alma.repositories.ChatRepository;
import com.example.alma.services.ChatServiceInterface;
import com.example.alma.services.MessageServiceInterface;
import com.example.alma.services.UserServiceInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gianalex
 */


@Controller
public class ConversationController {

    @Autowired           
    ChatServiceInterface chatServiceInterface;
    
 
    @Autowired
    MessageServiceInterface messageServiceInterface;   
    
    
    @Autowired
    UserServiceInterface userServiceInterface;    

    @GetMapping("/getChat")
    public String getChat(HttpSession session,ModelMap mm,
            @RequestParam("id") int chatId
            ) {

        User user = (User) session.getAttribute("user");
        List<Chat> chatList =new ArrayList();
        chatList=chatServiceInterface.findUser1Id(user);
        chatList.addAll(chatServiceInterface.findUser2Id(user));
     
        List<Message> messageList;
        Chat chat = chatServiceInterface.findChatById(chatId);
        messageList = messageServiceInterface.getByConversationIdList(chat);
                
        mm.addAttribute("chatList", chatList);
        mm.addAttribute("roleId",user.getRoleId().getRoleId());
        mm.addAttribute("chatId",chatId);
        mm.addAttribute("messageList",messageList);
        



        return "conversations";
    }
    
    
    @GetMapping("/getConversations")
    public String getConversations(HttpSession session,ModelMap mm
            ) {

        User user = (User) session.getAttribute("user");
        List<Chat> chatList =new ArrayList();
        chatList=chatServiceInterface.findUser1Id(user);
        chatList.addAll(chatServiceInterface.findUser2Id(user));
     
        List<Message> messageList =new ArrayList();
        int chatId=0;
        if(!chatList.isEmpty()){
            chatId = chatList.get(0).getChatId();
            messageList = messageServiceInterface.getByConversationIdList(chatList.get(0));
        }
        
                
        mm.addAttribute("chatList", chatList);
        mm.addAttribute("roleId",user.getRoleId().getRoleId());
        mm.addAttribute("chatId",chatId);
        mm.addAttribute("messageList",messageList);
        



        return "conversations";
    }    
    
    

    
  
    
    @GetMapping("/startConversation")
    public String startConversation(ModelMap mm,
            HttpSession session,
    @ModelAttribute("conversation") Chat chat,
    @RequestParam (name="id") int id){

 
        User user = userServiceInterface.findUserById(id);
        
        User lawyer = (User) session.getAttribute("user");        

        java.util.Date utilDate = new Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());        
        
        chat.setDatetimeStarted(date);
        chat.setDatetimeUpdated(date);
        chat.setUser1Id(lawyer);
        chat.setUser2Id(user);
        
        int chatId= chatServiceInterface.saveChat(chat);
       
        return "redirect:getChat?id="+chatId;
    }
    
      @PostMapping("/saveMessage")
    public String saveMessage(ModelMap mm,
            HttpSession session,
            
            @RequestParam (name="message") String message,
            @RequestParam (name="chatId") int chatId){

       
        Message msg = new Message();
        
        User user = (User) session.getAttribute("user");        

        java.util.Date utilDate = new Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        Chat chat= chatServiceInterface.findChatById(chatId);

        msg.setDatetimeCreated(date);
        msg.setContent(message);
        msg.setConversationId(chat);
        msg.setSenderId(user);
        msg.setStatus(1);
        

        messageServiceInterface.saveMessage(msg);

       
        return "redirect:getChat?id="+chatId;
    }  
  

    
}
