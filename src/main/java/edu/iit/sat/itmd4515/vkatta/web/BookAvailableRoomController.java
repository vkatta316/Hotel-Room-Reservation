/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import edu.iit.sat.itmd4515.vkatta.service.BookingService;
import edu.iit.sat.itmd4515.vkatta.service.RoomService;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@SessionScoped
public class BookAvailableRoomController implements Serializable {

    private static final Logger LOG = Logger.getLogger(BookAvailableRoomController.class.getName());

    List<Room> availableRooms;
    List<Booking> reservation;

    String checkInDate;
    String checkOutDate;
    Double price;
    String roomNumber;
    Double totalPrice;
    Integer numberOfDays;
    
    @EJB
    RoomService roomService;

    @EJB
    BookingService bookingService;

    @Inject
    FacesContext facesContext;

    @PostConstruct
    private void postContruct() {

    }

    //action methods
    public String doGet() {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        roomNumber = request.getParameter("roomNumber");
        LOG.info("roomNumber" +roomNumber);
        checkInDate = request.getParameter("checkInDate");
        checkOutDate = request.getParameter("checkOutDate");
        price = Double.valueOf(request.getParameter("price"));
        
        try {
            calculateDays();
        } catch (ParseException ex) {
            Logger.getLogger(BookAvailableRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/customer/reservation.xhtml";
    }
    
    //Helper methods
    private void calculateDays() throws ParseException{
         // calculate number of day
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(checkInDate);
        Date d2 = sdf.parse(checkOutDate);
        numberOfDays = (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        
        totalPrice = numberOfDays * price; 
    }

    public List<Room> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(List<Room> availableRooms) {
        this.availableRooms = availableRooms;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

   
}
