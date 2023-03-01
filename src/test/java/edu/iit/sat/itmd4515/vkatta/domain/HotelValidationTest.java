/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author vinaychowdarykatta
 */
public class HotelValidationTest {
    private static Validator validator;

    @BeforeAll
    public static void beforeAllExecution() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @BeforeEach
    public void beforeEachTestMethod() {

    }
    
     @Test
    public void hotelIsValid() {
        // Create a Hotel data
        Hotel hotelObj = new Hotel("Hyatt", HotelType.SUPERIOR, "Bed and Breakfast", "London", LocalDate.now());
        // validate the data
        Set<ConstraintViolation<Hotel>> violations = validator.validate(hotelObj);
        
        //assert the values
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    public void validatePastDateSearchAndEmptyHotelName() {
        // create a hotel data
        Hotel hotelObj = new Hotel("", HotelType.SUPERIOR, "Bed and Breakfast", "London", LocalDate.of(2023, 01, 26));

        // validate the data
        Set<ConstraintViolation<Hotel>> violations = validator.validate(hotelObj);
        Set<String> expectedErrors = new HashSet<>();
        expectedErrors.add("size must be between 2 and 16");
        expectedErrors.add("Date can't be in the past");
        expectedErrors.add("Hotel name can't be empty");

        //assert the values
        Assertions.assertEquals(3, violations.size());
        for (ConstraintViolation<Hotel> v : violations) {
            Assertions.assertEquals(true, expectedErrors.contains(v.getMessage()));
        }

    }

    @Test
    public void validateMaxSizeForHotelName() {
        // create a Hotel data
        Hotel hotelObj = new Hotel("abcdefghijklmnopq", HotelType.SUPERIOR, "Bed and Breakfast", "London", LocalDate.now());
        // validate the data 
        Set<ConstraintViolation<Hotel>> violations = validator.validate(hotelObj);
        //assert the values
        Assertions.assertEquals(1, violations.size());
        Assertions.assertEquals("size must be between 2 and 16", violations.iterator().next().getMessage());
    }
    
    
    
    @AfterEach
    public void afterEachTestMethod() {

    }

    @AfterAll
    public static void afterAllExecution() {

    }
}
