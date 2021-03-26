/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.services;

import com.example.alma.models.Country;
import com.example.alma.repositories.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gianalex
 */

@Service
public class CountryServiceImpl implements CountryServiceInterface {
    
    @Autowired
    CountryRepository countryRepository;
    
    @Override
    public Country saveCountry(Country c) {
      return countryRepository.save(c);         
    }    
    
    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }  
    
    @Override
    public Country getCountry(String name) {
        return countryRepository.findByName( name);
    }    
    
    @Override
    public Country checkIfCountryExists(String name){
         Country tempdev = countryRepository.findByName(name);
        if (tempdev == null) {
            return null;
        }
        return tempdev;
    }    
    

    @Override
    public boolean deleteCountry(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
}
