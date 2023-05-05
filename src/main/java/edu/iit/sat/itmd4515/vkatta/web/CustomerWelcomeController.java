/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import edu.iit.sat.itmd4515.vkatta.service.BookingService;
import edu.iit.sat.itmd4515.vkatta.service.GuestService;
import edu.iit.sat.itmd4515.vkatta.service.RoomService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 * Customer welcome page
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class CustomerWelcomeController {

    private static final Logger LOG = Logger.getLogger(CustomerWelcomeController.class.getName());

    /**
     *
     */
    public CustomerWelcomeController() {
    }
    
    private Guest guest;
    
    private Room room;
    
    private Booking booking;
    
    
    @EJB GuestService guestService;
    
    
    @EJB BookingService bookingService;
    
    @Inject LoginController loginController;
    
    @Inject
    FacesContext facesContext;
    
    @PostConstruct
    private void postContruct(){
        LOG.info("GuestController.postConstruct() with " + loginController.getAuthenticatedUser());
        guest = guestService.findGuestByUsername(loginController.getAuthenticatedUser());
        List<Booking> booking = bookingService.findAll();
        LOG.info("\t" + booking.toString());
        LOG.info("\t" + guest.toString());
    }
    
     // helper method

    /**
     *
     */
    public void refreshCustomer() {
        guest = guestService.findGuestByUsername(loginController.getAuthenticatedUser());
    }

    /**
     *
     * @return
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     *
     * @param guest
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
    /**
     *
     * @return
     */
    public Room getRoom() {
        return room;
    }

    /**
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     *
     * @return
     */
    public Booking getBooking() {
        return booking;
    }

    /**
     *
     * @param booking
     */
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
