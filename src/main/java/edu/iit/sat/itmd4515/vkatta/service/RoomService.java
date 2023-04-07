/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;


import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@Stateless
public class RoomService extends AbstractService<Room>{
    
   public RoomService() {
        super(Room.class);
    }
    
    public List<Room> findAll(){
        return super.findAll("Room.findAll");
    }
    
    public void addRoomForCustomer(Room room, Guest customer, Booking book){
        //
        em.persist(room);
        
        Guest managedCustomerRef = em.getReference(Guest.class,customer.getId());
        managedCustomerRef.setRoom(room);
        em.merge(managedCustomerRef);
        
        Booking managedBookingRef = em.getReference(Booking.class,book.getId());
        managedBookingRef.setRoom(room);
        em.merge(managedBookingRef);
        
    }
    
}
