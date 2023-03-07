/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta;

import edu.iit.sat.itmd4515.vkatta.domain.Hotel;
import edu.iit.sat.itmd4515.vkatta.domain.HotelType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;

/**
 *
 * @author vinaychowdarykatta
 */
public class Demo {
    
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("itmd4515testPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Hotel hotel = new Hotel("Hyatt", HotelType.SUPERIOR, "Premium Hotel for B & B", "London");
        em.persist(hotel);
        
        tx.commit();
        hotel = null;
        //hotel = em.find(Hotel.class, 1l);
        
        hotel = em.createNamedQuery("Hotel.findByName", Hotel.class)
                .setParameter("name", "Hyatt")
                .getSingleResult();
        System.out.println(hotel.toString());
        
    }
}
