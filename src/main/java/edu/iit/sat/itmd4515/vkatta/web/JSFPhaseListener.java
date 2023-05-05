/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.vkatta.web;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import java.util.logging.Logger;

/**
 * An interface implemented by object will be notified at the beginning and ending of processing 
 * @author vinaychowdarykatta
 */
public class JSFPhaseListener implements PhaseListener{

    private static final Logger LOG = Logger.getLogger(JSFPhaseListener.class.getName());

    /**
     *
     * @return
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

    /**
     * Beginning of processing 
     * @param event
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        if(event.getPhaseId() == PhaseId.RESTORE_VIEW){
            LOG.info("A New JSF is starting");
        }
        
       LOG.info("Before the JSF Phase" + event.getPhaseId().toString());
    }

    /**
     * Ending of processing 
     * @param event
     */
    @Override
    public void afterPhase(PhaseEvent event) {
       LOG.info("After the JSF Phase" + event.getPhaseId().toString());
       
        if(event.getPhaseId() == PhaseId.RENDER_RESPONSE){
            LOG.info("A New JSF has completed");
        }
        
       
    }
}
