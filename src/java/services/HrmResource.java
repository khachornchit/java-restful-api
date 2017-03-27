/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import modules.bug.HrmBugs;
import modules.helpticket.hrm.HrmHelpTicket;
import modules.helpticket.hrm.HrmHelpTicketDetail;
import modules.hrm.alert.HrmLiveAlert;
import modules.hrm.criticalerror.HrmCriticalError;
import modules.hrm.alert.HrmTestAlert;
import modules.hrm.priority.HrmPriority;

/**
 * REST Web Service
 *
 * @author kajornjit.songsaen
 */
@Path("hrm")
public class HrmResource {

    public HrmResource() {
    }

    @GET
    @Path("/helpticket")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelpTicket() throws Exception {
        HrmHelpTicket hrm = new HrmHelpTicket();
        return hrm.toJson();
//        return Response.ok()
//                .entity(hrm.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @GET
    @Path("/helpticketdetail")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public String getHelpTicketDetail() throws Exception {
        HrmHelpTicketDetail hrm = new HrmHelpTicketDetail();
        return hrm.toJson();
//        return Response.ok()
//                .entity(hrm.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @GET
    @Path("/bugs")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public String getBugs() throws Exception {
        HrmBugs hrm = new HrmBugs();
        return hrm.toJson();
//        return Response.ok()
//                .entity(hrm.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @GET
    @Path("/testgraylog")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public String getTestGraylog() throws Exception {
        HrmTestAlert hrm = new HrmTestAlert();
        return hrm.toJson();
//        return Response.ok()
//                .entity(hrm.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @GET
    @Path("/livegraylog")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public String getLiveGraylog() throws Exception {
        HrmLiveAlert hrm = new HrmLiveAlert();
        return hrm.toJson();
//        return Response.ok()
//                .entity(hrm.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @GET
    @Path("/criticalerror")
//    @Produces(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getCriticalError() throws Exception {
        HrmCriticalError hrm = new HrmCriticalError();
        return hrm.toJson();
        
//        return Response.ok()
//                .entity(hrm.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }

    @GET
    @Path("/priority")
//    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public String getPriority() throws Exception {
        HrmPriority priority = new HrmPriority();
        return priority.toJson();
        
//        return Response.ok()
//                .entity(priority.toJson())
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
    }
}
