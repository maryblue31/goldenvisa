/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.alma.controllers;

import com.example.alma.models.Chat;
import com.example.alma.models.City;
import com.example.alma.models.Country;
import com.example.alma.models.Role;
import com.example.alma.models.User;
import com.example.alma.services.ChatServiceInterface;
import com.example.alma.services.CityServiceInterface;
import com.example.alma.services.CountryServiceInterface;
import com.example.alma.services.FileHandlingInterface;
import com.example.alma.services.PropertyServiceInterface;
import com.example.alma.services.RoleServiceInterface;
import com.example.alma.services.UserServiceInterface;
import com.example.alma.validators.UserValidator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author alex
 */
@Controller
public class UserController {

    private static final String REGISTER_FORM = "newUser";

    @Autowired
    UserServiceInterface userServiceInterface;

    @Autowired
    RoleServiceInterface roleServiceInterface;
    
    @Autowired
    CityServiceInterface cityServiceInterface; 
    
    
    @Autowired
    ChatServiceInterface chatServiceInterface;     
    
     @Autowired
    PropertyServiceInterface propertyServiceInterface;    
    
    @Autowired
    CountryServiceInterface countryServiceInterface;    

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FileHandlingInterface fileHandlingInterface;



    @Autowired
    UserValidator userValidator;

