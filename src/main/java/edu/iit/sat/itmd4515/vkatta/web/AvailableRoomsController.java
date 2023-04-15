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
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@SessionScoped
public class AvailableRoomsController implements Serializable {

    private static final Logger LOG = Logger.getLogger(AvailableRoomsController.class.getName());

    List<Room> availableRooms;
    List<Booking> reservation;

    String checkInDate;
    String checkOutDate;

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

        checkInDate = request.getParameter("checkInDate");
        checkOutDate = request.getParameter("checkOutDate");

        availableRooms = roomService.findAll();

        reservation = bookingService.findAll();
        System.out.println("edu.iit.sat.itmd4515.vkatta.web.AvailableRoomsController.doGet() reservation" + reservation.size());
        System.out.println("edu.iit.sat.itmd4515.vkatta.web.AvailableRoomsController.doGet() availableRooms" + availableRooms.size());

        for (int i = 0; i < reservation.size(); i++) {
            
                for(int j = 0; j < availableRooms.size(); j++) {
                    boolean sameCheckInDate = reservation.get(i).getBookingFromDate().toString().equals(checkInDate);
                    boolean sameCheckOutDate = reservation.get(i).getBookingToDate().toString().equals(checkOutDate);
                   if(reservation.get(i).getRooms().get(i).getId().toString().equals(availableRooms.get(j).getId().toString()) && sameCheckInDate && sameCheckOutDate) {
                        availableRooms.remove(j);
                        break;
                    }
                }
            }
        
        LOG.info("edu.iit.sat.itmd4515.vkatta.web.AvailableRoomsController.doGet() VK" + availableRooms);
        return "/customer/available_rooms.xhtml";
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
}
