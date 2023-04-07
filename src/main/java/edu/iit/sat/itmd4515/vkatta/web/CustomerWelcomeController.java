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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class CustomerWelcomeController {

    private static final Logger LOG = Logger.getLogger(CustomerWelcomeController.class.getName());

    public CustomerWelcomeController() {
    }
    
    private Guest guest;
    
    private Room room;
    
    private Booking booking;
    
    List<Room> availableRooms;
    List<Booking> reservation;
    
    
    String checkInDate;
    String checkOutDate;
    
    @EJB GuestService guestService;
    
    @EJB RoomService roomService;
    
    @EJB BookingService bookingService;
    
    @Inject LoginController loginController;
    
    @Inject
    FacesContext facesContext;
    
    @PostConstruct
    private void postContruct(){
        LOG.info("GuestController.postConstruct() with " + loginController.getAuthenticatedUser());
        guest = guestService.findGuestByUsername(loginController.getAuthenticatedUser());
        
        LOG.info("\t" + guest.toString());
    }
    
     
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    
    //action methods
    public String doGet() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
       
        checkInDate = request.getParameter("checkInDate");
        checkOutDate = request.getParameter("checkOutDate");
        
        availableRooms = roomService.findAll();
        
        System.out.println("edu.iit.sat.itmd4515.vkatta.web.CustomerWelcomeController.doGet()" + availableRooms.size());
        reservation = bookingService.findAll();
         System.out.println("edu.iit.sat.itmd4515.vkatta.web.CustomerWelcomeController.doGet()" + reservation.size());
        
        for (int i = 0; i < reservation.size(); i++) {
            if (
                    (isDateAfterThan(checkInDate, reservation.get(i).getBookingFromDate().toString()) && 
                    isDateAfterThan(reservation.get(i).getBookingToDate().toString(), checkOutDate))
                    ||
                    (isDateAfterThan(reservation.get(i).getBookingFromDate().toString(), checkInDate) && 
                    isDateAfterThan(checkOutDate, reservation.get(i).getBookingToDate().toString()))
                    ||
                    (isDateAfterThan(checkInDate, reservation.get(i).getBookingFromDate().toString()) && 
                    isDateAfterThan(checkOutDate, reservation.get(i).getBookingToDate().toString()))
                    ||
                    (isDateAfterThan(reservation.get(i).getBookingFromDate().toString(), checkInDate) && 
                    isDateAfterThan(reservation.get(i).getBookingToDate().toString(), checkOutDate))
                ) {
                for(int j = 0; j < availableRooms.size(); j++) {
                    LOG.info("AAAA" + reservation.get(i).getRoom().getRoomNumber().toString());
                    LOG.info("VVVV" + availableRooms.get(j).getId().toString());
                    if(reservation.get(i).getRoom().toString().equals( availableRooms.get(j).getId().toString())) {
                        availableRooms.remove(j);
                        break;
                    }
                }
            }
        }
        LOG.info("edu.iit.sat.itmd4515.vkatta.web.CustomerWelcomeController.doGet() VK" + availableRooms);
        return "/customer/welcome.xhtml";
    }
    
    
    //helper methods
    private boolean isDateAfterThan(String firstDate, String secondDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = sdf.parse(firstDate);
            Date date2 = sdf.parse(secondDate);
            return date1.compareTo(date2) > 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
