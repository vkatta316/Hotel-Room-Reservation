/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "ROOM")
@NamedQuery(name = "Room.findById" , query = "select room from Room room where room.id = :id")
@NamedQuery(name = "Room.findAll", query="select room from Room room")
public class Room extends AbstractEntity {
    
   @Column(name = "ROOM_TYPE")
    private String roomType;
    @Lob
    @Size(max = 65535)
    @Column(name = "ROOM_DESCRIPTION")
    private String roomDescription;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "PERSONS_ALLOWED")
    private Integer personsAllowed;
    
    
     //Uni directional between Room (owning side) and Hotel (inverse side). Many rooms at one hotel
   // @ManyToOne
    //@JoinColumn(name = "HOTEL_ID")
    //private Hotel hotel;

    @ManyToMany(mappedBy = "rooms")
    private List<Guest> guests = new ArrayList<>();
    
    @ManyToMany(mappedBy = "rooms")
    private List<Booking> bookings = new ArrayList<>();

    public Room() {
    }

    public Room(String roomType, String roomDescription) {
        this.roomType = roomType;
        this.roomDescription = roomDescription;
    }

    public Room(String roomType, String roomDescription, Double price, Integer personsAllowed) {
        this.roomType = roomType;
        this.roomDescription = roomDescription;
        this.price = price;
        this.personsAllowed = personsAllowed;
        
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
 

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPersonsAllowed() {
        return personsAllowed;
    }

    public void setPersonsAllowed(Integer personsAllowed) {
        this.personsAllowed = personsAllowed;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Room{" + "roomType=" + roomType + ", roomDescription=" + roomDescription + ", price=" + price + ", personsAllowed=" + personsAllowed + '}';
    }

    

}
