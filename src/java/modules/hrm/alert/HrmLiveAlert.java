/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.alert;

import modules.hrm.base.BaseHrmAlert;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import models.WidgetCount;
import modules.graylog.GraylogHrm;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmLiveAlert extends BaseHrmAlert {

    public HrmLiveAlert() {
        getErrorHandling();
        setCountErrorHandling3();
        setCountWaS();
        setCountInteressent();
    }

    @Override
    public void getWaSYesterday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getWaSLiveToTextYesterday();
            wasyesterday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");

                    if (!bHeader && GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader && Count < 20) {
                            wasyesterday.add(new GraylogHrm(thisLine, true));
                        }

                        if (!bHeader) {
                            Count++;
                        }
                    }
                }
                this.countwasyesterday.setCount(Count);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @Override
    public void getWaSToday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getWaSLiveToTextToday();
            wastoday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader && Count < 20) {
                            wastoday.add(new GraylogHrm(thisLine, true));
                        }

                        if (!bHeader) {
                            Count++;
                        }
                    }
                }
                this.countwastoday.setCount(Count);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @Override
    public void getInteressentYesterday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getInteressentLiveToTextYesterday();
            interessentyesterday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");

                    if (!bHeader && GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader && Count < 20) {
                            interessentyesterday.add(new GraylogHrm(thisLine, true));
                        }

                        if (!bHeader) {
                            Count++;
                        }
                    }
                }
                this.countinteressentyesterday.setCount(Count);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @Override
    public void getInteressentToday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getInteressentLiveToTextToday();
            interessenttoday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader && Count < 20) {
                            interessenttoday.add(new GraylogHrm(thisLine, true));
                        }

                        if (!bHeader) {
                            Count++;
                        }
                    }
                }
                this.countinteressenttoday.setCount(Count);
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    @Override
    public void setCountWaSToday() {
        counttotaltoday.setCount(countinteressenttoday.getCount() + countwastoday.getCount());

        countwastoday.setName("HRM WaS Live");
        countwastoday.setColorAlert();

        countinteressenttoday.setName("HRM Interessent Live");
        countinteressenttoday.setColorAlert();

        counttotaltoday.setName("HRM Test Live");
        counttotaltoday.setColorAlert();
    }

    @Override
    public void setCountWaSYesterday() {
        counttotalyesterday.setCount(countinteressentyesterday.getCount() + countwasyesterday.getCount());

        countwasyesterday.setName("HRM WaS Live");
        countwasyesterday.setColorAlert();

        countinteressentyesterday.setName("HRM Interessent Live");
        countinteressentyesterday.setColorAlert();

        counttotalyesterday.setName("HRM Live Alert");
        counttotalyesterday.setColorAlert();
    }

    @Override
    public void getErrorHandling() {
        errorhandling3 = new ArrayList<>();
        getAllError();
        
        Collections.sort(errorhandling3, GraylogHrm.CompareTimeStamp);
    }

    private void getAllError() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getAllError();

            try {
                String thisLine = null;
                int CountTotal = 0;
                int CountWaS = 0;
                int CountInteressent = 0;

                BufferedReader buffer = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = buffer.readLine()) != null) {
                    if (IsWorkflowWas(thisLine)) {
                        if (GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, workflowName);
                            if (hrm.IsProductionAlert()) {
                                CountWaS += 1;
                                errorhandling3.add(hrm);
                            }
                        }
                    }

                    if (IsWorkflowInteressent(thisLine)) {
                        if (GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, workflowName);
                            if (hrm.IsProductionAlert()) {
                                CountInteressent += 1;
                                errorhandling3.add(hrm);
                            }
                        }
                    }
                }

                CountTotal = CountWaS + CountInteressent;

                counterrorhandling3 = new WidgetCount();
                countwas = new WidgetCount();
                countinteressent = new WidgetCount();

                counterrorhandling3.setCount(CountTotal);
                countwas.setCount(CountWaS);
                countinteressent.setCount(CountInteressent);

            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    private int getErrorHandling3Was() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getErrorHandling3WaS();

            try {
                String thisLine = null;
                int Count = 0;
                BufferedReader brWaS = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = brWaS.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowWaS());
                            if (!hrm.IsCriticalError()) {
                                Count++;
                                errorhandling3.add(hrm);
                            }
                        }
                    }
                }
                return Count;
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return -1;
    }

    private int getErrorHandling3Interessent() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getErrorHandling3Interessent();

            try {
                String thisLine = null;
                int Count = 0;
                BufferedReader brWaS = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = brWaS.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowInteressent());
                            if (!hrm.IsCriticalError()) {
                                Count++;
                                errorhandling3.add(hrm);
                            }
                        }
                    }
                }
                return Count;
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return -1;
    }

    private void setCountErrorHandling3() {
        counterrorhandling3.setName("HRM Live Error");
        counterrorhandling3.setColorAlertYellow();
    }

    private void setCountWaS() {
        countwas.setName("HRM Error WaS");
        countwas.setColorAlertYellow();
    }

    private void setCountInteressent() {
        countinteressent.setName("HRM Error Interessent");
        countinteressent.setColorAlertYellow();
    }

    private boolean IsWorkflowWas(String line) {
        if (line.contains(workflowWaS)) {
            workflowName = workflowWaS;
            return true;
        } else {
            return false;
        }
    }

    private boolean IsWorkflowInteressent(String line) {
        if (line.contains(workflowInteressent)) {
            workflowName = workflowInteressent;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int addErrorHandlingInteressent(GraylogHrm hrm) {
        if (hrm.IsNullOrEmptyId() && hrm.IsDate29092016()) {
            return 0;
        }

        if (errorhandling3.isEmpty()) {
            if (hrm.IsMail()) {
                hrm.setMailcycle(hrm.getCycle());
            } else {
                hrm.setTaskcycle(hrm.getCycle());
            }
            errorhandling3.add(hrm);
            return 1;
        } else if (hrm.IsMail()) {
            for (GraylogHrm existErrorHrm : errorhandling3) {
                if (existErrorHrm.EqualTask(hrm)) {
                    errorhandling3.get(errorhandling3.indexOf(existErrorHrm)).CombineErrorActionFromEmail(hrm);
                    return 0;
                }
            }
            hrm.setMailcycle(hrm.getCycle());
            errorhandling3.add(hrm);
            return 1;
        } else if (hrm.IsTask()) {
            for (GraylogHrm existErrorHrm : errorhandling3) {
                if (existErrorHrm.EqualMail(hrm)) {
                    errorhandling3.get(errorhandling3.indexOf(existErrorHrm)).CombineErrorActionFromTask(hrm);
                    return 0;
                }
            }
            hrm.setTaskcycle(hrm.getCycle());
            errorhandling3.add(hrm);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int addSuccessfulWaS(GraylogHrm hrm) {
        if (hrm.IsNullOrEmptyId()) {
            return 0;
        }

        if (successfulhandling.isEmpty()) {
            if (hrm.IsMail()) {
                hrm.setMailcycle(hrm.getCycle());
            } else {
                hrm.setTaskcycle(hrm.getCycle());
            }
            successfulhandling.add(hrm);
            return 1;
        } else if (hrm.IsMail()) {
            for (GraylogHrm existHrm : successfulhandling) {
                if (existHrm.EqualTask(hrm)) {
                    successfulhandling.get(successfulhandling.indexOf(existHrm)).CombineSuccessfulActionFromEmail(hrm);
                    return 0;
                }
            }
            hrm.setMailcycle(hrm.getCycle());
            successfulhandling.add(hrm);
            return 1;
        } else if (hrm.IsTask()) {
            for (GraylogHrm existHrm : successfulhandling) {
                if (existHrm.EqualMail(hrm)) {
                    successfulhandling.get(successfulhandling.indexOf(existHrm)).CombineSuccessfulActionFromTask(hrm);
                    return 0;
                }
            }
            hrm.setTaskcycle(hrm.getCycle());
            successfulhandling.add(hrm);
            return 1;
        } else {
            return -1;
        }
    }

}
