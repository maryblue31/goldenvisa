/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.controllers;

import com.example.alma.dto.MediaDTO;
import com.example.alma.models.City;
import com.example.alma.models.Document;
import com.example.alma.models.Features;
import com.example.alma.models.Media;
import com.example.alma.models.Property;
import com.example.alma.models.RequiredDocuments;
import com.example.alma.models.User;
import com.example.alma.repositories.PropertyRepository;
import com.example.alma.services.CityServiceInterface;
import com.example.alma.services.CountryServiceInterface;
import com.example.alma.services.DocumentServiceInterface;
import com.example.alma.services.FeaturesServiceInterface;
import com.example.alma.services.FileHandlingInterface;
import com.example.alma.services.MediaServiceInterface;
import com.example.alma.services.PropertyServiceInterface;
import com.example.alma.services.RequiredDocumentsServiceInterface;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author alex
 */
@Controller
public class PropertyController {
   
    @Autowired
    PropertyServiceInterface propertyServiceInterface;
    
    @Autowired
    CityServiceInterface cityServiceInterface;

    @Autowired
    CountryServiceInterface countryServiceInterface; 
    
    @Autowired
    RequiredDocumentsServiceInterface requiredDocumentsServiceInterface;
    
    
    @Autowired
    DocumentServiceInterface documentServiceInterface; 
    
    @Autowired
    MediaServiceInterface mediaServiceInterface;   
    
    
    @Autowired
    FeaturesServiceInterface featuresServiceInterface;       
    
    @Autowired
    FileHandlingInterface fileHandlingInterface;   
    
      
    
