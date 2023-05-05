/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Type of room
 * @author vinaychowdarykatta
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum RoomType {
    
    /**
     * Single Occupancy
     */
    SINGLE("SINGLE"), 

    /**
     * Double Occupancy
     */
    DOUBLE("DOUBLE"), 

    /**
     * Suite Occupancy
     */
    SUITE("SUITE"), 

    /**
     * Meeting Room
     */
    MEETING("MEETING");
    
    private String description;
    
     private RoomType(String description) {
        this.description = description;
    }

    /**
     * Type of Room
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
