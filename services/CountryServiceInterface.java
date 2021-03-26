/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Country;
import java.util.List;

/**
 *
 * @author gianalex
 */
public interface CountryServiceInterface {

    public Country saveCountry(Country c);    
    
    public List<Country> getCountries();  
    
   public Country checkIfCountryExists(String name);

    public boolean deleteCountry(int id);     
    
    public Country getCountry(String name);
    
}
