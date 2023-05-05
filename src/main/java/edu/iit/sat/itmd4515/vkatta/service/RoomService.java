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
    
    /**
     *
     */
    public RoomService() {
        super(Room.class);
    }
    
    /**
     * Find all available rooms
     * @return list of all the available rooms
     */
    public List<Room> findAll(){
        return super.findAll("Room.findAll");
    }
    
    /**
     * Find room by passing the id
     * @param id id of the room
     * @return room
     */
    public Room findById(Long id){
        return super.findById(id);
    }
    
     /**
     * Create Rest End point utility
     * @param room
     * @return
     */
    public Room createAndReturnRoom(Room room) {
        em.persist(room);
        em.flush();
        return room;
    }
    
    
    /**
     * Update Rest End point utility
     *
     * @param room object
     * @param updateRoom object
     * @return Room Reference object
     */
    public Room updateRoom(Room room, Room updateRoom) {
        Room managedRoomReference = em.getReference(Room.class, room.getId());
        managedRoomReference.setId(room.getId());
        managedRoomReference.setRoomType(updateRoom.getRoomType());
        managedRoomReference.setPrice(updateRoom.getPrice());
        managedRoomReference.setPersonsAllowed(updateRoom.getPersonsAllowed());
        managedRoomReference.setRoomDescription(updateRoom.getRoomDescription());
        em.merge(managedRoomReference);
        return managedRoomReference;
    }
    
    
    /**
     * Delete Rest End point utility
     * @param room object
     * @return Confirmation Message On Delete
     */
    public String deleteRoom(Room room){
        room = em.getReference(Room.class, room.getId());
        em.remove(em.merge(room));
        return "Room is deleted";
        
    }
}
