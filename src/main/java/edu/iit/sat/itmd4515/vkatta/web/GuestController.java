/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.service.GuestService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class GuestController {

    private static final Logger LOG = Logger.getLogger(GuestController.class.getName());
    
    private Guest guest;
    
    @EJB GuestService guestService;
    
    @Inject CustomerWelcomeController cwc;


    
    public GuestController() {
    }
    
    @PostConstruct
    private void postContruct(){
        LOG.info("GuestController.postConstruc() -> Initializing Guest Model");
        guest = new Guest();
    }
    
    //Action Method
    public String saveGuest(){
        LOG.info("GuestController.saveGuest() -> Saving Guest Application on Click"); 
        LOG.info("Guest is created with all values");
        LOG.info("\t" + guest.toString());
        
        guestService.create(guest);
        
        //guestService.addRoomForCustomer(cwc.getRoom(), guest);
        
        LOG.info("Guest is created with all values after saving to database");        
        LOG.info("\t" + guest.toString());
        
        return "/customer/confirmation.xhtml";
    }
    
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
