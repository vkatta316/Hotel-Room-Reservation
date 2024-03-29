/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.security;

import edu.iit.sat.itmd4515.vkatta.domain.Hotel;
import edu.iit.sat.itmd4515.vkatta.domain.Room;
import edu.iit.sat.itmd4515.vkatta.service.AbstractService;
import jakarta.ejb.Stateless;
import java.util.List;

/**
 * Group related methods
 * @author vinaychowdarykatta
 */
@Stateless
public class GroupService extends AbstractService<Group>{
    
    /**
     *
     */
    public GroupService() {
        super(Group.class);
    }
    
    /**
     *
     * @return
     */
    public List<Group> findAll(){
        return super.findAll("Group.findAll");
    }
   
    /**
     * Find the group by passing name
     * @param name
     * @return name of the group
     */
    public Group findGroupByName(String name) {
        return em.createNamedQuery("Group.findByName", Group.class)
                .setParameter("name", name)
                .getSingleResult();
    }
    
    /**
     * Add an user to the group
     * @param user name
     * @param group name
     */
    public void addUserToGroup(User user, Group group){
        
        User managedUserRef = em.getReference(User.class,user.getUserName());
        managedUserRef.addGroup(group);
        em.merge(managedUserRef);  
    }
}
