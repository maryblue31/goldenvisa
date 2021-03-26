/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.validators;

import com.example.alma.models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author alex
 */
@Component
public class UserValidator implements Validator{
    
private static final String NAME_PATTERN = "^[a-zA-Z0-9]{4,35}$";
private static final String CHARACTERS_PATTERN = "^[a-zA-Z ]*$";  
private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]{4,40}$";
private static final String NAME_PATTERN_TEST = "^[a-zA-Z0-9]{6,35}$";
private static final String PASSWORD_PATTERN = "^[a-zA-Z0-9]{8,35}$";
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User newUser = (User) target;


        if (!newUser.getFirstname().matches(NAME_PATTERN)) {
            if(newUser.getFirstname().length()<4 || newUser.getFirstname().length()>35){
                            errors.rejectValue("firstname", "firstname.wrong");
            }
        }
         if (!newUser.getLastname().matches(NAME_PATTERN)) {
            if(newUser.getLastname().length()<4 || newUser.getLastname().length()>35){
                            errors.rejectValue("lastname", "lastname.wrong");
            }
        } 
         if (!newUser.getUsername().matches(NAME_PATTERN)) {
            if(newUser.getLastname().length()<4 || newUser.getLastname().length()>35){
                            errors.rejectValue("username", "username.wrong");
            }
        } 
         if (!newUser.getPassword().matches(PASSWORD_PATTERN)) {
            if(newUser.getLastname().length()<8 || newUser.getLastname().length()>35){
                            errors.rejectValue("password", "password.wrong");
            }
        }         
         if (!newUser.getEmail().matches(EMAIL_PATTERN)) {
            if(newUser.getEmail().length()<4 || newUser.getEmail().length()>40){
                            errors.rejectValue("email", "email.wrong");
            }
        }         

    }    
    
    
    
}
