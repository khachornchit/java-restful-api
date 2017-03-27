/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.priority;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import modules.core.BaseTasks;
import modules.core.BaseCount;
import modules.core.BaseProgressive;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmPriority {

    private BaseCount countopen;
    private BaseCount countinprogress;
    private BaseCount counttotal;
    private BaseProgressive countpriority;

    List<BaseTasks> priority;
    List<BaseTasks> opens;
    List<BaseTasks> inprogresss;

    public HrmPriority() throws ProtocolException, IOException {
        getPriorityList();
        getOpenList();
        getInProgressList();
        Summarize();
    }

    private void Summarize() {
        counttotal = new BaseCount();
        counttotal.setName("HRM Priority Total");
        counttotal.setCount(countopen.getCount() + countinprogress.getCount());

        Collections.sort(opens, BaseTasks.CompareStatus);
        Collections.sort(inprogresss, BaseTasks.CompareStatus);
        Collections.sort(priority, BaseTasks.CompareKey);
    }

    private void getOpenList() throws ProtocolException, IOException {
        countopen = new BaseCount();
        HrmPriorityReader hrmpriority = new HrmPriorityReader();
        JsonObject json = hrmpriority.getPriorityOpen();
        JsonArray jsonarray = json.getJsonArray("issues");

        try {
            opens = new ArrayList<>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonValue jsonValue = jsonarray.get(i);
                addOpen(new BaseTasks(jsonValue));
            }
            countopen.setCount(opens.size());
            countopen.setName("HRM Priority Open");
            countopen.setColorPass();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getPriorityList() throws ProtocolException, IOException {
        countpriority = new BaseProgressive();
        HrmPriorityReader hrmpriority = new HrmPriorityReader();
        JsonObject json = hrmpriority.getPriorityProgressive();
        JsonArray jsonarray = json.getJsonArray("issues");

        try {
            int cinprogress = 0;
            int cdone = 0;
            int copen = 0;
            priority = new ArrayList<>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonValue jsonValue = jsonarray.get(i);
                BaseTasks task = new BaseTasks(jsonValue);

                priority.add(task);
                if (task.IsDone()) {
                    cdone = cdone + 1;
                }
                if (task.IsInProgress()) {
                    cinprogress = cinprogress + 1;
                }
                if (task.IsOpen()) {
                    copen = copen +1;
                }
            }
            countpriority.setCounttoal(priority.size());
            countpriority.setCountinprogress(cinprogress);
            countpriority.setCountdone(cdone);           

            countpriority.setCount(priority.size());
            countpriority.setName("HRM Priority Progress");
            countpriority.setColorPass();
            
            countpriority.setProgressive(String.format("%,.2f", (cdone / priority.size())));
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private void getInProgressList() throws ProtocolException, IOException {
        this.countinprogress = new BaseCount();
        HrmPriorityReader hrmpriority = new HrmPriorityReader();
        JsonObject json = hrmpriority.getPriorityInProgress();
        JsonArray jsonarray = json.getJsonArray("issues");

        try {
            inprogresss = new ArrayList<>();
            for (int i = 0; i < jsonarray.size(); i++) {
                JsonValue jsonValue = jsonarray.get(i);
                addInProgress(new BaseTasks(jsonValue));
            }
            countinprogress.setCount(inprogresss.size());
            countinprogress.setName("HRM Priority");
            countinprogress.setColor("yellow");
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
    
    public int addInProgress(BaseTasks task) {
        if (priority.isEmpty()) {
            inprogresss.add(task);
            return 1;
        } else {
            for (BaseTasks existPriority : priority) {
                if (existPriority.EqualKey(task)) {
                    return 0;
                }
            }
            inprogresss.add(task);
            return 1;
        }
    }

    public int addOpen(BaseTasks task) {
        if (priority.isEmpty()) {
            opens.add(task);
            return 1;
        } else {
            for (BaseTasks existPriority : priority) {
                if (existPriority.EqualKey(task)) {
                    return 0;
                }
            }
            opens.add(task);
            return 1;
        }
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
