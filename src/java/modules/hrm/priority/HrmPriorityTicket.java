/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.priority;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import mysql.connection.BaseMySQLDatabase;
import utilities.CallRestful;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmPriorityTicket extends BaseMySQLDatabase {

    private List<String> tickets;

    public List<String> getTickets() {
        return tickets;
    }

    public HrmPriorityTicket() {
        databaseurl = "jdbc:mysql://vmthrm01.tsrv.ebuero.de:3306/sugarhrm-test?zeroDateTimeBehavior=convertToNull";
        user = "sugarhrm-test";
        password = "Ooyia3hohp4puim9";

        getPriorityTickets();
    }

    private void getPriorityTickets() {
        tickets = new ArrayList<>();
        String requesturl = "http://hrm.clbs.co.th/custom/priority/tblHrmPriority.php";
        CallRestful rest = new CallRestful();
        String reading = rest.GetJson(requesturl);
        String[] parts = reading.split(",");

        for (String part : parts) {
            if (part.length() > 2) {
                System.out.println(part);
                tickets.add(part);
            }
        }
    }

    public String getPriorityTicketSyntax() {
        String output = "";
        for (String ticket : tickets) {
            if (output.isEmpty()) {
                output = "key=" + ticket;
            } else {
                output = output + " OR " + "key=" + ticket;
            }
        }
        return output;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
