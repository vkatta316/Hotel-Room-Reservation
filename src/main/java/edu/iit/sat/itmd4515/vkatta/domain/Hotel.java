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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.FutureOrPresent;
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
@Table(name = "HOTEL")
@NamedQuery(name = "Hotel.findByName" , query = "select hotel from Hotel hotel where hotel.hotelName = :name")
public class Hotel {
    
    @Column(name = "HOTEL_ID")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;
    
    @NotBlank(message="Hotel name can't be empty")
    @Size(min=2, max=16)
    @Column(name = "HOTEL_NAME" ,nullable = false, unique = true)
    private String hotelName;
    
    @Column(name = "HOTEL_TYPE")
    @Enumerated(EnumType.STRING)
    private HotelType hotelType;
    
    
    @Column(name = "HOTEL_DESCRIPTION")
    private String hotelDescription;
    
    @Column(name = "HOTEL_ADDRESS")
    private String hotelAddress;
    
    @FutureOrPresent(message="Date can't be in the past")
    @Column(name = "HOTEL_SEARCH_DATE")
    private LocalDate dateSearch;
 
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Guest> guestList = new ArrayList<>();

    public Hotel(){
        
    }

    public Hotel(String hotelName, HotelType hotelType, String hotelDescription, String hotelAddress) {
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.hotelDescription = hotelDescription;
        this.hotelAddress = hotelAddress;
    }

    public Hotel(String hotelName, HotelType hotelType, String hotelDescription, String hotelAddress, LocalDate dateSearch) {
        this.hotelName = hotelName;
        this.hotelType = hotelType;
        this.hotelDescription = hotelDescription;
        this.hotelAddress = hotelAddress;
        this.dateSearch = dateSearch;
    }
    
    /**
     * Get the value of hotelId
     *
     * @return the value of hotelId
     */
     public Long getHotelId() {
        return hotelId;
    }

     /**
     * Set the value of hotelId
     *
     * @param hotelId new value of hotelName
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }
    
    /**
     * Get the value of hotelName
     *
     * @return the value of hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Set the value of hotelName
     *
     * @param hotelName new value of hotelName
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }


    /**
     * Get the value of hotelType
     *
     * @return the value of hotelType
     */
    public HotelType getHotelType() {
        return hotelType;
    }

    /**
     * Set the value of hotelType
     *
     * @param hotelType new value of hotelType
     */
    public void setHotelType(HotelType hotelType) {
        this.hotelType = hotelType;
    }


    /**
     * Get the value of hotelDescription
     *
     * @return the value of hotelDescription
     */
    public String getHotelDescription() {
        return hotelDescription;
    }

    /**
     * Set the value of hotelDescription
     *
     * @param hotelDescription new value of hotelDescription
     */
    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }


    /**
     * Get the value of hotelAddress
     *
     * @return the value of hotelAddress
     */
    public String getHotelAddress() {
        return hotelAddress;
    }

    /**
     * Set the value of hotelAddress
     *
     * @param hotelAddress new value of hotelAddress
     */
    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }
    
    /**
     * Get the value of dateSearch
     *
     * @return the value of dateSearch
     */
    public LocalDate getDateSearch() {
        return dateSearch;
    }
    
    /**
     * Set the value of dateSearch
     *
     * @param dateSearch new value of dateSearch
     */
    public void setDateSearch(LocalDate dateSearch) {
        this.dateSearch = dateSearch;
    }    
    
     /**
     * Get the value of guestList
     *
     * @return the value of guestList
     */
    public List<Guest> getGuestList() {
        return guestList;
    }

    /**
     * Set the value of guestList
     *
     * @param guestList new value of guestList
     */
    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }


    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.hotelId);
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
        final Hotel other = (Hotel) obj;
        
        if ((this.hotelId == null) || (other.hotelId == null)) {
            return false;
        }
        return Objects.equals(this.hotelId, other.hotelId);
    }

    @Override
    public String toString() {
        return "Hotel{" + "hotelId=" + hotelId + ", hotelName=" + hotelName + ", hotelType=" + hotelType + ", hotelDescription=" + hotelDescription + ", hotelAddress=" + hotelAddress + ", dateSearch=" + dateSearch + '}';
    }

  
  
}
