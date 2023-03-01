/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.lab3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
public class Staff {
    @NotNull(message="Id can't be null")
    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffId;
    @NotBlank
    @Size(min=2, max=16)
    private String firstName;
    @NotBlank
    @Size(min=2, max=16)
    private String lastName;
    @NotNull(message="Id can't be null")
    @Positive
    private Integer addressId;
    @Email(message="Not a valid email address")
    private String email;
    @NotNull(message="Id can't be null")
    @Positive
    private Integer storeId;
    @NotBlank
    private String username;
    @PastOrPresent
    private LocalDate lastUpdate;

    public Staff() {
    }

    public Staff(Integer staffId, String firstName, String lastName, Integer addressId, String email, Integer storeId, String username, LocalDate lastUpdate) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.email = email;
        this.storeId = storeId;
        this.username = username;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Get the value of staffId
     *
     * @return the value of staffId
     */
    public Integer getStaffId() {
        return staffId;
    }

    /**
     * Set the value of staffId
     *
     * @param staffId new value of staffId
     */
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", addressId=" + addressId + ", email=" + email + ", storeId=" + storeId + ", username=" + username + ", lastUpdate=" + lastUpdate + '}';
    }
}
