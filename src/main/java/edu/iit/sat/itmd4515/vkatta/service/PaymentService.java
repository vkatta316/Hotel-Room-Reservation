/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.service;

import edu.iit.sat.itmd4515.vkatta.domain.Payment;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Stateless
public class PaymentService extends AbstractService<Payment>{
    
   public PaymentService() {
        super(Payment.class);
    }
    
    public List<Payment> findAll(){
        return super.findAll("Payment.findAll");
    }
    
}
