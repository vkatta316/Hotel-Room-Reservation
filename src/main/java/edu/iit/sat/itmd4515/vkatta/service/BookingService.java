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
    
     public void addBookingForCustomer(List<Booking> booking, Guest customer){
        //
        em.persist(booking);
        
        Guest managedCustomerRef = em.getReference(Guest.class,customer.getId());
        managedCustomerRef.setBookings( booking);
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
     
    /**
     *
     * @param booking
     */
    public void modifyAppointment(Booking booking){
        // first step, get a managed reference to work with
        Booking managedBooking = em.getReference(Booking.class, booking.getId());
        
       
        em.merge(managedBooking);
    }

    
   
    /**
     * @param guest
     * @param booking
     */
    public void cancelBooking(Booking booking, Guest guest){
        booking  = em.getReference(Booking.class, booking.getId());
        System.out.println("BOOKING VK" +booking);
        System.out.println("GUEST VK" +guest.toString());
        booking.removeGuest(guest);
        guest.removeBooking(booking);
        delete(booking);
        System.out.println("GUEST Bookings" +guest.getBookings());
        
    }
    
     public Booking findBookingById(Long id) {
        return em.createNamedQuery("Booking.findById", Booking.class)
                .setParameter("id", id)
                .getSingleResult();
    }
   
}
