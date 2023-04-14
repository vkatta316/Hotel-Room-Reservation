/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

/**
 *
 * @author vinaychowdarykatta
 */
public enum RoomType {
    
    
    SINGLE("SINGLE"), 
    DOUBLE("DOUBLE"), 
    SUITE("SUITE"), 
    MEETING("MEETING");
    
    private String description;
    
     private RoomType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
