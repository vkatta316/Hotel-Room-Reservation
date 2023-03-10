/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Booking;
import edu.iit.sat.itmd4515.vkatta.domain.BookingType;
import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.domain.Hotel;
import edu.iit.sat.itmd4515.vkatta.domain.HotelType;
import edu.iit.sat.itmd4515.vkatta.domain.Payment;
import edu.iit.sat.itmd4515.vkatta.domain.PaymentType;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Startup
@Singleton
public class DatabaseInitService {
    
    private static final Logger LOG = Logger.getLogger(DatabaseInitService.class.getName());
    
    @EJB private GuestService guestService;
    @EJB private HotelService hotelService;
    @EJB private RoomService roomService;
    @EJB private PaymentService paymentService;
    @EJB private BookingService bookingService;
    

    public DatabaseInitService() {
        
    }
    
    @PostConstruct
    private void postConstruct(){
        LOG.info("DatabaseInitService Post Construct");
       
        // Create a Hotel
        Hotel h1 = new Hotel("Hyatt Place", HotelType.BUDGET, "Bed and Breakfast hotel", "Heathrow London");
        hotelService.create(h1);
        
        // Create Guests
        Guest g1 = new Guest("Vinay", "Katta", "6308536380", "vk@gmail.com", "Chicago");
        Guest g2 = new Guest("Akshitha", "Katta", "8978420914", "ak@gmail.com", "Chicago");
       
        Guest g3 = new Guest("Ayansh", "Katta", "9885952335", "akatta@gmail.com", "Chicago");
        Guest g4 = new Guest("Ayra", "Katta", "9885952335", "katta@gmail.com", "Chicago");
        
        guestService.create(g1);
        guestService.create(g2);
       
        
        // Set hotel id to guest
        g1.setHotel(h1);
        g2.setHotel(h1);
        
        
        // Add all guests to list
        List<Guest> guestList = new ArrayList<>();
        guestList.add(g1);
        guestList.add(g2);
       
        
        // Create Rooms
        Room r1 = new Room("1", "Single", "Single Room");
        Room r2 = new Room("2", "Double", "Double Room");
        Room r3 = new Room("3", "Quad", "Quad Room");
        roomService.create(r1);
        roomService.create(r2);
        roomService.create(r3);
        
        // Set room id to guest
        g1.setRoom(r1);
        g2.setRoom(r2);
        
        // Add all guests list to Hotel
        h1.setGuestList(guestList);
        
        // Create bookings for guest
        Booking b1 = new Booking("On Vinay Katta", BookingType.ONLINE, LocalDate.now(), LocalDate.of(2023, 04, 26), "Booking For Vinay Katta");
        Booking b2 = new Booking("On Ayra Katta", BookingType.ONLINE, LocalDate.now(), LocalDate.of(2023, 04, 26), "");
        Booking b3 = new Booking("On Ayansh Katta", BookingType.ONLINE, LocalDate.now(), LocalDate.of(2023, 04, 26), "");
        //bookingService.create(b1);
        
        
        Payment p1 = new Payment(LocalDate.now(), 3000L, PaymentType.ONLINE);
        Payment p2 = new Payment(LocalDate.now(), 5000L, PaymentType.ONLINE);
        Payment p3 = new Payment(LocalDate.now(), 4000L, PaymentType.ONLINE);
        paymentService.create(p1);
        paymentService.create(p2);
        
        
        // Add Guest Id to Payment
        p1.setGuest(g1);
        p2.setGuest(g2);
        
        Booking book = new Booking("VK Booking", BookingType.ONLINE, LocalDate.now(), 
                LocalDate.of(2023, 6, 26), "Customer make this booking online");
        book.makeBooking(g1, h1, p1);
       
        Booking book2 = new Booking("AK Booking", BookingType.ONLINE, LocalDate.of(2023, 6, 26), 
                LocalDate.of(2023, 9, 26), "Customer make this booking at desk");
        book2.makeBooking(g2, h1, p2);
        
        bookingService.create(book);
        bookingService.create(book2);
        
        g1.setBooking(book);
        g2.setBooking(book2);
        
        for(Hotel h: hotelService.findAll()){
            LOG.info(" Hotel Information ==================================");
            LOG.info(" Hotel " + h.toString());
            LOG.info(" Guests in the Hotel  ==================================");
            for(Guest g: h.getGuestList()){
                LOG.info("\t \t" + g.toString()); 
                LOG.info("\t \t" + g.getBooking().toString()); 
            
            }
            
        }
        
        for(Guest guest: guestService.findAll()){
            LOG.info(" Guest Information ==================================");
            LOG.info("Guest " + guest.toString());
            LOG.info(" Guest Hotel Booking ==================================");
            for(Booking b: guest.getBookings()){
                LOG.info("\t \t" +  b.getHotel().toString());  
                LOG.info("\t \t" +  b.getGuest().toString());  
                LOG.info("\t \t" +  b.getPayment().toString());  
                LOG.info("\t \t" +  guest.getBooking().toString());  
            }
        }
        
        for (Booking b: bookingService.findAll()){
            LOG.info("Payment information for Bookings ==================================");
            LOG.info("Booking Information for "+ b.getId() +" "  + b.toString());
            LOG.info(" \t \t Payment for the booking : "+ b.getId() +" "  + b.getPayment().toString());
            LOG.info(" \t \t Guest of this booking "+ b.getId() +" "  + b.getGuest().toString());
        }
       
        for(Payment p : paymentService.findAll()){
            LOG.info("List of all Payments " + p.toString());
        }
    }
    
}
