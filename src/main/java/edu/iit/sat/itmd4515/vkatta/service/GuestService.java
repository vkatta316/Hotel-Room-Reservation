/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Methods related to Guest entity
 * @author vinaychowdarykatta
 */
@Stateless
public class GuestService extends AbstractService<Guest> {
    
    @EJB
    private RoomService roomService;
    
    /**
     *
     */
    public GuestService() {
        super(Guest.class);
    }

    /**
     * Find all the guests
     * @return all the guests
     */
    public List<Guest> findAll() {
        return super.findAll("Guest.findAll");
    }

    /**
     * Find the guest by passing the username
     * @param userName
     * @return guest
     */
    public Guest findGuestByUsername(String userName) {
        return em.createNamedQuery("Guest.findByUsername", Guest.class)
                .setParameter("username", userName)
                .getSingleResult();
    }
    
    /**
     * Create room for the guest
     * @param room object
     * @param customer object
     */
    public void createRoomForGuest(Room room, Guest customer){
        // all that is happening in the default create method:        
        //em.persist(room);
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.addRoom(room);
        em.merge(managedGuestRef);        
    }
 
    /**
     * Create booking for customer
     * @param booking
     * @param customer
     */
    public void createBookingForGuest(Booking booking, Guest customer){     
        em.persist(booking);
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.addBooking(booking);
        em.merge(managedGuestRef);        
    }
    
    /**
     * Update room for guest
     * @param room object
     */
    public void updateRoomForGuest(Room room){
        Room managedRoomReference = em.getReference(Room.class, room.getId());
        managedRoomReference.setId(room.getId());
        managedRoomReference.setRoomType(room.getRoomType());
        managedRoomReference.setPrice(room.getPrice());
        managedRoomReference.setPersonsAllowed(room.getPersonsAllowed());
        managedRoomReference.setRoomDescription(room.getRoomDescription());
        em.merge(managedRoomReference);
    }
     
    /**
     * delete room for guest
     * @param room object
     */
    public void deleteRoomForGuest(Room room){
        room = em.getReference(Room.class, room.getId());
        em.remove(room);
    }

    /**
     * Remove booking reference for Guest
     * @param booking object
     * @param customer object
     */
    public void removeBookingForGuest(Booking booking, Guest customer){
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.removeBooking(booking);
        em.merge(managedGuestRef);        
    }
}
