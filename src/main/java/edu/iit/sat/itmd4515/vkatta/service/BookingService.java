/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Stateless
public class BookingService extends AbstractService<Booking>{
    
   public BookingService() {
        super(Booking.class);
    }
    
    public List<Booking> findAll(){
        return super.findAll("Booking.findAll");
    }
    
     public void addBookingForCustomer(Booking booking, Guest customer){
        //
        em.persist(booking);
        
        Guest managedCustomerRef = em.getReference(Guest.class,customer.getId());
        managedCustomerRef.setBooking(booking);
        em.merge(managedCustomerRef);  
    }
     
    public void createRoomForGuest(Room room, Guest customer){
        
        em.persist(room);
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.addRoom(room);
        em.merge(managedGuestRef);        
    }
    
     public void addRoomForBooking(Room room, Booking booking){
        
        //em.persist(room);
        Booking managedBookingRef = em.getReference(Booking.class, booking.getId());
        managedBookingRef.addRoom(room);
        em.merge(managedBookingRef);        
    }
}
