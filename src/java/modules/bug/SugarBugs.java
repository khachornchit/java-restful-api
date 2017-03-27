/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.bug;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import modules.core.BaseTasks;
import modules.core.BaseCount;
import modules.graylog.base.BaseGraylog;
import modules.core.BugsReader;

/**
 *
 * @author kajornjit.songsaen
 */
public class SugarBugs {
    
    private BaseCount open;
    private BaseCount inprogress;
    private BaseCount backlog;
    private BaseCount total;
    
    List<BaseTasks> bugopens;
    List<BaseTasks> buginprogresss;
    List<BaseTasks> bugbacklogs;
    
    public SugarBugs() throws ProtocolException, IOException {
        BugOpenProcessing();
        BugInProgressProcessing();
        BugBacklogProcessing();
        SummarizeBugs();
    }
    
    private void SummarizeBugs() {
        total = new BaseCount();
        total.setName("Sugar Bugs");
        total.setCount(open.getCount() + inprogress.getCount() + backlog.getCount());
    }
    
    private void BugOpenProcessing() throws ProtocolException, IOException {
        open = new BaseCount();
        BugsReader bugreader = new BugsReader();
        JsonObject json = bugreader.getSugarBugsOpen();
        JsonArray jsonarray = json.getJsonArray("issues");
        
        try {
            bugopens = new ArrayList<>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonValue jsonValue = jsonarray.get(i);
                bugopens.add(new BaseTasks(jsonValue));
            }
            open.setCount(bugopens.size());
            open.setName("Open");
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
    
    private void BugInProgressProcessing() throws ProtocolException, IOException {
        this.inprogress = new BaseCount();
        BugsReader bugreadopen = new BugsReader();
        JsonObject json = bugreadopen.getSugarBugsInProgress();
        JsonArray jsonarray = json.getJsonArray("issues");
        
        try {
            this.buginprogresss = new ArrayList<>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonValue jsonValue = jsonarray.get(i);
                this.buginprogresss.add(new BaseTasks(jsonValue));
            }
            inprogress.setCount(this.buginprogresss.size());
            inprogress.setName("In Progress");
            inprogress.setColor("yellow");
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
    
    private void BugBacklogProcessing() throws ProtocolException, IOException {
        this.backlog = new BaseCount();
        BugsReader bugreadopen = new BugsReader();
        JsonObject json = bugreadopen.getSugarBugsBacklog();
        JsonArray jsonarray = json.getJsonArray("issues");
        
        try {
            this.bugbacklogs = new ArrayList<>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonValue jsonValue = jsonarray.get(i);
                this.bugbacklogs.add(new BaseTasks(jsonValue));
            }
            
            backlog.setCount(this.bugbacklogs.size());
            backlog.setName("Backlog");
            backlog.setColor("yellow");
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
