/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import edu.iit.sat.itmd4515.vkatta.security.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vinaychowdarykatta
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    private User user;

    @Inject
    SecurityContext securityContext;
    @Inject
    FacesContext facesContext;

    public LoginController() {
    }

    @PostConstruct
    private void postConstruct() {
        LOG.info("LoginController.postConstruct()");
        user = new User();
    }
    
    public String getAuthenticatedUser(){
        // REMOTE_USER
        return facesContext.getExternalContext().getRemoteUser();
    }
    
    public boolean isAdmin(){
        return securityContext.isCallerInRole("ADMIN_ROLE");
    }
    
    public boolean isCustomer(){
        return securityContext.isCallerInRole("CUSTOMER_ROLE");
    }

    public boolean isManager(){
        return securityContext.isCallerInRole("MANAGER_ROLE");
    }

    //action methods
    public String doLogin() {
        LOG.info("LoginController.doLogin() for " + this.user.getUserName());
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        
        // Authentication
        Credential cred = new UsernamePasswordCredential(
                this.user.getUserName(), 
                this.user.getPassword());
        
        AuthenticationStatus status = securityContext.authenticate(
                request, 
                response, 
                AuthenticationParameters.withParams().
                        credential(cred));
        
        switch(status){
            case SUCCESS: 
                LOG.info(status.toString());
                break;
                
            case SEND_FAILURE:
                LOG.info(status.toString());
                return "/error.xhtml";
            
            case NOT_DONE:
                LOG.info(status.toString());
                return "/error.xhtml";
            
            case SEND_CONTINUE:
                LOG.info(status.toString());
                break;
                
        }
        
        return "/welcome.xhtml?faces-redirect=true";
    }
    
    public String doLogout(){
         HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/login.xhtml?faces-redirect=true";
    }
    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
