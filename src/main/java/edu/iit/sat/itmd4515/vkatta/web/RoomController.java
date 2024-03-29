/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.domain.Room;
import edu.iit.sat.itmd4515.vkatta.domain.RoomType;
import edu.iit.sat.itmd4515.vkatta.service.GuestService;
import edu.iit.sat.itmd4515.vkatta.service.RoomService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

/**
 * Room information
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class RoomController {

    private static final Logger LOG = Logger.getLogger(RoomController.class.getName());
    
    private Room room;
    
    @EJB RoomService roomService;
    @EJB GuestService guestService;
    
    @Inject CustomerWelcomeController cwc;

    /**
     * Default constructor
     */
    public RoomController() {
    }
    
    @PostConstruct
    private void postContruct(){
        LOG.info("RoomController.postConstruct() -> Initializing Room Model");
        room = new Room();
    }
    
    /**
     * Display the information of the room
     * @param room
     * @return
     */
    public String displayReadRoomPage(Room room){
        LOG.info("Inside displayReadRoomPage " + room.toString() );
        LOG.info("Inside displayReadRoomPage ROOM TYPE" + room.getRoomType().toString() );
        this.room = room;
        return "/manager/readRoom.xhtml";
    }
    
    /**
     * Update the room information
     * @param room
     * @return
     */
    public String displayUpdateRoomPage(Room room){
        LOG.info("Inside displayUpdateRoomPage " + room.toString() );
        this.room = room;
        return "/manager/updateRoom.xhtml";  
    }
    
    /**
     * Delete room 
     * @param room
     * @return
     */
    public String displayDeleteRoomPage(Room room){
        LOG.info("Inside displayDeleteRoomPage " + room.toString() );
        this.room = room;
        return "/manager/deleteRoom.xhtml";
    }
    
    /**
     * A room is created for the clients to book successfully
     * @return
     */
    public String saveRoom(){
        LOG.info("RoomController.saveRoom() -> Saving Room Application on Click"); 
        LOG.info("Room is created with all values");
        LOG.info("\t" + room.toString());
        roomService.create(room);
        LOG.info("Room is created with all values after saving to database");        
        LOG.info("\t" + room.toString());
        return "/manager/confirmation.xhtml";
    }
    
    /**
     * Update room
     * @return
     */
    public String updateRoom(){
        LOG.info("Inside updateRoom " + room.toString() );
        guestService.updateRoomForGuest(room);
        return "/manager/welcome.xhtml";
        
    }

    /**
     * Delete room
     * @return
     */
    public String deleteRoom(){
        LOG.info("Inside deleteRoom " + room.toString() );
        guestService.deleteRoomForGuest(room);
        return "/manager/welcome.xhtml";
        
    }
    
    /**
     *
     * @return
     */
    public RoomType[] getAllRoomTypes(){
        return RoomType.values();
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
}
