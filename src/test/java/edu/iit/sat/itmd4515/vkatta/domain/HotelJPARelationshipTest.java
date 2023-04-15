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
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vinaychowdarykatta
 */
public class HotelJPARelationshipTest {
    
    private static EntityManagerFactory emf ;
    private static EntityManager em;
    private static EntityTransaction tx;
    Hotel hotel;
    Room room;
    Guest guest;
    Booking booking1;
    Booking booking2;
    Payment payment;
    
    Guest guest1;
    Guest guest2;
    Guest guest3;
    
     @BeforeAll
    public static void beforeAllExecution() {
        emf = Persistence.createEntityManagerFactory("itmd4515testPU");
    }

    @BeforeEach
    public void beforeEachTestMethod() {
        // Create a new Staff record before each test method
        createHotelEntry("Hyatt", HotelType.SUPERIOR, "Premium Hotel for B & B", "London" );
    }
    
    @Test
    public void testOneToOneUnidirectionalGuestRoomRelationship(){

        guest = new Guest("Chowdary", "Katta", "9440263355", "ck@gmail.com", "India");
        room = new Room("AC DEluxe", "Superior");
      
        guest.addRoom(room);
        
        tx.begin();
        em.persist(guest);
        em.persist(room );

        tx.commit();
        System.out.println("edu.iit.sat.itmd4515.vkatta.domain.HotelJPARelationshipTest.testOneToOneUnidirectionalHotelRoomRelationship()");
        //assertEquals(hotel.getHotelId(),room.getHotel().getHotelId());
        
        tx.begin();
        em.remove(room);
        em.remove(guest);
        tx.commit();
    }
    
    @Test
    public void testOneToOneUnidirectionalPaymentAndGuest(){
        payment = new Payment(LocalDate.now(), 2000L, PaymentType.ONLINE);
        guest = new Guest("Rao", "Katta", "99090900909", "aa@gmail.com", "India");
        
        payment.setGuest(guest);
        tx.begin();
        em.persist(payment);
        em.persist(guest );

        tx.commit();
        
        tx.begin();
        em.remove(payment);
        em.remove(guest);
        tx.commit();
        System.out.println("Test");
        
    }
    
    @Test
    public void testManyToOneUnidirectionalBookingsHotelRelationship(){
                
        hotel = new Hotel("Hyatt Regency", HotelType.SUPERIOR, "Bed", "Chicago");
       
        booking1 = new Booking("Katta's Reservation", LocalDate.now(),LocalDate.now(), "Test");
        booking2 = new Booking("Katta's Reservation2", LocalDate.now(), LocalDate.now(),"Test2");
        
        booking1.setHotel(hotel);
        booking2.setHotel(hotel);
        
        tx.begin();
        
        em.persist(booking1);
        em.persist(booking2);

        tx.commit();
        tx.begin();
        em.remove(booking1);
        em.remove(booking2);
        em.remove(hotel);
        tx.commit();
        System.out.println("Navigating the relationship from owning side " + hotel.toString());
    }
    
    @Test
    public void testOnetoManyUnidirectionalPaymentBookingsRelationship(){
        payment = new Payment(LocalDate.now(),82000L, PaymentType.ONLINE);
        booking1 = new Booking("K's Reservation", LocalDate.now(), LocalDate.of(2023, 04, 26), "Test");
        booking2 = new Booking("K's Reservation2", LocalDate.now(),LocalDate.now(), "Test2");
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(booking1);
        bookingList.add(booking2);
        payment.setBookings(bookingList); 
        tx.begin();
        em.persist(payment);
        tx.commit();
        tx.begin();
        em.remove(payment);
        em.remove(booking1);
        em.remove(booking2);
        tx.commit();
        System.out.println("Navigating the relationship from owning side " + payment.toString());
    }
    
    @Test
    public void testManyToOneBiDirectionalGuestHotelRelationship(){
        guest1 = new Guest("Vin", "Katta", "9885952335", "vk@gmail.com", "Guntur");
        guest2 = new Guest("Aksh", "Katta", "8978420914", "vk@gmail.com", "Guntur");
        guest3 = new Guest("Ayra", "Katta", "6308536380", "vk@gmail.com", "Guntur");
        
        hotel = new Hotel("Hyatt Place", HotelType.SUPERIOR, "Gachibowli", "India");
        guest1.setHotel(hotel);
        guest2.setHotel(hotel);
        guest3.setHotel(hotel);
        
        List<Guest> g = new ArrayList<>();
        g.add(guest1);
        g.add(guest2);
        g.add(guest3);
        
        hotel.setGuestList(g);
        tx.begin();
        em.persist(hotel);
        tx.commit();
        tx.begin();
        em.remove(guest1);
        em.remove(guest2);
        em.remove(guest3);
        em.remove(hotel);
        tx.commit();
        System.out.println("edu.iit.sat.itmd4515.vkatta.domain.HotelJPARelationshipTest.testManyToOneBiDirectionalGuestHotelRelationship()");
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
    
    private void readGuestEntry(String guestName){
        guest = em.createNamedQuery("Hotel.findByName", Guest.class)
                .setParameter("name", guestName)
                .getSingleResult();
        
    }
    private void readRoomEntry(String roomNumber){
        room = em.createNamedQuery("Room.findByNumber", Room.class)
                .setParameter("number", roomNumber)
                .getSingleResult(); 
    }
    
    private void deleteHotelEntry(String hotelName){
        tx.begin();
        readHotelEntry(hotelName);
        em.remove(hotel);
        tx.commit();
    }
    
    private void deleteEntries(String hotelName, String roomNumber){
        tx.begin();
        readRoomEntry(roomNumber);
        em.remove(room);
        readHotelEntry(hotelName);
        em.remove(hotel);
        tx.commit();
    }
    
   
    @AfterEach
    public void afterEachTestMethod() {
        deleteHotelEntry("Hyatt");
    }

    @AfterAll
    public static void afterAllExecution() {

    }
}
