/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.graylog;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import modules.graylog.base.BaseGraylog;
import modules.hrm.alert.HrmGraylogReader;
import utilities.JsonUtil;
import java.util.Comparator;

/**
 *
 * @author kajornjit.songsaen
 */
public class GraylogHrm extends BaseGraylog {

    private String solutiontype = null;
    private String errorhandling = null;
    private String action = null;
    private String cycle = null;
    private String applicationid = null;
    private String succesful = null;
    private String workflow = null;
    private String actionurl = null;
    private String comment = null;
    private String mailcycle = null;
    private String taskcycle = null;
    private boolean resolved = false;

    private static String refcriticalemail = "The email could not be sent to";
    private static String refcriticaltask = "The Task cannot been created in a second try";
    private static String reftestid = "b88a3bad-f7ca-74d5-ac8a-56cebaf7b6d9";
    private static String refmsgerrorhandling = "error handling";

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public void setMailcycle(String mailcycle) {
        this.mailcycle = mailcycle;
        if (taskcycle == null) {
            taskcycle = "0";
        }
    }

    public void setTaskcycle(String taskcycle) {
        this.taskcycle = taskcycle;
        if (mailcycle == null) {
            mailcycle = "0";
        }
    }

    public String getMailcycle() {
        return mailcycle;
    }

