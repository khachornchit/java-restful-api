/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datetime.management;

import utilities.CallRestful;
import java.util.Calendar;
import java.util.TimeZone;
import org.joda.time.*;
import org.joda.time.format.*;

/**
 *
 * @author kajornjit.songsaen
 */
public class DateTimeManage {

    private static final String timezonethai = "Asia/Bankok";
    private static final String serverTestUrl = "http://vmdevtest01";
    private static final String serverWeb05Url = "http://vmwinweb05";
    private static final String serverUrl = "http://localhost";

    ;

    private static String getTimezonethai() {
        return timezonethai;
    }

    public void Increase30min() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime time = formatter.parseLocalTime("14:00");
        time = time.plusMinutes(30);
        System.out.println(formatter.print(time));
    }

    public String Increase30min(String inputTime) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        LocalTime time = formatter.parseLocalTime(inputTime);
        time = time.plusMinutes(30);
        return formatter.print(time);
    }

    public static String getToday() {
        return DateUtils.now("dd.MM.yyyy");
    }

    public static String getTime() {
        String requesturl = String.format("%s/automation.test/timezone/timethai.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String getTimezoneSample() {
        Calendar cal = Calendar.getInstance();
        String name = cal.getTimeZone().getDisplayName();
        System.out.println("Current Time Zone:" + name);
        TimeZone tz = TimeZone.getTimeZone("Asia/Bangkok");
        cal.setTimeZone(tz);
        String timezonename = cal.getTimeZone().getDisplayName();
        System.out.println(timezonename);

        return timezonename;
    }

    public static String graylogFixDateBegin(String fixDate) {
//        Input 2016-09-22T00:00:00.000Z        
        String requesturl = String.format("%s/automation.test/timezone/graylogyesterdaytimebegin.php", serverUrl);
        CallRestful rest = new CallRestful();
        String date = rest.GetJson(requesturl);
        String[] parts = date.split("T");
        return date.replaceAll(parts[0], fixDate);
    }

    public static String graylogFixDateEnd(String fixDate) {
//        Input 2016-09-22T00:00:00.000Z        
        String requesturl = String.format("%s/automation.test/timezone/graylogyesterdaytimeend.php", serverUrl);
        CallRestful rest = new CallRestful();
        String date = rest.GetJson(requesturl);
        String[] parts = date.split("T");
        return date.replaceAll(parts[0], fixDate);
    }

    public static String graylogYesterdayTimeBegin() {
        String requesturl = String.format("%s/automation.test/timezone/graylogyesterdaytimebegin.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String graylogYesterdayTimeEnd() {
        String requesturl = String.format("%s/automation.test/timezone/graylogyesterdaytimeend.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String graylogTodayTimeBegin() {
        String requesturl = String.format("%s/automation.test/timezone/graylogtodaytimebegin.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String graylogTodayTimeEnd() {
        String requesturl = String.format("%s/automation.test/timezone/graylogtodaytimeend.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String last3daysbegin() {
        String requesturl = String.format("%s/automation.test/timezone/last3daysbegin.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String last3daysend() {
        String requesturl = String.format("%s/automation.test/timezone/last3daysend.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String last4daysbegin() {
        String requesturl = String.format("%s/automation.test/timezone/last4daysbegin.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String last4daysend() {
        String requesturl = String.format("%s/automation.test/timezone/last4daysend.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String tomorrowbegin() {
        String requesturl = String.format("%s/automation.test/timezone/tomorrowbegin.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }

    public static String tomorrowend() {
        String requesturl = String.format("%s/automation.test/timezone/tomorrowend.php", serverUrl);
        CallRestful instance = new CallRestful();
        return instance.GetJson(requesturl);
    }
}
