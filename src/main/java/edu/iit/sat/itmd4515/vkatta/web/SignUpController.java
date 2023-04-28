/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Hotel;
import edu.iit.sat.itmd4515.vkatta.security.Group;
import edu.iit.sat.itmd4515.vkatta.security.GroupService;
import edu.iit.sat.itmd4515.vkatta.security.User;
import edu.iit.sat.itmd4515.vkatta.security.UserService;
import edu.iit.sat.itmd4515.vkatta.service.HotelService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class SignUpController {

    private static final Logger LOG = Logger.getLogger(SignUpController.class.getName());

    private Guest guest;
    private User user;
    private Group group;
    private Hotel hotel;

    @Inject
    SecurityContext securityContext;
    @Inject
    FacesContext facesContext;
    
    @EJB UserService userService;


    public SignUpController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("SignUpController.postConstruct()");
        guest = new Guest();
        guest.setUser(new User());
    }
  
   //Action Method
    public String saveUser(){
        LOG.info("SignUpController.saveUser() -> Saving User on Click"); 
        LOG.info("User is created with all values");
        
        userService.signupNewCustomerUser(guest);
       
        
        //userService.create(user);
        
        //group = groupService.findGroupByName("CUSTOMER_GROUP");
        
        //hotel = hotelService.findHotelByName("Hyatt Place");
        
        //groupService.addUserToGroup(user, group);
       // hotel.setUser(user);
       // user.setEnabled(true);
                
        LOG.info("User is created with all values after saving to database");        
        
        return "login.xhtml?faces-redirect=true";
    }
    
    
    
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
  

}