    @GetMapping("/preAddProperty")
    public String showAddPropertyForm(ModelMap mm,
            @ModelAttribute("parserror") String error) {

        mm.addAttribute("newProperty", new Property());
        mm.addAttribute("newMediaDTO", new MediaDTO());
        mm.addAttribute("newDocument", new Document());
        mm.addAttribute("newFeatures", new Features());
        mm.addAttribute("parserror", error);
        return "upload";
    }  
    
    
    @PostMapping("/addProperty")
    public String addProperty(ModelMap mm,
            @ModelAttribute("newProperty") Property property,
            @ModelAttribute("newDocument") Document document,
            @ModelAttribute("newFeatures") Features features,
            @ModelAttribute("newMediaDTO") MediaDTO mediaDTO,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
            boolean redirect =false;
            int id;
            
           
        if(redirect){
            return "redirect:preAddProperty";
        }
        
        
        
        
        City returnedCity = cityServiceInterface.checkIfCityExists(property.getCityId().getName());       
        if(returnedCity==null){
           property.getCityId().setCountryId(countryServiceInterface.getCountry("Greece"));
            cityServiceInterface.saveCity(property.getCityId());
        }
        else{
            property.setCityId(returnedCity);
        }

        RequiredDocuments requiredDocs = new RequiredDocuments();  
        requiredDocs.setStatus(1);
        requiredDocumentsServiceInterface.saveRequiredDocument(requiredDocs);
        
        document.setRequiredDocumentsId(requiredDocs);
        property.setRequiredDocumentsUploaded(requiredDocs);
        
        Random r = new Random();
        document.setDescription("Certificate Of Ownership");
        String path = property.getOwnerId().getUsername() + r.nextInt();
        document.setMediaPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilenameTypical6(), path));
        
        documentServiceInterface.saveDocument(document);
        
        
        property.setStatus("Uploaded");
        
        java.util.Date utilDate = new Date();
        // Convert it to java.sql.Date
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        
        property.setDatetimeUploaded(date);
        property.setDatetimeUpdated(date);


        id=propertyServiceInterface.saveProperty(property);
        
        Media livingRoom =new Media();
        Media kitchen =new Media();
        Media bedroom =new Media();
        Media bathroom =new Media();
        Media outdoor =new Media();
        Media livingRoom360 =new Media();
        Media kitchen360 =new Media();
        Media bedroom360 =new Media();
        Media bathroom360 =new Media();
        Media outdoor360 =new Media();       
        
        List<Media> mediaList = new ArrayList<Media>();
        

        
        livingRoom.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilenameTypical1(), property.getOwnerId().getUsername() + r.nextInt()));             
         livingRoom.setPropertyId(property);
         livingRoom.setType(1);
         mediaServiceInterface.saveMedia(livingRoom);
         
         livingRoom360.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilename1(), property.getOwnerId().getUsername() + r.nextInt()));             
         livingRoom360.setPropertyId(property);
         livingRoom360.setType(2);
         mediaServiceInterface.saveMedia(livingRoom360);         
          
         kitchen.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilenameTypical2(), property.getOwnerId().getUsername() + r.nextInt()));             
         kitchen.setPropertyId(property);
         kitchen.setType(3);
         mediaServiceInterface.saveMedia(kitchen);
         
         kitchen360.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilename2(), property.getOwnerId().getUsername() + r.nextInt()));             
         kitchen360.setPropertyId(property);
         kitchen360.setType(4);
         mediaServiceInterface.saveMedia(kitchen360);         
         
         
         bedroom.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilenameTypical3(), property.getOwnerId().getUsername() + r.nextInt()));             
         bedroom.setPropertyId(property);
         bedroom.setType(5);
         mediaServiceInterface.saveMedia(bedroom);
         
         bedroom360.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilename3(), property.getOwnerId().getUsername() + r.nextInt()));             
         bedroom360.setPropertyId(property);
         bedroom360.setType(6);
         mediaServiceInterface.saveMedia(bedroom360);         
         
         bathroom.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilenameTypical4(), property.getOwnerId().getUsername() + r.nextInt()));             
         bathroom.setPropertyId(property);
         bathroom.setType(7);
         mediaServiceInterface.saveMedia(bathroom);
         
         bathroom360.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilename4(), property.getOwnerId().getUsername() + r.nextInt()));             
         bathroom360.setPropertyId(property);
         bathroom360.setType(8);
         mediaServiceInterface.saveMedia(bathroom360);
         
         outdoor.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilenameTypical5(), property.getOwnerId().getUsername() + r.nextInt()));             
         outdoor.setPropertyId(property);
         outdoor.setType(9);
         mediaServiceInterface.saveMedia(outdoor);
         
         outdoor360.setPath(fileHandlingInterface
                .storeFileToDisk(mediaDTO.getFilename5(), property.getOwnerId().getUsername() + r.nextInt()));             
         outdoor360.setPropertyId(property);
         outdoor360.setType(10);
         mediaServiceInterface.saveMedia(outdoor360);         
                 
         mediaList.add(livingRoom);
         mediaList.add(kitchen);         
         mediaList.add(bedroom);
         mediaList.add(bathroom);         
         mediaList.add(outdoor); 
         mediaList.add(livingRoom360);
         mediaList.add(kitchen360);         
         mediaList.add(bedroom360);
         mediaList.add(bathroom360);         
         mediaList.add(outdoor360);         
         
         property.setMediaCollection(mediaList);
         
         short ii=0;
         
        features.setPropertyId(property);
        features.setAirconditioning(mediaDTO.getAirconditioning());
        features.setLaundry(mediaDTO.getLaundry());
        features.setRefrigerator(mediaDTO.getRefrigerator());
        features.setWasher(mediaDTO.getWasher());
        features.setBarbeque(mediaDTO.getBarbeque());
        features.setLawn(mediaDTO.getLawn());
        features.setSauna(mediaDTO.getSauna());
        features.setDryer(mediaDTO.getDryer());
        features.setSwimmingpool(mediaDTO.getSwimmingpool());
        features.setWindowcoverings(mediaDTO.getWindowcoverings());
        features.setGym(mediaDTO.getGym());
        features.setOutdoorshower(mediaDTO.getOutdoorshower());
        features.setTvcable(mediaDTO.getTvcable());
        features.setTennis(mediaDTO.getTennis());
        features.setGolf(mediaDTO.getGolf());
        

        featuresServiceInterface.saveFeatures(features);  
        

         return "redirect:getProperty?property="+id;//+id;
    }    
    
    
    
    
    
    @GetMapping("/getProperties")
    public String showProperties(ModelMap mm) {

        List<Property> result = propertyServiceInterface.getProperties();
        mm.addAttribute("resultProperties", result);

        return "propertiesList";
    } 
    
    
    

    
     @GetMapping("/getPropertyList")
    public String propertyPageable(Pageable pageable,ModelMap mm) {
		
              
          Page<Property> pages = propertyServiceInterface.getPages(pageable);
          mm.addAttribute("number", pages.getNumber());
          mm.addAttribute("totalPages", pages.getTotalPages());
          mm.addAttribute("totalElements", pages.getTotalElements());
     
          mm.addAttribute("data", pages.getContent());
          return "propertiesListPaging";              
                
    } 
    
    
     @GetMapping("/getRecentProperties")
    public String getRecentProperties(ModelMap mm) {

        List<Property> result = propertyServiceInterface.getRecentProperties();
          mm.addAttribute("data", result);         

        return "propertiesListPaging";
    }   
    
     @GetMapping("/getProperty")
    public String getProperty(ModelMap mm,
            @RequestParam(name = "property") int propertyId) {

        Property property= propertyServiceInterface.findProperty(propertyId);
        Features features = featuresServiceInterface.findFeaturesById(property);
        
        List<Property> recent = new ArrayList();
        recent= propertyServiceInterface.getRecentTwoProperties();        
        
        mm.addAttribute("newProperty",property);
        mm.addAttribute("newFeatures", features);
        mm.addAttribute("recent", recent);
        

        

        return "propertySingle";
    }   
    
    
    
    @GetMapping("/getSubmittedProperty")
    public String showProperty(HttpServletRequest request,ModelMap mm ) {

     
     Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
    if (inputFlashMap != null) {
         List<Property> recent = new ArrayList();
        recent= propertyServiceInterface.getRecentTwoProperties();   
        mm.addAttribute("recent",recent);       
       return "propertySingle";
    }
    else{
        return "redirect:getPropertyList";
    }    
    } 

    @GetMapping("/getPropertyDetail")
    public String showPropertyInDetail(HttpServletRequest request,ModelMap mm ) {

        List<Property> recent = new ArrayList();
        recent= propertyServiceInterface.getRecentTwoProperties();   
        mm.addAttribute("recent",recent);
       return "propertySingle";
   
    }
    
    
}