    public String getTaskcycle() {
        return taskcycle;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWorkflow() {
        return workflow;
    }

    public GraylogHrm(String message, boolean bText, boolean bErrorHandling, String workflow) {
        try {
            this.workflow = workflow;

            String[] messages = message.split(",");
            this.timestamp = messages[0].trim().replaceAll("\"", "");
            this.source = messages[1].trim().replaceAll("\"", "");
            this.project = messages[2].trim().replaceAll("\"", "");
            this.level = Integer.parseInt(messages[3].trim().replaceAll("\"", ""));
            this.message = messages[4].trim().replaceAll("\"", "");

            String remove = messages[0] + "," + messages[1] + "," + messages[2] + "," + messages[3] + "," + messages[4] + ",";
            String fmsg1 = message.trim().replaceAll(remove, "");
            String fmsg2 = fmsg1.substring(1, fmsg1.length() - 1).replaceAll(",\\\"", ",").replaceAll("\\\":\\\"", ":");
            String full_message = fmsg2.substring(2, fmsg2.length() - 2).replaceAll("\\\",", ",");

            String[] full_messages = full_message.split(",");

            String[] errorhandling1 = full_messages[0].split(":");
            errorhandling = errorhandling1[1].replaceAll("\"", "");

            String[] action1 = full_messages[1].split(":");
            action = action1[1].replaceAll("\"", "");

            String[] cycle1 = full_messages[2].split(":");
            cycle = cycle1[1].replaceAll("\"", "");
            cycle = new Integer(getCycleNumber(cycle)).toString();

            String[] applicationid1 = full_messages[3].split(":");
            applicationid = applicationid1[1].replaceAll("\"", "");

            String[] succesful1 = full_messages[4].split(":");
            succesful = succesful1[1].replaceAll("\"", "").replaceAll("}", "");

            ContainmentAction();
        } catch (Exception e) {

        }
    }

    public GraylogHrm(String message, boolean bText) {
        try {
            String[] messages = message.split(",");
            this.timestamp = messages[0].trim().replaceAll("\"", "");
            this.source = messages[1].trim().replaceAll("\"", "");
            this.project = messages[2].trim().replaceAll("\"", "");
            this.level = Integer.parseInt(messages[3].trim().replaceAll("\"", ""));
            this.message = messages[4].trim().replaceAll("\"", "");
            this.full_message = FullMessageProcessing(messages[5].trim().replaceAll("\"", ""));
        } catch (Exception e) {

        }
    }

    public GraylogHrm(String message) {

        try {
            JsonObject json = JsonUtil.ReadDataToJsonObject(message);
            JsonObject objmessage = JsonUtil.ReadObjectToJsonObject(json.get("message"));
            this.level = getJsonInt(objmessage, "level");
            this.project = getJsonString(objmessage, "project");
            this.source = getJsonString(objmessage, "source");
            this.message = getJsonString(objmessage, "message");
            this.timestamp = getJsonString(objmessage, "timestamp");
            this.full_message = FullMessageProcessing(getJsonString(objmessage, "full_message"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private static boolean IsTestId(String thisLine) {
        return thisLine.contains(reftestid);
    }

    public static boolean IsHrmLive(String thisLine) {
        return !IsTestId(thisLine) && !IsCriticalBeforeStandardizeFullMessage(thisLine);
    }

    public static boolean IsHrmTest(String thisLine) {
        return IsTestId(thisLine);
    }

    public static boolean IsHrmLiveId(String thisLine) {
        return !IsTestId(thisLine);
    }

    private static boolean IsCriticalBeforeStandardizeFullMessage(String thisLine) {
        return thisLine.contains(refcriticaltask) || thisLine.contains(refcriticalemail);
    }

    public static boolean IsErrorHandling(String thisLine) {
        return thisLine.contains(refmsgerrorhandling);
    }

    private String getErrorHandling(String full_message) {
        JsonReader reader = Json.createReader(new StringReader(full_message));
        JsonObject obj = reader.readObject();

        errorhandling = obj.getString("type");
        action = obj.getString("action");
        cycle = obj.getString("cycle");
        applicationid = obj.getString("applicationid");
        succesful = obj.getString("successful");

        errorhandling = "hello";

        return full_message;
    }

    public String getErrorhandling() {
        return errorhandling;
    }

    public String getAction() {
        return action;
    }

    public String getCycle() {
        return cycle;
    }

    public String getApplicationid() {
        return applicationid;
    }

    public boolean getSuccesful() {
        return succesful.contains("true");
    }

    public boolean IsCriticalError() {
        boolean firstcycle = (getCycleNumber(cycle) == 1);
        if (succesful.contains("false")) {
            if (action.equals("task") && !firstcycle) {
                return true;
            } else if (action.equals("mail")) {
                return true;
            } else if (action.equals("mailhistory")) {
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean IsProductionAlert() {
        return action.equals("mailhistory");
    }

    public void CombineErrorActionFromEmail(GraylogHrm hrm) {
        action = "taskandmail";
        taskcycle = cycle;
        mailcycle = hrm.getCycle();
        ContainmentAction();
    }

    public void CombineErrorActionFromTask(GraylogHrm hrm) {
        action = "taskandmail";
        taskcycle = hrm.getCycle();
        mailcycle = cycle;
        ContainmentAction();
    }

    public boolean IsResolved(GraylogHrm hrmError) {
        return action.contains(hrmError.getAction()) && applicationid.equals(hrmError.getApplicationid());
    }

    public void CombineSuccessfulActionFromEmail(GraylogHrm hrm) {
        action = "taskandmail";
        taskcycle = cycle;
        mailcycle = hrm.getCycle();
    }

    public void CombineSuccessfulActionFromTask(GraylogHrm hrm) {
        action = "taskandmail";
        taskcycle = hrm.getCycle();
        mailcycle = cycle;
    }

    private void ContainmentAction() {
        if (IsCriticalError()) {
            if (applicationid != null) {
                if (workflow == HrmGraylogReader.getWorkflowInteressent() && action.equals("task")) {
                    actionurl = "http://hrm.clbs.co.th/addonplugin/workflow/error_handling_interessent.php?id=" + applicationid + "&action=taskonly" + "&taskcycle=" + (getCycleNumber(cycle) + 1);
                }
                if (workflow == HrmGraylogReader.getWorkflowInteressent() && action.equals("mail")) {
                    actionurl = "http://hrm.clbs.co.th/addonplugin/workflow/error_handling_interessent.php?id=" + applicationid + "&action=mailonly" + "&mailcycle=" + (getCycleNumber(cycle) + 1);
                }
                if (workflow == HrmGraylogReader.getWorkflowInteressent() && action.equals("taskandmail")) {
                    actionurl = "http://hrm.clbs.co.th/addonplugin/workflow/error_handling_interessent.php?id=" + applicationid + "&action=taskandmail" + "&taskcycle=" + (getCycleNumber(taskcycle) + 1) + "&mailcycle=" + (getCycleNumber(mailcycle) + 1);
                }

                if (workflow == HrmGraylogReader.getWorkflowWaS() && action.equals("task")) {
                    actionurl = "http://hrm.clbs.co.th/addonplugin/workflow/error_handling_was.php?id=" + applicationid + "&action=taskonly" + "&taskcycle=" + (getCycleNumber(cycle) + 1);
                }
                if (workflow == HrmGraylogReader.getWorkflowWaS() && action.equals("mail")) {
                    actionurl = "http://hrm.clbs.co.th/addonplugin/workflow/error_handling_was.php?id=" + applicationid + "&action=mailonly" + "&mailcycle=" + (getCycleNumber(cycle) + 1);
                }

                if (workflow == HrmGraylogReader.getWorkflowWaS() && action.equals("taskandmail")) {
                    actionurl = "http://hrm.clbs.co.th/addonplugin/workflow/error_handling_was.php?id=" + applicationid + "&action=taskandmail" + "&taskcycle=" + (getCycleNumber(taskcycle) + 1) + "&mailcycle=" + (getCycleNumber(mailcycle) + 1);
                }
            }
        }
    }

    public boolean EqualTask(GraylogHrm hrm) {
        return this.IsTask() && hrm.getApplicationid().equals(applicationid) && hrm.getWorkflow().equals(workflow);
    }

    public boolean EqualMail(GraylogHrm hrm) {
        return this.IsMail() && hrm.getApplicationid().equals(applicationid) && hrm.getWorkflow().equals(workflow);
    }

    public boolean IsMail() {
        return action.equals("mail");
    }

    public boolean IsNullOrEmptyId() {
        return applicationid == null || applicationid.isEmpty() || applicationid.length() < 10;
    }

    public boolean IsDate29092016() {
        return timestamp.contains("2016-09-29");
    }

    public boolean IsTask() {
        return action.equals("task");
    }

    public boolean IsMailHistory() {
        return action.equals("mailhistory");
    }

    private int getCycleNumber(String cycleString) {
        switch (cycleString) {
            case "first":
                return 1;
            case "second":
                return 2;
            case "third":
                return 3;
            case "fourth":
                return 4;
            case "fifth":
                return 5;
            case "sixth":
                return 6;
            case "seventh":
                return 7;
            case "eighth":
                return 8;
            case "ninth":
                return 9;
            case "tenth":
                return 10;
            default:
                return Integer.parseInt(cycleString);
        }
    }

    private String getCycleString(int cycleNumber) {
        switch (cycleNumber) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "fourth";
            case 5:
                return "fifth";
            case 6:
                return "sixth";
            case 7:
                return "seventh";
            case 8:
                return "eighth";
            case 9:
                return "ninth";
            case 10:
                return "tenth";
            default:
                return "unknow";
        }

    }

    public static Comparator<GraylogHrm> CompareTimeStamp = (GraylogHrm hrm1, GraylogHrm hrm2) -> {
        String t1 = hrm1.timestamp;
        String t2 = hrm2.timestamp;
        return t1.compareTo(t2);
    };
}
