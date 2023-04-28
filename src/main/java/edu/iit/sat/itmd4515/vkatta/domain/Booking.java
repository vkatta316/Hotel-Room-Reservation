/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "BOOKING")
@NamedQuery(name = "Booking.findByName" , query = "select booking from Booking booking where booking.bookingTitle = :name")
@NamedQuery(name = "Booking.findAll", query="select booking from Booking booking")
@NamedQuery(name = "Booking.findById" , query = "select booking from Booking booking where booking.id = :id")
public class Booking extends AbstractEntity {
    
    
    @Column(name = "CUSTOMER_NAME")
    private String bookingTitle;
     
    @Column(name = "CHECK_IN_DATE")
    private LocalDate bookingFromDate;
    
    @Column(name = "CHECK_OUT_DATE")
    private LocalDate bookingToDate;
    
    @Column(name = "BOOKING_DESCRIPTION")
    private String bookingDescription;
    
    @Size(max = 25)
    @Column(name = "EMAIL")
    private String email;
     
    @Size(max = 20)
    @Column(name = "PHONE")
    private String phone;
    
    
    
    @ManyToMany(mappedBy = "bookings")
    private List<Guest> guests = new ArrayList<>();

    
    // *Must* Uni directional between Booking (owning side) and Hotel (inverse side). Many bookings at one hotel
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;
    
    // *Must* Uni-directional One to One relationship between Booking and Payment
    @OneToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    
      // *Must* Bi-directional One to One relationship between room and guest
    // bi-directional ManyToMany relationship between Owner (owning side) and Pet (inverse side)
    @ManyToMany
    @JoinTable(name = "BOOKINGS_ROOMS",
            joinColumns = @JoinColumn(name = "BOOKING_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROOM_ID"))
    private List<Room> rooms = new ArrayList<>();
    
 // relationship helper methods
    public void addRoom(Room room) {
        if (!this.rooms.contains(room)) {
            this.rooms.add(room);
        }
        if (!room.getBookings().contains(this)) {
            room.getBookings().add(this);
        }
    }

    public void removeRoom(Room room) {
        if (this.rooms.contains(room)) {
            this.rooms.remove(room);
        }
        if (room.getBookings().contains(this)) {
            room.getBookings().remove(this);
        }
    }
    
     public void removeGuest(Guest guest) {
        if (this.guests.contains(guest)) {
            this.guests.remove(guest);
        }
        if (guest.getBookings().contains(this)) {
            guest.getBookings().remove(this);
        }
    }

    public Booking(String bookingTitle, LocalDate bookingFromDate, LocalDate bookingToDate, String bookingDescription) {
        this.bookingTitle = bookingTitle;
        this.bookingFromDate = bookingFromDate;
        this.bookingToDate = bookingToDate;
        this.bookingDescription = bookingDescription;
    }

    public Booking() {
    }

    public Booking(String bookingTitle, LocalDate bookingFromDate, LocalDate bookingToDate, String bookingDescription, String email, String phone) {
        this.bookingTitle = bookingTitle;
        this.bookingFromDate = bookingFromDate;
        this.bookingToDate = bookingToDate;
        this.bookingDescription = bookingDescription;
        this.email = email;
        this.phone = phone;
    }
    
    //helper methods for relationships
   
    /**
     * Get the value of bookingTitle
     *
     * @return the value of bookingTitle
     */
    public String getBookingTitle() {
        return bookingTitle;
    }

    /**
     * Set the value of bookingTitle
     *
     * @param bookingTitle new value of bookingTitle
     */
    public void setBookingTitle(String bookingTitle) {
        this.bookingTitle = bookingTitle;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public LocalDate getBookingFromDate() {
        return bookingFromDate;
    }

    public void setBookingFromDate(LocalDate bookingFromDate) {
        this.bookingFromDate = bookingFromDate;
    }

    public LocalDate getBookingToDate() {
        return bookingToDate;
    }

    public void setBookingToDate(LocalDate bookingToDate) {
        this.bookingToDate = bookingToDate;
    }

    /**
     * Get the value of bookingDescription
     *
     * @return the value of bookingDescription
     */
    public String getBookingDescription() {
        return bookingDescription;
    }

    /**
     * Set the value of bookingDescription
     *
     * @param bookingDescription new value of bookingDescription
     */
    public void setBookingDescription(String bookingDescription) {
        this.bookingDescription = bookingDescription;
    }
    
     /**
     * Get the value of hotel
     *
     * @return the value of hotel
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
    public int hashCode() {
        int hash = 7;
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
        final Booking other = (Booking) obj;
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Booking{" + "bookingTitle=" + bookingTitle + ", bookingFromDate=" + bookingFromDate + ", bookingToDate=" + bookingToDate + ", bookingDescription=" + bookingDescription + ", email=" + email + ", phone=" + phone + '}';
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

   
    
}
