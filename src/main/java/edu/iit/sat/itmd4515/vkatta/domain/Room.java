/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "ROOM")
@NamedQuery(name = "Room.findByNumber" , query = "select room from Room room where room.roomNumber = :number")
public class Room {
    
    @Column(name = "ROOM_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    
    @Column(name = "ROOM_NUMBER")
    private String roomNumber;
    @Column(name = "ROOM_TYPE")
    private String roomType;
    @Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;
    
    
     //Uni directional between Room (owning side) and Hotel (inverse side). Many rooms at one hotel
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;


    public Room( String roomNumber, String roomType, String roomDescription) {
        
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomDescription = roomDescription;
    }

    public Room() {
    }
    
    

    /**
     * Get the value of roomId
     *
     * @return the value of roomId
     */
    public Long getRoomId() {
        return roomId;
    }

    /**
     * Set the value of roomId
     *
     * @param roomId new value of roomId
     */
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    /**
     * Get the value of roomNumber
     *
     * @return the value of roomNumber
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Set the value of roomNumber
     *
     * @param roomNumber new value of roomNumber
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


    /**
     * Get the value of roomType
     *
     * @return the value of roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * Set the value of roomType
     *
     * @param roomType new value of roomType
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }


    /**
     * Get the value of roomDescription
     *
     * @return the value of roomDescription
     */
    public String getRoomDescription() {
        return roomDescription;
    }

    /**
     * Set the value of roomDescription
     *
     * @param roomDescription new value of roomDescription
     */
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
    
    /**
     * Get the value of getHotel
     *
     * @return the hotel of getHotel
     */
    public Hotel getHotel() {
        return hotel;
    }
     
    /**
     * Set the value of hotel
     *
     * @param hotel new value of hotel
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
   
    
    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", roomNumber=" + roomNumber + ", roomType=" + roomType + ", roomDescription=" + roomDescription + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.roomId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Room other = (Room) obj;
        if ((this.roomId == null) || (other.roomId == null)) {
            return false;
        }
        return Objects.equals(this.roomId, other.roomId);
    }

  

}
