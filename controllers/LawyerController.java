/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
	

package com.example.alma.controllers;

import com.example.alma.models.Application;

import com.example.alma.models.Document;

import com.example.alma.models.Lawyerinfo;

import com.example.alma.models.Media;

import com.example.alma.models.Property;

import com.example.alma.models.RequiredDocuments;

import com.example.alma.models.*;
import com.example.alma.models.User;
import com.example.alma.models.UserServesUser;

import com.example.alma.models.UserServesUserPK;	

import com.example.alma.repositories.UserServesUserRepository;
import com.example.alma.services.ApplicationServiceInterface;
import com.example.alma.services.DocumentServiceInterface;
import com.example.alma.services.FileHandlingInterface;
import com.example.alma.services.LawyerinfoServiceInterface;	

import com.example.alma.services.MediaServiceInterface;
import com.example.alma.services.PropertyServiceInterface;
import com.example.alma.services.RequiredDocumentsServiceInterface;
import com.example.alma.services.RoleServiceInterface;
import com.example.alma.services.*;
import com.example.alma.services.UserServiceInterface;
import com.example.alma.validators.UserValidator;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
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
import org.springframework.web.servlet.view.RedirectView; 



@Controller
public class LawyerController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;     
    
    
    private static final String REGISTER_FORM = "newUser";

    @Autowired
    UserServiceInterface userServiceInterface;
    
    @Autowired
    LawyerinfoServiceInterface lawyerinfoServiceInterface;
    
    @Autowired
    RequiredDocumentsServiceInterface requiredDocumentsServiceInterface; 
    
    @Autowired
    DocumentServiceInterface documentServiceInterface;   
    
    @Autowired
    PropertyServiceInterface propertyServiceInterface;
    
    @Autowired
    MediaServiceInterface mediaServiceInterface;
       

    @Autowired
    ApplicationServiceInterface applicationServiceInterface;       
    
    
    @Autowired
    UserServesUserRepository userServesUserRepository;

    @Autowired
    RoleServiceInterface roleServiceInterface;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    FileHandlingInterface fileHandlingInterface;

	
    @Autowired
    private StripeService paymentsService; 

    @Autowired
    UserValidator userValidator;

    @InitBinder(REGISTER_FORM)
    private void initBinder(final WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/getLawyers")
    public String getLawyers(ModelMap mm,
            @RequestParam(name ="application") int applicationId) {

        List<User> result = userServiceInterface.getLawyers();
        mm.addAttribute("resultLawyers", result);
        mm.addAttribute("applicationId",applicationId);
        return "lawyersList";
    }
    
    @GetMapping("/getApprovedLawyers")
    public String getApprovedLawyers(ModelMap mm,
            @RequestParam(name ="application") int applicationId) {

        List<User> result = userServiceInterface.getLawyers();
        
        List <User> approved = new ArrayList();
        
        for(User u: result){
            if(u.getRequiredDocumentsUploaded().getStatus()==2){
                approved.add(u);
            }
        }         
        mm.addAttribute("resultLawyers", approved);
        mm.addAttribute("applicationId",applicationId);
        return "lawyersList";
    }
    
     @GetMapping("/getLawyersList")
    public String getLawyersList(ModelMap mm) {

        List<User> result = userServiceInterface.getLawyers();
        mm.addAttribute("resultLawyers", result);
        return "lawyersList";
    } 
    
    
     @GetMapping("/getApprovedLawyersList")
    public String getApprovedLawyersList(ModelMap mm) {

        List<User> result = userServiceInterface.getLawyers();
        List <User> approved = new ArrayList();
        
        for(User u: result){
            if(u.getRequiredDocumentsUploaded().getStatus()==2){
                approved.add(u);
            }
        }       
        mm.addAttribute("resultLawyers", approved);
        return "lawyersList";
    }   
    
    
     @GetMapping("/getLawyer")
    public String getLawyer(ModelMap mm,
            @RequestParam(name = "lawyer") int lawyerId,
            @RequestParam(name = "application") int applicationId
    ) {

        User lawyer= userServiceInterface.findUser(lawyerId);
        Application application = applicationServiceInterface.findApplicationById(applicationId);
        mm.addAttribute("lawyer",lawyer);
        mm.addAttribute("application",applicationId);
        mm.addAttribute("app",application);
         mm.addAttribute("stripePublicKey", stripePublicKey); 
        return "lawyerinfo";
    }   
    
     @Transactional
     @PostMapping("/lawyerConfirmation")
    public String lawyerConfirmation(ModelMap mm,
            HttpSession session,
            @RequestParam(name = "lawyer") int lawyerId,
            @RequestParam(name = "application") int applicationId,
            @RequestParam(name = "amount") int amount,
            @RequestParam(name = "stripeEmail") String stripeEmail,
            @RequestParam(name = "stripeToken") String stripeToken,
            RedirectAttributes attrs	
            ) throws StripeException {

        User u=(User) session.getAttribute("user");

        UserServesUser usu = new UserServesUser();
        
        UserServesUserPK upk = new UserServesUserPK();
        upk.setUser1Id(lawyerId);
        upk.setUser2Id(u.getUserId());

        usu.setUserServesUserPK(upk);
        
        java.util.Date utilDate = new Date();

        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        
        
        Application app = new Application();
        app = applicationServiceInterface.findApplicationById(applicationId);
        app.setStatus(2);
        app.getPropertyId().setStatus("Booked");
        applicationServiceInterface.saveApplication(app);
        
        usu.setDatetimeHired(date); 
        usu.setApplicationId(app);
        
        
        userServesUserRepository.save(usu);
        
        
 
         ChargeRequest chargeRequest = new ChargeRequest();
         chargeRequest.setAmount(amount * 100);
         chargeRequest.setStripeEmail(stripeEmail);
         chargeRequest.setStripeToken(stripeToken);
         chargeRequest.setDescription("Payment for lawyer booking");
         chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
         Charge charge = paymentsService.charge(chargeRequest);
         attrs.addFlashAttribute("paymentStatus", charge.getStatus().equalsIgnoreCase("succeeded"));
         attrs.addFlashAttribute("chargeStatus", charge.getStatus());
         attrs.addFlashAttribute("chargeId", charge.getId());
         attrs.addFlashAttribute("balance_transaction", charge.getBalanceTransaction());         
          

        return "redirect:/";
    } 
    
    @ExceptionHandler(StripeException.class)
    public RedirectView handleError(Model model, StripeException ex, RedirectAttributes redir) {
        RedirectView redirectView = new RedirectView("/",true);
        redir.addFlashAttribute("error", true);
        redir.addFlashAttribute("errorMessage", ex.getMessage());
        return redirectView;
    }    
    
    
    
      @GetMapping("/preAddBuyer")
    public String preAddBuyer(ModelMap mm,
            @RequestParam("property") int propertyId,
            HttpSession session,
            @ModelAttribute("parserror") String error) {
        
        User user= (User) session.getAttribute("user");
        
        mm.addAttribute("newApplication", new Application());
        mm.addAttribute("property", propertyId);
         
        mm.addAttribute("parserror", error);
        return "uploadBuyer";
       
    } 
    
    
      @PostMapping("/addBuyer")
    public String addBuyer(ModelMap mm,
            @ModelAttribute("newApplication") Application application,
            @RequestParam("property") int propertyId,
            @RequestParam("filename1") MultipartFile filename1,
            @RequestParam("filename2") MultipartFile filename2,
            HttpSession session
            ) {

        Random r = new Random();
        int id;
        
         java.util.Date utilDate = new Date();
        // Convert it to java.sql.Date
        java.sql.Date date = new java.sql.Date(utilDate.getTime()); 
        
        Property property = propertyServiceInterface.findPropertyById(propertyId);
        
        int p=property.getPropertyId();
        
        User u=(User) session.getAttribute("user");
        Media passport = new Media();
        passport.setPath(fileHandlingInterface
                .storeFileToDisk(filename1, u.getUsername()+ r.nextInt()));
        passport.setPropertyId(property);
        passport.setType(12);
         mediaServiceInterface.saveMedia(passport);
        
         
        
        Media maritalCertificate = new Media();
        maritalCertificate.setPath(fileHandlingInterface
                .storeFileToDisk(filename1, u.getUsername()+ r.nextInt()));
        maritalCertificate.setPropertyId(property);
        maritalCertificate.setType(13);
        mediaServiceInterface.saveMedia(maritalCertificate);
         
         application.setPropertyId(property);
         application.setUserId(u);
         application.setStatus(1);
         application.setDateOfApplication(date);
         
         id=applicationServiceInterface.saveApplication(application);
         
        return "redirect:getApprovedLawyers?application="+id;
    }    

    
      @GetMapping("/approveLawyer")
    public String approveLawyer(ModelMap mm,
            HttpSession session,
            @RequestParam (name="id") int id){
        
        User user = userServiceInterface.findUserById(id);

        user.getRequiredDocumentsUploaded().setStatus(2);

        userServiceInterface.saveUser(user);       
       
        return "redirect:getLawyerInfoAdmin?id="+id;
    } 
    
    
    

    @GetMapping("/preAddLawyer")
    public String preAddLawyer(ModelMap mm,
            @ModelAttribute("parserror") String error) {

        mm.addAttribute("newLawyer", new Lawyerinfo());
        mm.addAttribute("newDocument", new Document());
        mm.addAttribute("parserror", error);
        return "uploadLawyer";
    }

    @PostMapping("/addLawyer")
    public String addLawyer(ModelMap mm,
            @Valid @ModelAttribute("newLawyer") Lawyerinfo lawyerinfo,
            BindingResult bindingResult,
            @ModelAttribute("newDocument") Document document,
            @RequestParam("filename") MultipartFile filename,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        boolean redirect =false;
        User u;
        int id;
         u=(User) session.getAttribute("user");


        RequiredDocuments requiredDocs = new RequiredDocuments();  
        requiredDocs.setStatus(1);
        requiredDocumentsServiceInterface.saveRequiredDocument(requiredDocs);
        
        document.setRequiredDocumentsId(requiredDocs);
        u.setRequiredDocumentsUploaded(requiredDocs);

        
        Random r = new Random();
        document.setDescription("Passport/ID");
        String path = u.getUsername()+ r.nextInt();
        document.setMediaPath(fileHandlingInterface
                .storeFileToDisk(filename, path));
        
        documentServiceInterface.saveDocument(document);
        
         
  
         id=lawyerinfoServiceInterface.saveLawyerinfo(lawyerinfo);
         u.setLawyerinfoId(lawyerinfo);
         
         userServiceInterface.saveUser(u);

         return "redirect:information";
    }
    
    
     @GetMapping("/getYourBookings")
    public String getYourBookings(Pageable pageable,ModelMap mm ,HttpSession session) {
 
          List <Application> applicationList = applicationServiceInterface.getApplicationsByStatus(2);
          User user = (User) session.getAttribute("user");
         List<UserServesUser> myClients;
         List<UserServesUser> myClientsApplications = new ArrayList();
         List<UserServesUser> result;
         List <Application>filteredApplications = new ArrayList();

         
         UserServesUserPK usuk = new UserServesUserPK();
         usuk.setUser1Id(user.getUserId());
         usuk.setUser2Id(150);

          
          
          myClients = userServesUserRepository.findByUser1Id(user.getUserId());
          
          List <UserServesUser> myBookings = new ArrayList();
          List <Application> myApplications = new ArrayList();
          

          
          for(UserServesUser client: myClients){
            
               if(applicationList.contains(client.getApplicationId())){
                   myClientsApplications.add(client);
               }         
              
        } 

          mm.addAttribute("data", myClientsApplications);
          return "clientbookings";              
 
    }    
    
    

    
    
     @GetMapping("/addProperty3")
    public String addProperty3() {
        return "upload";
    } 
    
    
     @GetMapping("/propertyDetail1")
    public String propertyDetail1() {
        return "propertySingle";
    } 
    
     @GetMapping("/information1")
    public String information1() {
        return "info";
    }

     @GetMapping("/logout1")
    public String logout1(HttpSession session,SessionStatus status) {
        status.setComplete();
        session.invalidate();
        return "redirect:/";
    }    
    

    @ResponseBody
    @GetMapping("checkUsername1/{name}")
    public String checkUsername1(@PathVariable("name") String name) {
        return userServiceInterface.checkIfUsernameExists(name);
    }

    @ResponseBody
    @GetMapping("checkEmail1/{email}")
    public String checkEmail1(@PathVariable("email") String email) {
        return userServiceInterface.checkIfEmailExists(email);
    }

}
