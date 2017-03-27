/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import models.WidgetCount;
import modules.bug.SugarBugs;
import modules.criticalerror.CaiError;
import modules.criticalerror.SugarError;
import modules.criticalerror.SugarErrorDetail;
import modules.helpticket.sugar.SugarHelpTicket;
import modules.helpticket.sugar.SugarHelpTicketDetail;

/**
 * REST Web Service
 *
 * @author kajornjit.songsaen
 */
@Path("sugar")
public class SugarResource {

    public SugarResource() {
    }

    @GET
    @Path("/caierror")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getCaiError() throws Exception {
        WidgetCount count = new WidgetCount();
        CaiError cai = new CaiError();
        count.setName("CAI Error");
        count.setCount(cai.getCount());
        count.setColor(cai.getColor());
        return count.toJson();
    }

    @GET
    @Path("/sugarerror")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSugarError() throws Exception {
        WidgetCount count = new WidgetCount();
        SugarError sugar = new SugarError();
        count.setName(sugar.getName());
        count.setCount(sugar.getCount());
        count.setColor(sugar.getColor());
        return count.toJson();
    }

    @GET
    @Path("/sugarerrortotal")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSugarErrorTotal() throws Exception {
        SugarErrorDetail sugardetail = new SugarErrorDetail();

        WidgetCount count = new WidgetCount();
        count.setName("Critical Error");
        count.setCount(sugardetail.getTotalcount());
        count.setColor(sugardetail.getTotalcolor());
        return count.toJson();
    }

    @GET
    @Path("/sugarerrordetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSugarErrorDetail() throws Exception {
        SugarErrorDetail sugardetail = new SugarErrorDetail();
        return sugardetail.toJson();
    }

    @GET
    @Path("/sugarhelpticket")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSugarHelpTicket() throws Exception {
        SugarHelpTicket sugar = new SugarHelpTicket();
        return sugar.toJson();
    }

    @GET
    @Path("/sugarhelpticketdetail")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSugarHelpTicketDetail() throws Exception {
        SugarHelpTicketDetail helpticket = new SugarHelpTicketDetail();
        return helpticket.toJson();
    }

    @GET
    @Path("/sugarbugs")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getSugarBugs() throws Exception {
        SugarBugs bugs = new SugarBugs();
        return bugs.toJson();
    }
}