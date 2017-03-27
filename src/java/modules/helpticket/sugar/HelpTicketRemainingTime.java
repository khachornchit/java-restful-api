/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules.helpticket.sugar;

/**
 *
 * @author kajornjit.songsaen
 */
public class HelpTicketRemainingTime {

    private String remainingTimeStringMin = null;
    private String remainingTimeStringMax = null;
    private int remainingTimeNumberMin = -1;
    private int remainingTimeNumberMax = -1;

    public HelpTicketRemainingTime() {
    }

    private int calculateRemainingTimeNumber(String newRemainingTime) {
        try {
            String[] output1 = newRemainingTime.split("h");
            int hr = Integer.parseInt(output1[0]);

            String[] output2 = newRemainingTime.split(" ");
            String[] output3 = output2[1].split("m");
            int min = Integer.parseInt(output3[0]);
            return (hr * 60) + min;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getColor(String remainingTime) {
        try {
            String[] output1 = remainingTime.split("h");
            int hr = Integer.parseInt(output1[0]);
            if (hr < 0) {
                return "yellow";
            } else {
                return "green";
            }
        } catch (Exception e) {
            return "yellow";
        }
    }

    public String getColor(int count) {
        if (count > 0) {
            return "red";
        } else {
            return "green";
        }
    }

    public void getRemainingTime(String remainingTimeStringNew) {
        if (this.remainingTimeNumberMin == -1) {
            this.remainingTimeStringMin = remainingTimeStringNew;
            this.remainingTimeStringMax = remainingTimeStringNew;
            this.remainingTimeNumberMin = calculateRemainingTimeNumber(remainingTimeStringNew);
            this.remainingTimeNumberMax = this.remainingTimeNumberMin;
        } else {
            int remainingTimeNumberNew = calculateRemainingTimeNumber(remainingTimeStringNew);
            if (this.remainingTimeNumberMin > remainingTimeNumberNew) {
                this.remainingTimeNumberMin = remainingTimeNumberNew;
                this.remainingTimeStringMin = remainingTimeStringNew;
            }

            if (this.remainingTimeNumberMax < remainingTimeNumberNew) {
                this.remainingTimeNumberMax = remainingTimeNumberNew;
                this.remainingTimeStringMax = remainingTimeStringNew;
            }
        }
    }

    public String toString() {
        return String.format("min = %s, max = %s", remainingTimeStringMin, remainingTimeStringMax);
    }

    public String getRemainingTimeStringMin() {
        return remainingTimeStringMin;
    }

    public String getRemainingTimeStringMax() {
        return remainingTimeStringMax;
    }
}
