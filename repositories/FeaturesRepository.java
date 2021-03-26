/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.repositories;

import com.example.alma.models.Features;
import com.example.alma.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gianalex
 */
@Repository
public interface FeaturesRepository extends JpaRepository<Features, Integer>{
 
    public Features findByPropertyId(Property property);
    
}