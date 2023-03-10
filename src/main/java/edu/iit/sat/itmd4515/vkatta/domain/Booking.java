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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "BOOKING")
@NamedQuery(name = "Booking.findByName" , query = "select booking from Booking booking where booking.bookingTitle = :name")
@NamedQuery(name = "Booking.findAll", query="select booking from Booking booking")
public class Booking extends AbstractEntity {
    
    
    @Column(name = "BOOKING_TITLE")
    private String bookingTitle;
    
    @Column(name = "BOOKING_TYPE")
    @Enumerated(EnumType.STRING)
    private BookingType bookingType;
    
    @Column(name = "BOOKING_FROM_DATE")
    private LocalDate bookingFromDate;
    
    @Column(name = "BOOKING_TO_DATE")
    private LocalDate bookingToDate;
    
    @Column(name = "BOOKING_DESCRIPTION")
    private String bookingDescription;
    
    
    // Guests can have a booking 
    @OneToOne
    @JoinColumn(name = "GUEST_ID")
    private Guest guest;
    
    // *Must* Uni directional between Booking (owning side) and Hotel (inverse side). Many bookings at one hotel
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;
    
    // *Must* Uni-directional One to One relationship between Booking and Payment
    @OneToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;


    public Booking(String bookingTitle, BookingType bookingType, LocalDate bookingFromDate, LocalDate bookingToDate, String bookingDescription) {
        this.bookingTitle = bookingTitle;
        this.bookingType = bookingType;
        this.bookingFromDate = bookingFromDate;
        this.bookingToDate = bookingToDate;
        this.bookingDescription = bookingDescription;
    }

    public Booking() {
    }
    
    //helper methods for relationships
    
    // Guest is going to book a room at hotel and make payment
    public void makeBooking(Guest g, Hotel h, Payment p){
        this.guest =g;
        this.hotel=h;
        this.payment =p;
        
        if(!g.getBookings().contains(this)){
            g.getBookings().add(this);
        }       
    }
    
    // Guest is going to cancel a room at hotel and make payment
    public void cancelBooking(Guest g, Hotel h, Payment p){
        if(g.getBookings().contains(this)){
            g.getBookings().remove(this);
        }       
        this.guest = null;
        this.hotel= null;
        this.payment = null;
    }
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

    /**
     * Get the value of bookingType
     *
     * @return the value of bookingType
     */
    public BookingType getBookingType() {
        return bookingType;
    }

    /**
     * Set the value of bookingType
     *
     * @param bookingType new value of bookingType
     */
    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
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
     * Get the value of guest
     *
     * @return the value of guest
     */
    public Guest getGuest() {
        return guest;
    }

    /**
     * Set the value of guest
     *
     * @param guest new value of guest
     */
    public void setGuest(Guest guest) {
        this.guest = guest;
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
    public String toString() {
        return "Booking{" + "bookingId=" + id + ", bookingTitle=" + bookingTitle + ", bookingType=" + bookingType + ", bookingFromDate=" + bookingFromDate + ",  bookingToDate=" + bookingToDate + ", bookingDescription=" + bookingDescription + '}';
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

   
    
}
