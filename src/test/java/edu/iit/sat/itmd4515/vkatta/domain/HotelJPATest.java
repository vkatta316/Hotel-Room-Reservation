/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edu.iit.sat.itmd4515.vkatta.domain;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vinaychowdarykatta
 */
public class HotelJPATest {
    
    private static EntityManagerFactory emf ;
    private static EntityManager em;
    private static EntityTransaction tx;
    Hotel hotel;
    
    /**
     *
     */
    @BeforeAll
    public static void beforeAllExecution() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    /**
     *
     */
    @BeforeEach
    public void beforeEachTestMethod() {
        // Create a new Staff record before each test method
        createHotelEntry("Hyatt", HotelType.SUPERIOR, "Premium Hotel for B & B", "London" );
    }
     
    /**
     *
     */
    @Test
    public void createTest(){
        createHotelEntry("Hyatt Place", HotelType.STANDARD, "Standard Hotel for B & B", "London" );
        System.out.println("Create New Hotel : " + hotel.toString());
        deleteHotelEntry("Hyatt Place");

    }
    
    /**
     *
     */
    @Test
    public void readTest(){
        // Read test record from database
        readHotelEntry("Hyatt");
        // Assert the value returned from database
        assertEquals("Hyatt",hotel.getHotelName());
        System.out.println("Read Hotel Name: " + hotel.toString());
    }
    
    /**
     *
     */
    @Test
    public void updateTest(){
        // get the inserted record
        tx.begin();
        // update the hotel type
        readHotelEntry("Hyatt");
        
        hotel.setHotelType(HotelType.LUXURY);
        // update
        tx.commit();
        // find the updated record and assert
        System.out.println("Update Hotel Type: " + hotel.toString());
        Hotel readBackFromDatabase = em.find(Hotel.class, hotel.getId());
        assertEquals(HotelType.LUXURY,readBackFromDatabase.getHotelType());
    }
    
    /**
     *
     */
    @Test
    public void deleteTest(){
        // Create Test date for delete operation
        createHotelEntry("Delete Hyatt", HotelType.BUDGET, "Budget Hotel for B & B", "London" );
        System.out.println("Delete Hotel Name: " + hotel.toString());
        assertNotNull(hotel.getId());
        // Delete the above record
        tx.begin();
        em.remove(hotel);
        tx.commit();
        
        Hotel readBackFromDatabase = em.find(Hotel.class, hotel.getId());
        assertNull(readBackFromDatabase);
        
    }
    
    private void createHotelEntry(String hotelName, HotelType hotelType, String hotelDesc, String hotelAddress  ){
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        hotel = new Hotel(hotelName, hotelType, hotelDesc, hotelAddress);
        em.persist(hotel);
        tx.commit();
    }
    
    private void readHotelEntry(String hotelName){
        hotel = em.createNamedQuery("Hotel.findByName", Hotel.class)
                .setParameter("name", hotelName)
                .getSingleResult();
    }
    
    private void deleteHotelEntry(String hotelName){
        tx.begin();
        readHotelEntry(hotelName);
        hotel = em.createNamedQuery("Hotel.findByName", Hotel.class)
                .setParameter("name", hotelName)
                .getSingleResult();
        em.remove(hotel);
        tx.commit();
    }
    
    /**
     *
     */
    @AfterEach
    public void afterEachTestMethod() {
        deleteHotelEntry("Hyatt");
    }

    /**
     *
     */
    @AfterAll
    public static void afterAllExecution() {

    }
}
