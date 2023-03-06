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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "GUEST")
@NamedQuery(name = "Guest.findByName" , query = "select guest from Guest guest where guest.firstName = :name")
public class Guest extends AbstractEntity {
    
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "GUEST_MOBILE_NUMBER")
    private String guestMobileNumber;
    @Column(name = "GUEST_EMAIL_ADDRESS")
    private String guestEmailAddress;
    @Column(name = "GUEST_ADDRESS")
    private String guestAddress;
    

    // *Must* Bi directional Many to One and One to Many. One guest at one hotel. A hotel can have many guests. 
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;
    
     // *Must* Uni-directional One to One relationship between room and guest
    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;
    
    @OneToMany(mappedBy = "guest")
    private List<Booking> bookings = new ArrayList<>();
    
    public Guest(String firstName, String lastName, String guestMobileNumber, String guestEmailAddress, String guestAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestMobileNumber = guestMobileNumber;
        this.guestEmailAddress = guestEmailAddress;
        this.guestAddress = guestAddress;
    }

    public Guest() {
    }


    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Get the value of guestMobileNumber
     *
     * @return the value of guestMobileNumber
     */
    public String getGuestMobileNumber() {
        return guestMobileNumber;
    }

    /**
     * Set the value of guestMobileNumber
     *
     * @param guestMobileNumber new value of guestMobileNumber
     */
    public void setGuestMobileNumber(String guestMobileNumber) {
        this.guestMobileNumber = guestMobileNumber;
    }


    /**
     * Get the value of guestEmailAddress
     *
     * @return the value of guestEmailAddress
     */
    public String getGuestEmailAddress() {
        return guestEmailAddress;
    }

    /**
     * Set the value of guestEmailAddress
     *
     * @param guestEmailAddress new value of guestEmailAddress
     */
    public void setGuestEmailAddress(String guestEmailAddress) {
        this.guestEmailAddress = guestEmailAddress;
    }


    /**
     * Get the value of guestAddress
     *
     * @return the value of guestAddress
     */
    public String getGuestAddress() {
        return guestAddress;
    }

    /**
     * Set the value of guestAddress
     *
     * @param guestAddress new value of guestAddress
     */
    public void setGuestAddress(String guestAddress) {
        this.guestAddress = guestAddress;
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
    
    /**
     * Get the value of bookings
     *
     * @return the value of bookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }
    
    /**
     * Set the value of bookings
     *
     * @param bookings new value of bookings
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
     
    /**
     * Get the value of getGuest
     *
     * @return the guest of getGuest
     */
    public Room getRoom() {
        return room;
    }
    
    /**
     * Set the value of guest
     *
     * @param room new value of guest
     */
    public void setRoom(Room room) {
        this.room = room;
    }
   
    
    @Override
    public String toString() {
        return "Guest{" + "guestId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", guestMobileNumber=" + guestMobileNumber + ", guestEmailAddress=" + guestEmailAddress + ", guestAddress=" + guestAddress + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Guest other = (Guest) obj;
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }    

}
