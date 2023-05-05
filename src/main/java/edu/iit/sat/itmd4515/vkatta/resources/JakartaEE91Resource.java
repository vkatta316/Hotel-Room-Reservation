package edu.iit.sat.itmd4515.vkatta.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

/**
 * The container will inject an instance of the requested resource into the application component when the component is initialized
 * @author 
 */
@Path("jakartaee9")
public class JakartaEE91Resource {
    
    /**
     *
     * @return
     */
    @GET
    public Response ping(){
        return Response
                .ok("ping Jakarta EE")
                .build();
    }
}
