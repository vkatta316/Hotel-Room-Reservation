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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "BOOKING")
public class Booking {
    
    @Column(name = "BOOKING_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    @Column(name = "BOOKING_TITLE")
    private String bookingTitle;
    
    @Column(name = "BOOKING_TYPE")
    @Enumerated(EnumType.STRING)
    private BookingType bookingType;
    
    @Column(name = "BOOKING_DATE")
    private LocalDate bookingDate;
    
    @Column(name = "BOOKING_DESCRIPTION")
    private String bookingDescription;
    
    //Bi directional between Booking (owning side) and Guest (inverse side). A guest can have many bookings. Many
    // Guests can have a booking 
    @ManyToOne
    @JoinColumn(name = "GUEST_ID")
    private Guest guest;
    
    // *Must* Uni directional between Booking (owning side) and Hotel (inverse side). Many bookings at one hotel
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOTEL_ID")
    private Hotel hotel;
    
   


    public Booking(String bookingTitle, BookingType bookingType, LocalDate bookingDate, String bookingDescription) {
        this.bookingTitle = bookingTitle;
        this.bookingType = bookingType;
        this.bookingDate = bookingDate;
        this.bookingDescription = bookingDescription;
    }

    public Booking() {
    }
    

    /**
     * Get the value of bookingId
     *
     * @return the value of bookingId
     */
    public Long getBookingId() {
        return bookingId;
    }

    /**
     * Set the value of bookingId
     *
     * @param bookingId new value of bookingId
     */
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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


    /**
     * Get the value of bookingDate
     *
     * @return the value of bookingDate
     */
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    /**
     * Set the value of bookingDate
     *
     * @param bookingDate new value of bookingDate
     */
    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
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
        return "Booking{" + "bookingId=" + bookingId + ", bookingTitle=" + bookingTitle + ", bookingType=" + bookingType + ", bookingDate=" + bookingDate + ", bookingDescription=" + bookingDescription + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.bookingId);
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
        if ((this.bookingId == null) || (other.bookingId == null)) {
            return false;
        }
        return Objects.equals(this.bookingId, other.bookingId);
    }
    
}
