/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.repositories;

import com.example.alma.models.Property;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alex
 */

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer>{


    @Query("SELECT p FROM Property p WHERE " +
            "p.price <=:maxprice AND p.price >=:minprice AND "+
            "p.rooms >=:minRooms AND p.bathrooms >=:minBaths AND "+
            "p.type like :propType"
    )
    List<Property> findProperties(@Param("maxprice") double maxprice,           
            @Param("minprice") double minprice,
            @Param("minRooms") int minRooms,@Param("minBaths") int minBaths,
            @Param("propType") String propType);
    
    
        @Query("SELECT p FROM Property p WHERE " +
            "p.price <=:maxprice AND p.price >=:minprice AND "+
            "p.rooms >=:minRooms AND p.bathrooms >=:minBaths AND "+
            "p.type like :propType AND p.area >=:minArea"
    )
    List<Property> findPropertiesMinArea(@Param("maxprice") double maxprice,           
            @Param("minprice") double minprice,
            @Param("minRooms") int minRooms,@Param("minBaths") int minBaths,
            @Param("propType") String propType,@Param("minArea") int minArea);
    
    
        @Query("SELECT p FROM Property p WHERE " +
            "p.price <=:maxprice AND p.price >=:minprice AND "+
            "p.rooms >=:minRooms AND p.bathrooms >=:minBaths AND "+
            "p.type like :propType AND p.area <=:maxArea"
    )          
    List<Property> findPropertiesMaxArea(@Param("maxprice") double maxprice,           
            @Param("minprice") double minprice,
            @Param("minRooms") int minRooms,@Param("minBaths") int minBaths,
            @Param("propType") String propType,@Param("maxArea") int maxArea); 
    
    
        @Query("SELECT p FROM Property p WHERE " +
            "p.price <=:maxprice AND p.price >=:minprice AND "+
            "p.rooms >=:minRooms AND p.bathrooms >=:minBaths AND "+
            "p.type like :propType AND p.area >=:minArea AND p.area <=:maxArea"
    )
    List<Property> findPropertiesAll(@Param("maxprice") double maxprice,           
            @Param("minprice") double minprice,
            @Param("minRooms") int minRooms,@Param("minBaths") int minBaths,
            @Param("propType") String propType,@Param("minArea") int minArea,@Param("maxArea") int maxArea);    
    
    

    
    public Property findByPropertyId(int propertyId);
    
    public List<Property> findTop6ByOrderByDatetimeUploadedDesc();
    
    public List<Property> findTop2ByOrderByDatetimeUploadedDesc();
    
    
    public List<Property> findTop4ByOrderByPriceDesc();
}

