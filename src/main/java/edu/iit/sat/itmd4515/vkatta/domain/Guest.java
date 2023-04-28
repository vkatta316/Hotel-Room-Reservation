/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

import edu.iit.sat.itmd4515.vkatta.security.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@NamedQuery(name = "Guest.findAll", query="select guest from Guest guest")
@NamedQuery(name = "Guest.findByUsername" , query = "select guest from Guest guest where guest.user.userName = :username")
public class Guest extends AbstractEntity {
    
    @NotBlank(message="First name can't be empty")
    @Size(message="First name too short/Long", min=2, max=16)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @NotBlank(message="Last name can't be empty")
    @Size(message="Last name too short/Long" ,min=2, max=16)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Size(message="Mobile number should have minimum 10 numbers", min=10)
    @Column(name = "GUEST_MOBILE_NUMBER")
    private String guestMobileNumber;
    @Email(message="Invalid Email Address")
    @Column(name = "GUEST_EMAIL_ADDRESS")
    private String guestEmailAddress;
    @Column(name = "GUEST_ADDRESS")
    private String guestAddress;
    

    // *Must* Bi directional Many to One and One to Many. One guest at one hotel. A hotel can have many guests. 
    @ManyToOne
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;
    
     // *Must* Bi-directional One to One relationship between room and guest
    // bi-directional ManyToMany relationship between Owner (owning side) and Pet (inverse side)
    @ManyToMany
    @JoinTable(name = "GUESTS_ROOMS",
            joinColumns = @JoinColumn(name = "GUEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID"))
    private List<Room> rooms = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name = "GUESTS_BOOKINGS",
            joinColumns = @JoinColumn(name = "GUEST_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOKING_ID"))
    private List<Booking> bookings = new ArrayList<>();
    
    
    
    // relationship helper methods
    public void addRoom(Room room) {
        if (!this.rooms.contains(room)) {
            this.rooms.add(room);
        }
        if (!room.getGuests().contains(this)) {
            room.getGuests().add(this);
        }
    }

    public void removeRoom(Room room) {
        if (this.rooms.contains(room)) {
            this.rooms.remove(room);
        }
        if (room.getGuests().contains(this)) {
            room.getGuests().remove(this);
        }
    }
    
     public void addBooking(Booking booking) {
        if (!this.bookings.contains(booking)) {
            this.bookings.add(booking);
        }
        if (!booking.getGuests().contains(this)) {
            booking.getGuests().add(this);
        }
    }

    public void removeBooking(Booking booking) {
        if (this.bookings.contains(booking)) {
            this.bookings.remove(booking);
        }
        if (booking.getGuests().contains(this)) {
            booking.getGuests().remove(this);
        }
    }
    
    public Guest(String firstName, String lastName, String guestMobileNumber, String guestEmailAddress, String guestAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.guestMobileNumber = guestMobileNumber;
        this.guestEmailAddress = guestEmailAddress;
        this.guestAddress = guestAddress;
    }

    public Guest() {
    }
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;


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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

  
}
