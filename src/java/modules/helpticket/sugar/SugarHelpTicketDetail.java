/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.helpticket.sugar;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import modules.core.BaseHelpTicket;
import modules.jira.JiraReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarHelpTicketDetail {

    private int actionNeeded = 0;
    private String remainingTimeMin = "0h 0m";
    private String remainingTimeMax = "0h 0m";

    private String actionNeededColor = "green";
    private String remainingTimeMinColor = "green";
    private String remainingTimeMaxColor = "green";

    List<BaseHelpTicket> helptickets;

    public SugarHelpTicketDetail() throws ProtocolException, IOException {
        JiraReader ticket = new JiraReader();
        JsonObject json = ticket.getSugarTicket();
        JsonArray jsonarray = json.getJsonArray("issues");

        try {
            this.actionNeeded = json.getInt("total");
            if (this.actionNeeded > 0) {
                HelpTicketRemainingTime remainingTime = new HelpTicketRemainingTime();
                helptickets = new ArrayList<>();
                jsonarray.getValuesAs(JsonValue.class).stream().forEach((jsonValue) -> {
                    BaseHelpTicket ht = new BaseHelpTicket(jsonValue.toString());
                    remainingTime.getRemainingTime(ht.getRemainingTime());
                    ht.setColor(remainingTime.getColor(ht.getRemainingTime()));
                    helptickets.add(ht);
                });

                this.remainingTimeMin = remainingTime.getRemainingTimeStringMin();
                this.remainingTimeMax = remainingTime.getRemainingTimeStringMax();

                this.actionNeededColor = remainingTime.getColor(actionNeeded);
                this.remainingTimeMinColor = remainingTime.getColor(this.remainingTimeMin);
                this.remainingTimeMaxColor = remainingTime.getColor(this.remainingTimeMax);
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
