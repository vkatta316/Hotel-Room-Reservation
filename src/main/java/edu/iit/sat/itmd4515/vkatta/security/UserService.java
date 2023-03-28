/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.security;

import edu.iit.sat.itmd4515.vkatta.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Stateless
public class UserService extends AbstractService<User>{

    public UserService() {
        super(User.class);
    }

     public List<User> findAll(){
        return super.findAll("User.findAll");
    }
    
}
