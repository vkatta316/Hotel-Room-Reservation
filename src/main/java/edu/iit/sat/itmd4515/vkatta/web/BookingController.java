/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.service.BookingService;
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
public class BookingController {

    private static final Logger LOG = Logger.getLogger(BookingController.class.getName());
    
    private Booking booking;
    
    
    @EJB BookingService bookingService;
    
    @Inject CustomerWelcomeController cwc;


    
    public BookingController() {
    }
    
    @PostConstruct
    private void postContruct(){
        LOG.info("BookingController.postConstruc() -> Initializing Booking Model");
        booking = new Booking();
    }
    
    //Action Method
    public String saveBooking(){
        LOG.info("BookingController.saveBooking() -> Saving Booking Application on Click"); 
        LOG.info("Booking is created with all values");
        LOG.info("\t" + booking.toString());
        
        bookingService.create(booking);
        
        //guestService.addRoomForCustomer(cwc.getRoom(), guest);
        
        LOG.info("Booking is created with all values after saving to database");        
        LOG.info("\t" + booking.toString());
        
        return "/customer/confirmation.xhtml";
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    
}
