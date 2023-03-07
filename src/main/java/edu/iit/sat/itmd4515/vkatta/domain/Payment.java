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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "PAYMENT")
@NamedQuery(name = "Payment.findByName" , query = "select payment from Payment payment where payment.paymentAmount = :name")
@NamedQuery(name = "Payment.findAll", query="select payment from Payment payment")
public class Payment extends AbstractEntity {
    
    
    @Column(name = "PAYMENT_DATE")
    private LocalDate paymentDate;
    
    @Column(name = "PAYMENT_AMOUNT")
    private Long paymentAmount;
    
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

     // *Must* Uni-directional One to One relationship between payment and guest
    @OneToOne
    @JoinColumn(name = "PAYMENT_GUEST_ID")
    private Guest guest;

    public Payment(LocalDate paymentDate, Long paymentAmount, PaymentType paymentType) {
        
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.paymentType = paymentType;
    }
    
    // *Must* Uni-directional One to Many relationship between payment and Booking. One payment for many bookings
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="PAYMENT_ID")
    private List<Booking> bookings;
    
    public Payment() {
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
     * Get the value of paymentDate
     *
     * @return the value of paymentDate
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Set the value of paymentDate
     *
     * @param paymentDate new value of paymentDate
     */
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }


    /**
     * Get the value of paymentAmount
     *
     * @return the value of paymentAmount
     */
    public Long getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Set the value of paymentAmount
     *
     * @param paymentAmount new value of paymentAmount
     */
    public void setPaymentAmount(Long paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    /**
     * Get the value of paymentType
     *
     * @return the value of paymentType
     */
    public PaymentType getPaymentType() {
        return paymentType;
    }

    /**
     * Set the value of paymentType
     *
     * @param paymentType new value of paymentType
     */
    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }
    
    /**
     * Get the value of getBookings
     *
     * @return the value of getBookings
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Set the value of setBookings
     *
     * @param bookings new value of setBookings
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + id + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount + ", paymentType=" + paymentType + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Payment other = (Payment) obj;
        
        if ((this.id == null) || (other.id == null)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

   


}
