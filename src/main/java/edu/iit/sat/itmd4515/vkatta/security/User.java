/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.security;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinaychowdarykatta
 */
@Entity
@Table(name = "SEC_USER")
@EntityListeners(UserPasswordHash.class)
@NamedQuery(name = "User.findByName" , query = "select user from User user where user.userName = :name")
@NamedQuery(name = "User.findAll", query="select user from User user")
public class User {

    @Id
    private String userName;
    private String password;
    private boolean enabled;
    
    @ManyToMany
    @JoinTable(name = "SEC_USER_GROUPS", joinColumns = @JoinColumn(name = "USERNAME"),
            inverseJoinColumns = @JoinColumn(name ="GROUPNAME"))
    private List<Group> groups = new ArrayList<>();;

    public User() {
    }

    public User(String userName, String password, boolean enabled) {
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }
    
    //helper method
    public void addGroup(Group group){
        if (!this.groups.contains(group)) 
             this.groups.add(group);
        if (!group.getUsers().contains(this)) 
            group.getUsers().add(this);
    }
    
    public void removeGroup(Group group){
        if (this.groups.contains(group)) 
             this.groups.remove(group);
        if (group.getUsers().contains(this)) 
            group.getUsers().remove(this);
    }


    /**
     * Get the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the value of userName
     *
     * @param userName new value of userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
