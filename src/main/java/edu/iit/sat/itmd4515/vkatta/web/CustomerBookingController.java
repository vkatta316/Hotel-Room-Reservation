/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.config.SendEmailsForClientReservation;
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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class CustomerBookingController implements Serializable {

    private static final Logger LOG = Logger.getLogger(CustomerBookingController.class.getName());

    /**
     *
     */
    public CustomerBookingController() {

    }

    private Guest guest;

    private Room room;

    private Booking booking;

    String bookingTitle;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    String bookingDescription;
    String email;
    String phone;

    @EJB
    GuestService guestService;

    @EJB
    RoomService roomService;

    @EJB
    BookingService bookingService;

    @Inject
    LoginController loginController;
    
  
    @Inject
    SendEmailsForClientReservation emailController;
    
    
    

    @Inject
    FacesContext facesContext;

    @PostConstruct
    private void postContruct() {

        LOG.info("CustomerBookingController.postConstruct() with " + loginController.getAuthenticatedUser());
        guest = guestService.findGuestByUsername(loginController.getAuthenticatedUser());
        LOG.info("\t" + guest.toString());
        booking = new Booking();

    }

    // helper method
    /**
     * Refresh Guest list
     */
    public void refreshGuest() {
        guest = guestService.findGuestByUsername(loginController.getAuthenticatedUser());
    }

    /**
     *
     * @return
     */
    public List<Booking> refreshBooking() {
        return bookingService.findAll();
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
     * Creates booking for client and room is reserved for the client
     *
     * @return client reservation details
     */
    public String saveBooking() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        bookingTitle = request.getParameter("bookingTitle");
        LOG.info("Guest Id" + guest.getId());
        long roomNumber = Long.parseLong(request.getParameter("roomId"));
        LOG.info("Room Id" + roomNumber);
        checkInDate = LocalDate.parse(request.getParameter("checkInDate"));
        LOG.info("checkInDate" + checkInDate);
        checkOutDate = LocalDate.parse(request.getParameter("checkOutDate"));
        bookingDescription = request.getParameter("bookingDescription");
        email = request.getParameter("email");
        phone = request.getParameter("phone");
        LOG.info("CustomerBookingController.saveReservation() -> Create Reservation on Click");
        booking = new Booking(bookingTitle, checkInDate, checkOutDate, bookingDescription, email, phone);
        guestService.createBookingForGuest(booking, guest);
        room = roomService.findById(roomNumber);
        LOG.info("ROOM UPdate" + room);
        guestService.createRoomForGuest(room, guest);

        bookingService.addRoomForBooking(room, booking);
        LOG.info("Reservation is created with all values after saving to database");
        LOG.info("\t" + booking.toString());
        //emailController.connectAndSendSmtp("smtp.gmail.com", "465", "ssl", 
          //      "itmd542@gmail.com", "ajwomlvfoxwsobqk", "vinaykatta316@gmail.com", 
            //    "Confirmation Email", "Your reservation at Hyatt is Confirmed");
        
        //emailController.sendEmail("vinaykatta316@gmail.com", "Confirmation Email", "Your reservation at Hyatt is Confirmed");
        return "/customer/myReservations.xhtml?faces-redirect=true";
    }

    /**
     * Read the Reservation information of client
     */
    public void initBookingById() {
        LOG.info("initBookingById before init " + this.booking.toString());
        this.booking = bookingService.read(booking.getId());
        LOG.info("initBookingById after init " + this.booking.toString());
    }

    /**
     * Modify the reservation
     */
    public void modifyBookingById() {
        LOG.info("initBookingById before init " + this.booking.toString());
        this.booking = bookingService.read(booking.getId());
        LOG.info("initBookingById after init " + this.booking.toString());
        executeCancelButtonClick();
    }

    /**
     * Cancel the reservation
     *
     * @return my reservations
     */
    public String executeCancelButtonClick() {
        LOG.info("CustomerBookingController.executeCancelButtonClick " + this.booking.toString());
        //LOG.info("CustomerBookingController.executeCancelButtonClick " + this.room.toString());
        guestService.removeBookingForGuest(booking, guest);
        bookingService.cancelBooking(booking, guest);
        return "/customer/myReservations.xhtml";
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

    /**
     *
     * @return
     */
    public String getBookingTitle() {
        return bookingTitle;
    }

    /**
     *
     * @param bookingTitle
     */
    public void setBookingTitle(String bookingTitle) {
        this.bookingTitle = bookingTitle;
    }

    /**
     *
     * @return
     */
    public String getBookingDescription() {
        return bookingDescription;
    }

    /**
     *
     * @param bookingDescription
     */
    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    /**
     *
     * @param checkInDate
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     *
     * @return
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    /**
     *
     * @param checkOutDate
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
