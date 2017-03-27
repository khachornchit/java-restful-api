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
import models.WidgetCount;
import modules.graylog.GraylogHrm;

/**
 *
 * @author kajornjit.songsaen
 */
public class HrmTestAlert extends BaseHrmAlert {

    public HrmTestAlert() {
//        getWaSToday();
//        getInteressentToday();
//        setCountWaSToday();
//
//        getWaSYesterday();
//        getInteressentYesterday();
//        setCountWaSYesterday();

        getErrorHandling();
        setCountErrorHandling3();
        setCountWaS();
        setCountInteressent();
    }

    @Override
    public void getWaSYesterday() {
        try {
            HrmGraylogReader hrmgraylog = new HrmGraylogReader();
            HttpURLConnection request = hrmgraylog.getWaSTestToTextYesterday();
            wasyesterday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && !GraylogHrm.IsHrmLive(thisLine)) {
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
            HttpURLConnection request = hrmgraylog.getWaSTestToTextToday();
            wastoday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && !GraylogHrm.IsHrmLive(thisLine)) {
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
            HttpURLConnection request = hrmgraylog.getInteressentTestToTextYesterday();
            interessentyesterday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && !GraylogHrm.IsHrmLive(thisLine)) {
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
            HttpURLConnection request = hrmgraylog.getInteressentTestToTextToday();
            interessenttoday = new ArrayList<>();
            String thisLine = null;
            try {
                int Count = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader((request.getInputStream())));
                while ((thisLine = br.readLine()) != null) {
                    boolean bHeader = thisLine.contains("timestamp") && thisLine.contains("source") && thisLine.contains("level") && thisLine.contains("message") && thisLine.contains("full_message");
                    if (!bHeader && !GraylogHrm.IsHrmLive(thisLine)) {
                        if (!bHeader && Count < 20) {
                            interessenttoday.add(new GraylogHrm(thisLine, true));
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

        countwastoday.setName("HRM WaS Test");
        countwastoday.setColorAlertBlue();

        countinteressenttoday.setName("HRM Interessent Test");
        countinteressenttoday.setColorAlertBlue();

        counttotaltoday.setName("HRM Test Alert");
        counttotaltoday.setColorAlertBlue();
    }

    @Override
    public void setCountWaSYesterday() {
        counttotalyesterday.setCount(countinteressentyesterday.getCount() + countwasyesterday.getCount());

        countwasyesterday.setName("HRM WaS Test");
        countwasyesterday.setColorAlertBlue();

        countinteressentyesterday.setName("HRM Interessent Test");
        countinteressentyesterday.setColorAlertBlue();

        counttotalyesterday.setName("HRM Test Alert");
        counttotalyesterday.setColorAlertBlue();
    }

    @Override
    public void getErrorHandling() {
        errorhandling3 = new ArrayList<>();
        int CountWaS = getErrorHandling3Was();
        int CountInteressent = getErrorHandling3Interessent();
        int Count = CountWaS + CountInteressent;

        counterrorhandling3 = new WidgetCount();
        countwas = new WidgetCount();
        countinteressent = new WidgetCount();

        counterrorhandling3.setCount(Count);
        countwas.setCount(CountWaS);
        countinteressent.setCount(CountInteressent);
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
                    if (!bHeader && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmTest(thisLine)) {
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
                    if (!bHeader && GraylogHrm.IsErrorHandling(thisLine) && GraylogHrm.IsHrmTest(thisLine)) {
                        if (!bHeader) {
                            GraylogHrm hrm = new GraylogHrm(thisLine, true, true, HrmGraylogReader.getWorkflowInteressent());
                            Count++;
                            errorhandling3.add(hrm);
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
        counterrorhandling3.setName("HRM Test Error");
        counterrorhandling3.setColorAlertBlue();
    }

    private void setCountWaS() {
        countwas.setName("HRM Error WaS");
        countwas.setColorAlertBlue();
    }

    private void setCountInteressent() {
        countinteressent.setName("HRM Error Interessent");
        countinteressent.setColorAlertBlue();
    }
}
