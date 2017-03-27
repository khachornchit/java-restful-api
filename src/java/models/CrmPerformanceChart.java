/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.*;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kajornjit.songsaen
 */
public class CrmPerformanceChart {

    private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@vmdevtest01:1521/XE";
    private static final String DB_USER = "clbsdashboard";
    private static final String DB_PASSWORD = "123456";

    private Integer id;
    private String time;
    private Integer pagespeed;
    private Integer restspeed;
    private Integer lsl;
    private Integer usl;
    private Integer reference;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        this.date = Date;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer Reference) {
        this.reference = Reference;
    }

    public Integer getUsl() {
        return usl;
    }

    public void setUsl(Integer Usl) {
        this.usl = Usl;
    }

    public Integer getLsl() {
        return lsl;
    }

    public void setLsl(Integer Lsl) {
        this.lsl = Lsl;
    }

    public Integer getRestSpeed() {
        return restspeed;
    }

    public void setRestSpeed(Integer RestSpeed) {
        this.restspeed = RestSpeed;
    }

    public Integer getPageSpeed() {
        return pagespeed;
    }

    public void setPageSpeed(Integer PageSpeed) {
        this.pagespeed = PageSpeed;
    }

    /**
     * Get the value of Time
     *
     * @return the value of Time
     */
    public String getTime() {
        return time;
    }

    /**
     * Set the value of Time
     *
     * @param Time new value of Time
     */
    public void setTime(String Time) {
        this.time = Time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = Id;
    }

    public boolean insertDataQuery(
            int PerformanceId,
            String Datex,
            String Timex,
            int PageSpeed,
            int RestSpeed,
            int Reference,
            int Lsl,
            int Usl
    ) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO CLBSDASHBOARD.CRMPERFERMANCECHART (PERFORMANCEID,DATEX,TIMEX,PAGESPEED,RESTSPEED,REFERENCE,LSL,USL) VALUES (?,?,?,?,?,?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, PerformanceId);
            preparedStatement.setString(2, Datex);
            preparedStatement.setString(3, Timex);
            preparedStatement.setInt(4, PageSpeed);
            preparedStatement.setInt(5, RestSpeed);
            preparedStatement.setInt(6, Reference);
            preparedStatement.setInt(7, Lsl);
            preparedStatement.setInt(8, Usl);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public boolean insertDataQueryService(CrmJsonGraph data) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String insertTableSQL = "INSERT INTO CLBSDASHBOARD.CRMPERFERMANCECHART (PERFORMANCEID,DATEX,TIMEX,PAGESPEED,RESTSPEED,REFERENCE,LSL,USL) VALUES (?,?,?,?,?,?,?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setInt(1, data.getId());
            preparedStatement.setString(2, data.getDate());
            preparedStatement.setString(3, data.getTime());
            preparedStatement.setInt(4, data.getPagespeed());
            preparedStatement.setInt(5, data.getRestspeed());
            preparedStatement.setInt(6, data.getReference());
            preparedStatement.setInt(7, data.getLsl());
            preparedStatement.setInt(8, data.getUsl());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public boolean insertData(
            int PerformanceId,
            String Datex,
            String Timex,
            int PageSpeed,
            int RestSpeed,
            int Reference,
            int Lsl,
            int Usl
    ) throws SQLException {
        Connection dbConnection = null;
        CallableStatement preparedStatement = null;
        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareCall("{call insertcrmperformancechart(?,?,?,?,?,?,?,?)}");

            preparedStatement.setInt(1, PerformanceId);
            preparedStatement.setString(2, Datex);
            preparedStatement.setString(3, Timex);
            preparedStatement.setInt(4, PageSpeed);
            preparedStatement.setInt(5, RestSpeed);
            preparedStatement.setInt(6, Reference);
            preparedStatement.setInt(7, Lsl);
            preparedStatement.setInt(8, Usl);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public boolean selectData() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT PERFORMANCEID,DATEX,TIMEX,PAGESPEED,RESTSPEED,LSL,USL,REFERENCE FROM CLBSDASHBOARD.CRMPERFERMANCECHART";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String PerformanceId = rs.getString("PERFORMANCEID");
                String Datex = rs.getString("DATEX");
                String Timex = rs.getString("TIMEX");
                String PageSpeed = rs.getString("PAGESPEED");
                String RestSpeed = rs.getString("RESTSPEED");
                String LSL = rs.getString("LSL");
                String USL = rs.getString("USL");
                String Reference = rs.getString("REFERENCE");

                System.out.println("PerformanceId : " + PerformanceId);
                System.out.println("Date : " + Datex);
                System.out.println("Time : " + Timex);
                System.out.println("PageSpeed : " + PageSpeed);
                System.out.println("RestSpeed : " + RestSpeed);
                System.out.println("LSL : " + LSL);
                System.out.println("USL : " + USL);
                System.out.println("Reference : " + Reference);
            }
            return true;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
            return true;
        }

    }

    public List<CrmPerformanceChartDisplay> selectChartData() throws SQLException {
        List<CrmPerformanceChartDisplay> lstChartData = new ArrayList<CrmPerformanceChartDisplay>();
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT A.PERFORMANCEID,A.DATEX,A.TIMEX,A.PAGESPEED,A.RESTSPEED,A.LSL,A.USL,A.REFERENCE FROM (SELECT B.PERFORMANCEID,B.DATEX,B.TIMEX,B.PAGESPEED,B.RESTSPEED,B.LSL,B.USL,B.REFERENCE FROM CLBSDASHBOARD.CRMPERFERMANCECHART B ORDER BY PERFORMANCEID DESC) A WHERE ROWNUM<=20 ORDER BY PERFORMANCEID";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CrmPerformanceChartDisplay obj = new CrmPerformanceChartDisplay();

                obj.setPerformanceId(Integer.parseInt(rs.getString("PERFORMANCEID")));
                obj.setDatex(rs.getString("DATEX"));
                obj.setTimex(rs.getString("TIMEX"));
                obj.setPageSpeed(Integer.parseInt(rs.getString("PAGESPEED")));
                obj.setRestSpeed(Integer.parseInt(rs.getString("RESTSPEED")));
                obj.setLSL(Integer.parseInt(rs.getString("LSL")));
                obj.setUSL(Integer.parseInt(rs.getString("USL")));
                obj.setReference(Integer.parseInt(rs.getString("REFERENCE")));

                lstChartData.add(obj);
            }
            return lstChartData;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public String getLastTimeQuery() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT TIMEX FROM CRMPERFERMANCECHART WHERE PERFORMANCEID = (SELECT MAX(PERFORMANCEID) FROM CRMPERFERMANCECHART)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            String Timex = null;
            while (rs.next()) {
                Timex = rs.getString("TIMEX");
                System.out.println("Last Time : " + Timex);
            }
            return Timex;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    public int getNewId() throws SQLException {
        return getLastId() + 1;
    }

    public int getLastIdQuery() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT MAX(PERFORMANCEID) as PerformanceId FROM CRMPERFERMANCECHART";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            String sPerformanceId = null;

            while (rs.next()) {
                sPerformanceId = rs.getString("PerformanceId");
                System.out.println("Performance Id : " + sPerformanceId);
            }
            return Integer.parseInt(sPerformanceId);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return -1;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }

    private Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {
            System.out.println("Connect failed !");
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    private java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    public int getLastId() throws SQLException {
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call getLastIdTime(?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.executeUpdate();
            return proc.getInt(1);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return -1;
        }
    }

    public CrmPerformanceChart getLastRecord() throws SQLException {
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call getLastId_CrmPerfermanceChart(?,?,?,?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.registerOutParameter(3, Types.INTEGER);
            proc.registerOutParameter(4, Types.INTEGER);
            proc.registerOutParameter(5, Types.INTEGER);
            proc.registerOutParameter(6, Types.INTEGER);
            proc.registerOutParameter(7, Types.INTEGER);
            proc.registerOutParameter(8, Types.VARCHAR);
            proc.executeUpdate();

            CrmPerformanceChart pChart = new CrmPerformanceChart();
            pChart.setId(proc.getInt(1));
            pChart.setTime(proc.getString(2));
            pChart.setPageSpeed(proc.getInt(3));
            pChart.setRestSpeed(proc.getInt(4));
            pChart.setLsl(proc.getInt(5));
            pChart.setUsl(proc.getInt(6));
            pChart.setReference(proc.getInt(7));
            pChart.setDate(proc.getString(8));
            return pChart;
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getLastTime() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call getLastIdTime(?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.executeUpdate();
            return proc.getString(2);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return null;
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
