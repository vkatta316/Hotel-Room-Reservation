/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.lab3;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;

import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author vinaychowdarykatta
 */
@WebServlet(urlPatterns = {"/staff"})
public class StaffServlet extends HttpServlet {
    
    @Resource
    Validator validator;
    
    //@Resource(name = "java:app/jdbc/itmd4515DS")
    //DataSource ds;
    
    @PersistenceContext(name="itmd4515PU")
    EntityManager em;
    
    @Resource
    UserTransaction tx;
    
    private static final Logger LOG = Logger.getLogger(StaffServlet.class.getName());
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        LOG.info("StaffServlet.doGet()");
        
        // Redirect 
        resp.sendRedirect(req.getContextPath() + "/staff.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        LOG.info("StaffServlet.doPost()");
        // Receive plain text parameters
        String staffIdParam = req.getParameter("staffId");
        String firstNameParam = req.getParameter("firstName");
        String lastNameParam = req.getParameter("lastName");
        String emailParam = req.getParameter("email");
        String storeParam = req.getParameter("storeId");
        String addressParam = req.getParameter("addressId");
        String dateParam = req.getParameter("exDate");
        String userNameParam = req.getParameter("userName");

        
        Integer staffId = null;
        Integer storeId= null;
        Integer addrId= null;
        LocalDate exDate=null;

        
        // Convert to data types
        if (staffIdParam != null && !staffIdParam.isBlank()) {
            staffId= Integer.valueOf(staffIdParam);
        }
        if (storeParam != null && !storeParam.isBlank()) {
           storeId= Integer.valueOf(storeParam);
        }
        if (addressParam != null && !addressParam.isBlank()) {
          addrId = Integer.valueOf(addressParam);
        }
         if (dateParam != null && !dateParam.isBlank()) {
           exDate = LocalDate.parse(dateParam);
        }
        
        LOG.info(staffId + " " + firstNameParam +" " + lastNameParam +" " + emailParam+ " " + storeId +" " + addrId+ " " + userNameParam +" " + exDate);
       
        Staff s = new Staff(staffId, firstNameParam, lastNameParam, addrId, emailParam, storeId, userNameParam, exDate);
        LOG.info("Our new staff member is " + s.toString());
        Set<ConstraintViolation<Staff>> violations = validator.validate(s);
        if (violations.size() > 0) {
            for(ConstraintViolation<Staff> violation: violations){
                LOG.info(violation.toString());
            }
            req.setAttribute("violations", violations);
            req.setAttribute("staff", s);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("staff.jsp");
            requestDispatcher.forward(req, resp);
        }
        else{
            //createStaffRecord(query,s);
            
            createStaffJPA(s);
            req.setAttribute("staff", s);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("confirmation.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
    
    private void createStaffJPA(Staff staffObj){
        try {
            tx.begin();
            em.persist(staffObj);
            tx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(StaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
