/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.api.rest;


import edu.iit.sat.itmd4515.vkatta.domain.Room;
import edu.iit.sat.itmd4515.vkatta.service.RoomService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Path("/rooms")
public class RoomServiceREST {
    
    @EJB RoomService roomService;
    
    /**
     * Get Version of Restful Services
     * @return
     */
    @GET
    @Path("/version")
    @Produces(MediaType.TEXT_PLAIN)
    public String versionInfo() {
        return "v1";
    }
    
    /**
     * Gel all the records Restful endpoint
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Room> getAllRooms(){
        return roomService.findAll();
    }
    
    /**
     *  Get a Single record RestFul End point
     * @param id
     * @return room
     */
    @GET
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Room findASingleRoom( @PathParam("id") Long id ){
        return roomService.read(id);
    }
    
    /**
     *  Create RestFul End point
     * @param room
     * @return room
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Room createRoom(Room room){
        return roomService.createAndReturnRoom(room);
    }
    
     /**
     *  Update RestFul End point
     * @param id
     * @param updateRoom
     * @return room
     */
    @PUT
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Room updateRoom(@PathParam("id") Long id, Room updateRoom){
        Room room = roomService.read(id);
        System.out.println("VVKKCC " + updateRoom.toString());
        return roomService.updateRoom(room, updateRoom);
    }
    
    
     /**
     * Delete RestFul End point
     * @param id
     * @return room
     */
    @DELETE
    @Path("/id/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String deleteRoom(@PathParam("id") Long id){
        Room room = roomService.read(id);
        return roomService.deleteRoom(room);
    }
    
}