    @InitBinder(REGISTER_FORM)
    private void initBinder(final WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/getUsers")
    public String showUsers(ModelMap mm) {

        List<User> result = userServiceInterface.getUsers();
        mm.addAttribute("resultusers", result);

        return "users";
    }

    @GetMapping("/preRegister")
    public String showRegistrationForm(ModelMap mm,
            @ModelAttribute("parserror") String error) {

        mm.addAttribute("newUser", new User());
        mm.addAttribute("allRoles", roleServiceInterface.getRolesWithoutAdmin());
        mm.addAttribute("parserror", error);
        mm.addAttribute("registerAttribute", "true");
        return "indexRegister";
    }

    @PostMapping("/registerUser")
    public String registerUser(ModelMap mm,
            @Valid @ModelAttribute("newUser") User user,
            BindingResult bindingResult,
            @RequestParam("secondPassword") String secondPassword,
            @RequestParam("avatarFilename") MultipartFile avatarFilename,
            @RequestParam("city") String city,
            @RequestParam("country") String country,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        boolean redirect =false;

        if (bindingResult.hasErrors()) {
            mm.addAttribute("allRoles", roleServiceInterface.getRolesWithoutAdmin());
            mm.addAttribute("registerAttribute", "true");
            return "indexRegister";
        } 
 
         if(userServiceInterface.checkIfUsernameExists(user.getUsername())!=null){
            redirectAttributes.addFlashAttribute("parserrorUsername", "The username "+ user.getUsername()+" already exist");
            redirect=true;
        }
        if(userServiceInterface.checkIfEmailExists(user.getEmail())!=null){
            redirectAttributes.addFlashAttribute("parserrorEmail", "The email "+ user.getEmail()+" already exist");
            redirect=true;
        }        
        
        if (!user.getPassword().equals(secondPassword)) {
            redirectAttributes.addFlashAttribute("parserrorPassword", "The passwords you have given are different");
            redirect=true;
        }
        if(redirect){
            return "redirect:preRegister";
        }
        Country returnedCountry = countryServiceInterface.checkIfCountryExists(country);
        City returnedCity = cityServiceInterface.checkIfCityExists(city);
        if(returnedCountry==null){
            //save country
            Country c =new Country();
            c.setName(country);
            returnedCountry=countryServiceInterface.saveCountry(c);
            
            City cityOfCountry = new City();
            cityOfCountry.setCountryId(c);
            cityOfCountry.setName(city);
            returnedCity=cityServiceInterface.saveCity(cityOfCountry);
        }       
        else if(returnedCity==null){
            //save city
            City cityOfCountry = new City();
            cityOfCountry.setCountryId(returnedCountry);
            cityOfCountry.setName(city);
            returnedCity=cityServiceInterface.saveCity(cityOfCountry);            
        }         
        
        user.setCurrentLocation(returnedCity);
        
        user.setPassword(passwordEncoder.encode(secondPassword));
        Random r = new Random();
        String imagename = user.getUsername() + r.nextInt();
        user.setAvatar(fileHandlingInterface
                .storeFileToDisk(avatarFilename, imagename));

        userServiceInterface.saveUser(user);
        session.setAttribute("user",user);
        
        if(user.getRoleId().getRolename().equals("lawyer")){
            return "redirect:preAddLawyer";
        }
        
         return "redirect:information";
    }

    @GetMapping("/preLogin")
    public String showLoginForm(ModelMap mm,
            @ModelAttribute("parserror") String error) {

        mm.addAttribute("parserror", error);
        mm.addAttribute("loginAttribute", "true");
        return "indexLogin";
    }

    @PostMapping("/loginUser")
    public String loginUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {


        User user = userServiceInterface.findUsername(username);
        
        if(user !=null){
                if(!passwordEncoder.matches(password, user.getPassword())){
                    redirectAttributes.addFlashAttribute("parserror", "The username or password is wrong. Please repeat the process");
                    return "redirect:preLogin";                 
                }
        }
        else if(user ==null){
            redirectAttributes.addFlashAttribute("parserror", "The username or password is wrong. Please repeat the process");
            return "redirect:preLogin";
        } 
        
        session.setAttribute("user",user);
        
        return "redirect:/";
    }
    
      @GetMapping("/getUserDetail")
    public String getUserDetail(ModelMap mm,HttpSession session,
            @RequestParam("id") int id,
            @RequestParam("property") int propertyId) {

        User user = userServiceInterface.findUserById(id);
        mm.addAttribute("user",user);
        mm.addAttribute("property", propertyServiceInterface.findPropertyById(propertyId));
        User lawyer = (User) session.getAttribute("user");
        List<Chat> chatList =new ArrayList();
        chatList=chatServiceInterface.findUser1IdAndUser2Id(lawyer, user);
        int i=0;
        
        if(chatList.isEmpty()){
              mm.addAttribute("conversation",new Chat());  
        }        
        return "sellerinfo";
        
    }
    
    
      @GetMapping("/getBuyerDetail")
    public String getBuyerDetail(ModelMap mm,
            HttpSession session,
            @RequestParam("id") int id,
            @RequestParam("property") int propertyId) {
        
        User user = userServiceInterface.findUserById(id);
        mm.addAttribute("user",user);
        mm.addAttribute("property", propertyServiceInterface.findPropertyById(propertyId));
        User lawyer = (User) session.getAttribute("user");
        List<Chat> chatList =new ArrayList();
        chatList=chatServiceInterface.findUser1IdAndUser2Id(lawyer, user);
        int i=0;
        
        if(chatList.isEmpty()){ 
              mm.addAttribute("conversation",new Chat());  
        }
        return "buyerinfo";
    } 
    
    
      @GetMapping("/getBuyerInfoAdmin")
    public String getBuyerInfoAdmin(ModelMap mm,
            HttpSession session,
            @RequestParam("id") int id) {
        
        User user = userServiceInterface.findUserById(id);
        mm.addAttribute("user",user);

        return "buyeradmin";
    } 

      @GetMapping("/getSellerInfoAdmin")
    public String getSellerInfoAdmin (ModelMap mm,
            HttpSession session,
            @RequestParam("id") int id) {
        
        User user = userServiceInterface.findUserById(id);
        mm.addAttribute("user",user);

        return "selleradmin";
    }   
    
    
       @GetMapping("/getLawyerInfoAdmin")
    public String getLawyerInfoAdmin (ModelMap mm,
            HttpSession session,
            @RequestParam("id") int id) {
        
        User user = userServiceInterface.findUserById(id);
        mm.addAttribute("user",user);

        return "lawyeradmin";
    }    
    
    

    @GetMapping("/showMainPage")
    public String showMainPage() {
        return "mainPage";
    }
    
     @GetMapping("/showAdminPage")
    public String showAdminPage() {
        return "adminPage";
    }
     @GetMapping("/showBuyers")
    public String showBuyers(ModelMap mm) {
        
        Role buyer = roleServiceInterface.findByRolename("buyer");
        
        List<User> result = userServiceInterface.findByRoleId(buyer);
        mm.addAttribute("data", result);
        
        return "adminBuyers";
    }
    
     @GetMapping("/showLawyers")
    public String showLawyers(ModelMap mm) {
        
        List<User> result = userServiceInterface.getLawyers();
        mm.addAttribute("data", result);        
        
        return "adminLawyers";
    }
    
     @GetMapping("/showSellers")
    public String showSellers(ModelMap mm) {
        
         Role seller = roleServiceInterface.findByRolename("seller");
        
        List<User> result = userServiceInterface.findByRoleId(seller);
        mm.addAttribute("data", result);       
        return "adminSellers";
    }
    
    
     @GetMapping("/showStatistics")
    public String showStatistics() {
        return "statistics";
    }   
    
    
    @GetMapping("/showWelcomePage")
    public String showWelcomePage() {
        return "welcome";
    }    
    
     @GetMapping("/addProperty2")
    public String addProperty2() {
        return "upload";
    } 
    
    
     @GetMapping("/propertyDetail")
    public String propertyDetail() {
        return "propertySingle";
    } 
    
     @GetMapping("/information")
    public String information() {
        return "info";
    }

     @GetMapping("/logout")
    public String logout(HttpSession session,SessionStatus status) {
        status.setComplete();
        session.invalidate();
        return "redirect:/";
    }    
    

    @ResponseBody
    @GetMapping("checkUsername/{name}")
    public String checkUsername(@PathVariable("name") String name) {
        return userServiceInterface.checkIfUsernameExists(name);
    }

    @ResponseBody
    @GetMapping("checkEmail/{email}")
    public String checkEmail(@PathVariable("email") String email) {
        return userServiceInterface.checkIfEmailExists(email);
    }
       
    
    

}
