/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.BookingType;
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
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class CustomerBookingController implements Serializable{

    private static final Logger LOG = Logger.getLogger(CustomerBookingController.class.getName());

    public CustomerBookingController() {
        
    }
    
    private Guest guest;
    
    private Room room;
    
    private Booking booking;
    
    List<Room> availableRooms;
    List<Booking> reservation;
    
    String bookingTitle;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String bookingDescription;
    String email;
    String phone;
    
    
    @EJB GuestService guestService;
    
    @EJB RoomService roomService;
    
    @EJB BookingService bookingService;
    
    @Inject LoginController loginController;
    
    @Inject
    FacesContext facesContext;
    
    @PostConstruct
    private void postContruct(){
        
        LOG.info("CustomerBookingController.postConstruct() with " + loginController.getAuthenticatedUser());
        guest = guestService.findGuestByUsername(loginController.getAuthenticatedUser());
        
        LOG.info("\t" + guest.toString());
        
        
    }
    
     
    public Guest getGuest() {
        return guest;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
   
    public String doPost() {
         HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        bookingTitle = request.getParameter("bookingTitle");
        
        LOG.info("Guest Id" + guest.getId());
        
        int roomNumber = Integer.parseInt(request.getParameter("roomId"));
        LOG.info("Room Id" + roomNumber);
        checkInDate = LocalDate.parse(request.getParameter("checkInDate"));
        LOG.info("checkInDate" +checkInDate);
        checkOutDate = LocalDate.parse(request.getParameter("checkOutDate"));
        bookingDescription = request.getParameter("bookingDescription");
        email = request.getParameter("email");
        phone = request.getParameter("phone");
        
        LOG.info("CustomerBookingController.saveReservation() -> Create Reservation on Click"); 
        
        
        booking = new Booking(bookingTitle, checkInDate, checkOutDate, bookingDescription, email, phone);
        bookingService.create(booking);
        //bookingService.addBookingForCustomer(booking, guest);
        
        //guestService.addRoomForCustomer(cwc.getRoom(), guest);
        
        LOG.info("Reservation is created with all values after saving to database");        
        LOG.info("\t" + booking.toString());
        
        return "/customer/confirmation.xhtml";
        
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

    public String getBookingTitle() {
        return bookingTitle;
    }

    public void setBookingTitle(String bookingTitle) {
        this.bookingTitle = bookingTitle;
    }

   
    public String getBookingDescription() {
        return bookingDescription;
    }

    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
