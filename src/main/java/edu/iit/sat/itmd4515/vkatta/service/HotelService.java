/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Hotel;
import jakarta.ejb.Stateless;

import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Stateless
public class HotelService extends AbstractService<Hotel>{
    
   public HotelService() {
        super(Hotel.class);
    }
    
    public List<Hotel> findAll(){
        return super.findAll("Hotel.findAll");
    }
}
