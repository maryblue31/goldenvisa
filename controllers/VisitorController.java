/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author gianalex
 */
@Controller
public class VisitorController {



    @GetMapping("/goldenVisa")
    public String goldenVisa() {
        return "goldenVisa";
    }
    
    @GetMapping("/whyGreece")
    public String whyGreece() {
        return "whyGreece";
    }    

    @GetMapping("/applicationProccess")
    public String applicationProccess() {
        return "applicationProccess";
    }     

    @GetMapping("/eligibilityCriteria")
    public String eligibilityCriteria() {
        return "eligibilityCriteria";
    } 
    
}
