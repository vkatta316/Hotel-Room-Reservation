/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Hotel;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Methods related to booking entity
 * @author vinaychowdarykatta
 */
@Stateless
public class BookingService extends AbstractService<Booking>{
    
    /**
     *
     */
    public BookingService() {
        super(Booking.class);
    }
    
    /**
     * 
     * @return all results related to Booking table
     */
    public List<Booking> findAll(){
        return super.findAll("Booking.findAll");
    }
    
    /**
     * Add the reference of booking to customer
     * @param booking bookings
     * @param customer customer object
     */
    public void addBookingForCustomer(List<Booking> booking, Guest customer){
        //
        em.persist(booking);
        
        Guest managedCustomerRef = em.getReference(Guest.class,customer.getId());
        managedCustomerRef.setBookings( booking);
        em.merge(managedCustomerRef);  
    }
     
    /**
     * Add the reference of room to customer
     * @param room room object
     * @param customer customer object
     */
    public void createRoomForGuest(Room room, Guest customer){
        
        em.persist(room);
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.addRoom(room);
        em.merge(managedGuestRef);        
    }
    
    /**
     * Add the reference of Room to Booking
     * @param room
     * @param booking
     */
    public void addRoomForBooking(Room room, Booking booking){
        
        //em.persist(room);
        Booking managedBookingRef = em.getReference(Booking.class, booking.getId());
        managedBookingRef.addRoom(room);
        em.merge(managedBookingRef);        
    }
     
    /**
     * Add the reference of booking to hotel
     * @param hotel
     * @param booking
     */
    public void addHotelForBooking(Hotel hotel, Booking booking){
        
        //em.persist(room);
        Booking managedBookingRef = em.getReference(Booking.class, booking.getId());
        managedBookingRef.setHotel(hotel);
        em.merge(managedBookingRef);        
    }
     
    /**
     * Modify the booking
     * @param booking
     */
    public void modifyAppointment(Booking booking){
        Booking managedBooking = em.getReference(Booking.class, booking.getId());
        em.merge(managedBooking);
    }

    
   
    /**
     * Cancel the booking for the customer
     * @param guest
     * @param booking
     */
    public void cancelBooking(Booking booking, Guest guest){
        booking  = em.getReference(Booking.class, booking.getId());
        booking.removeGuest(guest);
        guest.removeBooking(booking);
        delete(booking);
        System.out.println("GUEST Bookings" +guest.getBookings());
        
    }
    
    /**
     * Find booking by passing id
     * @param id
     * @return
     */
    public Booking findBookingById(Long id) {
        return em.createNamedQuery("Booking.findById", Booking.class)
                .setParameter("id", id)
                .getSingleResult();
    }
   
}
