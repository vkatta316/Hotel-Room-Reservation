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
 *
 * @author vinaychowdarykatta
 */
@Stateless
public class GuestService extends AbstractService<Guest> {
    
    @EJB
    private RoomService roomService;
    
    public GuestService() {
        super(Guest.class);
    }

    public List<Guest> findAll() {
        return super.findAll("Guest.findAll");
    }

    public Guest findGuestByUsername(String userName) {
        return em.createNamedQuery("Guest.findByUsername", Guest.class)
                .setParameter("username", userName)
                .getSingleResult();
    }
    
    public void createRoomForGuest(Room room, Guest customer){
        // all that is happening in the default create method:        
        //em.persist(room);
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.addRoom(room);
        em.merge(managedGuestRef);        
    }
    
     public void createBookingForGuest(Booking booking, Guest customer){
        // all that is happening in the default create method:        
        em.persist(booking);
        Guest managedGuestRef = em.getReference(Guest.class, customer.getId());
        managedGuestRef.addBooking(booking);
        em.merge(managedGuestRef);        
    }
    
     
     public void updateRoomForGuest(Room room){
        Room managedRoomReference = em.getReference(Room.class, room.getId());
        managedRoomReference.setId(room.getId());
        managedRoomReference.setRoomType(room.getRoomType());
        managedRoomReference.setPrice(room.getPrice());
        managedRoomReference.setPersonsAllowed(room.getPersonsAllowed());
        managedRoomReference.setRoomDescription(room.getRoomDescription());
        em.merge(managedRoomReference);
    }

}
