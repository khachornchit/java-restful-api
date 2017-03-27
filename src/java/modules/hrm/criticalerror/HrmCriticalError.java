/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.hrm.criticalerror;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import modules.graylog.GraylogHrm;
import modules.hrm.alert.HrmGraylogReader;
import modules.hrm.base.BaseHrmAlert;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmCriticalError extends BaseHrmAlert {

    public HrmCriticalError() {
        // 1st Process Successful
        getSuccessfulHandling();
        setCountSuccessfulHandling();
        setCountWaSSuccessful();
        setCountInteressentSuccessful();

        // 2nd Process Error
        getErrorHandling();
        setCountErrorHandling3();
        setCountWaS();
        setCountInteressent();
    }

    @Override
    public void getWaSYesterday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getWaSLiveToTextFixDate("2016-09-22");
            wasyesterday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");

                    if (!bHeader && GraylogHrm.IsHrmLiveId(thisLine)) {
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
                    if (!bHeader && GraylogHrm.IsHrmLiveId(thisLine)) {
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
    public void setCountWaSToday() {
        counttotaltoday.setCount(countinteressenttoday.getCount() + countwastoday.getCount());

        countwastoday.setName("HRM WaS Live Critical");
        countwastoday.setColorCritical();

        countinteressenttoday.setName("HRM Interessent Live Critical");
        countinteressenttoday.setColorCritical();

        counttotaltoday.setName("HRM Test Live Critical");
        counttotaltoday.setColorCritical();
    }

    @Override
    public void setCountWaSYesterday() {
        counttotalyesterday.setCount(countinteressentyesterday.getCount() + countwasyesterday.getCount());

        countwasyesterday.setName("HRM WaS Live Critical");
        countwasyesterday.setColorCritical();

        countinteressentyesterday.setName("HRM Interessent Live Critical");
        countinteressentyesterday.setColorCritical();

        counttotalyesterday.setName("HRM Live Critical");
        counttotalyesterday.setColorCritical();
    }

    @Override
    public void getInteressentYesterday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getInteressentLiveFixDate("2016-09-22");
            interessentyesterday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");

                    if (!bHeader && GraylogHrm.IsHrmLiveId(thisLine)) {
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
                    if (!bHeader && GraylogHrm.IsHrmLiveId(thisLine)) {
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
    public void getErrorHandling() {
        int CountWaS = getErrorHandlingWas();
        int CountInteressent = getErrorHandlingInteressent();
        int Count = CountWaS + CountInteressent;

        counterrorhandling3.setCount(Count);
        countwas.setCount(CountWaS);
        countinteressent.setCount(CountInteressent);

        Collections.sort(errorhandling3, GraylogHrm.CompareTimeStamp);
        Collections.sort(errorresolved, GraylogHrm.CompareTimeStamp);
    }

    private int getErrorHandlingWas() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getErrorHandling3WaS();

            try {
                String thisLine = null;
                int Count = 0;
                BufferedReader brWaS = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = brWaS.readLine()) != null) {
                    if (!IsGraylogHeader(thisLine) && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                        GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowWaS());
                        if (hrm.IsCriticalError() && !hrm.IsMailHistory()) {
                            if (IsResolved(hrm)) {
                                hrm.setResolved(true);
                                if (hrm.IsMail()) {
                                    hrm.setMailcycle(hrm.getCycle());
                                } else if (hrm.IsTask()) {
                                    hrm.setTaskcycle(hrm.getCycle());
                                }
                                if (hrm.IsNullOrEmptyId()) {
                                    errorresolved.add(hrm);
                                }

                            } else {
                                hrm.setResolved(false);
                                Count = Count + addErrorHandlingWaS(hrm);
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

    private int getErrorHandlingInteressent() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getErrorHandling3Interessent();

            try {
                String thisLine = null;
                int Count = 0;
                BufferedReader buffer = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = buffer.readLine()) != null) {
                    if (!IsGraylogHeader(thisLine) && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                        GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowInteressent());
                        if (hrm.IsCriticalError() && !hrm.IsMailHistory()) {
                            if (IsResolved(hrm)) {
                                hrm.setResolved(true);
                                if (hrm.IsMail()) {
                                    hrm.setMailcycle(hrm.getCycle());
                                } else if (hrm.IsTask()) {
                                    hrm.setTaskcycle(hrm.getCycle());
                                }
                                if (!hrm.IsNullOrEmptyId()) {
                                    errorresolved.add(hrm);
                                }
                            } else {
                                hrm.setResolved(false);
                                Count = Count + addErrorHandlingInteressent(hrm);
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

    @Override
    public void getSuccessfulHandling() {
        getAllSuccessful();
        Collections.sort(successfulhandling, GraylogHrm.CompareTimeStamp);
    }

    private int getAllSuccessful() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getAllSuccess();

            try {
                String thisLine = null;
                int CountTotal = 0;
                int CountWaS = 0;
                int CountInteressent = 0;

                BufferedReader buffer = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = buffer.readLine()) != null) {
                    if (IsWorkflowWas(thisLine)) {
                        if (GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowWaS());
                            if (hrm.getSuccesful() && !hrm.IsNullOrEmptyId()) {
                                CountWaS = CountWaS + addSuccessfulWaS(hrm);
                            }
                        }
                    }

                    if (IsWorkflowInteressent(thisLine)) {
                        if (GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowInteressent());
                            if (hrm.getSuccesful() && !hrm.IsNullOrEmptyId()) {
                                CountInteressent = CountInteressent + addSuccessfulInteressent(hrm);
                            }
                        }
                    }
                }

                CountTotal = CountWaS + CountInteressent;
                countsuccessfulhandling.setCount(CountTotal);
                countwassuccessful.setCount(CountWaS);
                countinteressentsuccessful.setCount(CountInteressent);
                return CountTotal;
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return -1;
    }

    private boolean IsWorkflowWas(String line) {
        return line.contains(workflowWaS);
    }

    private boolean IsWorkflowInteressent(String line) {
        return line.contains(workflowInteressent);
    }

    private int getSuccessfulHandlingWas() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getAllWaS();

            try {
                String thisLine = null;
                int Count = 0;
                BufferedReader brWaS = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = brWaS.readLine()) != null) {

                    System.out.println(thisLine);

                    if (!IsGraylogHeader(thisLine) && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                        GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowWaS());
                        if (hrm.getSuccesful() && !hrm.IsNullOrEmptyId()) {
                            Count = Count + addSuccessfulWaS(hrm);
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

    private int getSuccessfulHandlingInteressent() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getAllInteressent();

            try {
                String thisLine = null;
                int Count = 0;
                BufferedReader brWaS = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = brWaS.readLine()) != null) {
                    if (!IsGraylogHeader(thisLine) && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmLiveId(thisLine)) {
                        GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowInteressent());
                        if (hrm.getSuccesful() && !hrm.IsNullOrEmptyId()) {
                            Count = Count + addSuccessfulInteressent(hrm);
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
        counterrorhandling3.setName("HRM Critical Error");
        counterrorhandling3.setColorAlertRed();
    }

    private void setCountWaS() {
        countwas.setName("HRM Error WaS");
        countwas.setColorAlertRed();
    }

    private void setCountInteressent() {
        countinteressent.setName("HRM Error Interessent");
        countinteressent.setColorAlertRed();
    }

    private void setCountSuccessfulHandling() {
        countsuccessfulhandling.setName("HRM Successful Workflow");
        countsuccessfulhandling.setColorAlertGreen();
    }

    private void setCountWaSSuccessful() {
        countwassuccessful.setName("HRM Successful WaS");
        countwassuccessful.setColorAlertGreen();
    }

    private void setCountInteressentSuccessful() {
        countinteressentsuccessful.setName("HRM Successful Interessent");
        countinteressentsuccessful.setColorAlertGreen();
    }

    @Override
    public int addErrorHandlingWaS(GraylogHrm hrm) {
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

    @Override
    public int addSuccessfulInteressent(GraylogHrm hrm) {
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

    @Override
    public boolean IsResolved(GraylogHrm hrmError) {
        if (!successfulhandling.isEmpty()) {
            return successfulhandling.stream().map((existSuccessful) -> existSuccessful.IsResolved(hrmError)).anyMatch((resolved) -> (resolved));
        } else {
            return false;
        }
    }
}
