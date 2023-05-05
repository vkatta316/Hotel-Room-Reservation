/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.security;

import edu.iit.sat.itmd4515.vkatta.domain.Guest;
import edu.iit.sat.itmd4515.vkatta.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Sign up User 
 * @author vinaychowdarykatta
 */
@Stateless
public class UserService extends AbstractService<User>{

    /**
     *
     */
    public UserService() {
        super(User.class);
    }

    /**
     *
     * @return list of all users
     */
    public List<User> findAll(){
        return super.findAll("User.findAll");
    }
     
    /**
     * Sign up a new customer 
     * @param guest
     */
    public void signupNewCustomerUser(Guest guest){
        
        String groupQuery = "select g from Group g where g.groupName = 'CUSTOMER_GROUP'";
        
        Group customerGroup = 
                em.createQuery(groupQuery, Group.class).getSingleResult();
        
        User newUser = guest.getUser();
        newUser.addGroup(customerGroup);
        newUser.setEnabled(true);
        em.persist(newUser);
        
        // set with managed entity that we just persisted
        guest.setUser(newUser);
        em.persist(guest);
    }
    
}
