package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SpeedModel implements SpeedModelInterface {

    private Integer id;
    private String name;
    private String url;
    private int speed;
    private String ondate;
    private String ontime;
    private String averagetime;
    private static final String DBDRIVER = "oracle.jdbc.OracleDriver";
    private static final String DBCONNECTION = "jdbc:oracle:thin:@vmdevtest01:1521/XE";
    private static final String DBUSER = "clbsdashboard";
    private static final String DBPASSWORD = "123456";
    private String DBDatabase;
    private String DBTable;

    protected String getDBTable() {
        return DBTable;
    }

    protected void setDBTable(String DBTable) {
        this.DBTable = DBTable;
    }

    protected String getDBDatabase() {
        return DBDatabase;
    }

    protected void setDBDatabase(String DBDatabase) {
        this.DBDatabase = DBDatabase;
    }

    public String getAveragetime() {
        return averagetime;
    }

    public void setAveragetime(String averagetime) {
        this.averagetime = averagetime;
    }

    public String getTime() {
        return ontime;
    }

    public void setOntime(String ontime) {
        this.ontime = ontime;
    }

    public String getDate() {
        return ondate;
    }

    public void setOndate(String ondate) {
        this.ondate = ondate;
    }

    protected Integer getSpeed() {
        return speed;
    }

    protected void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String modulename) {
        this.name = modulename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            Class.forName(DBDRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DBCONNECTION, DBUSER, DBPASSWORD);
            return dbConnection;

        } catch (SQLException e) {
            System.out.println("Connect failed !");
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    @Override
    public boolean InsertAverageSpeed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean DeleteAverageSpeed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected boolean insertPageSpeed() {
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call insertPageSpeedCrm(?,?,?,?,?,?,?)}");
            proc.setInt(1, getId());
            proc.setString(2, getName());
            proc.setString(3, getUrl());
            proc.setInt(4, getSpeed());
            proc.setString(5, getDate());
            proc.setString(6, getTime());
            proc.setString(7, getAveragetime());
            proc.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected boolean insertPageSpeedAverage() {
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call insertPageSpeedAverageCrm(?,?,?,?,?)}");
            proc.setInt(1, getId());
            proc.setString(2, getUrl());
            proc.setInt(3, getSpeed());
            proc.setString(4, getDate());
            proc.setString(5, getTime());
            proc.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected boolean insertRestSpeed() {
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call insertRestSpeedCrm(?,?,?,?,?,?,?)}");
            proc.setInt(1, getId());
            proc.setString(2, getName());
            proc.setString(3, getUrl());
            proc.setInt(4, getSpeed());
            proc.setString(5, getDate());
            proc.setString(6, getTime());
            proc.setString(7, getAveragetime());
            proc.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    protected boolean insertRestSpeedAverage() {
        Connection dbConnection = null;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call insertRestSpeedAverageCrm(?,?,?,?,?)}");
            proc.setInt(1, getId());
            proc.setString(2, getUrl());
            proc.setInt(3, getSpeed());
            proc.setString(4, getDate());
            proc.setString(5, getTime());
            proc.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean DeleteSpeed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected PageSpeedCrm getLastPageSpeed() {
        Connection dbConnection;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call GETLASTID_PAGESPEEDCRM(?,?,?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.registerOutParameter(3, Types.VARCHAR);
            proc.registerOutParameter(4, Types.INTEGER);
            proc.registerOutParameter(5, Types.VARCHAR);
            proc.registerOutParameter(6, Types.VARCHAR);
            proc.registerOutParameter(7, Types.VARCHAR);
            proc.executeUpdate();

            PageSpeedCrm pageSpeed = new PageSpeedCrm();
            pageSpeed.setId(proc.getInt(1));
            pageSpeed.setPageName(proc.getString(2));
            pageSpeed.setUrl(proc.getString(3));
            pageSpeed.setPageSpeed(proc.getInt(4));
            pageSpeed.setOndate(proc.getString(5));
            pageSpeed.setOntime(proc.getString(6));
            pageSpeed.setAveragetime(proc.getString(7));

            return pageSpeed;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    protected RestSpeedCrm getLastRestSpeed() {

        Connection dbConnection;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call GETLASTID_RESTSPEEDCRM(?,?,?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.registerOutParameter(3, Types.VARCHAR);
            proc.registerOutParameter(4, Types.INTEGER);
            proc.registerOutParameter(5, Types.VARCHAR);
            proc.registerOutParameter(6, Types.VARCHAR);
            proc.registerOutParameter(7, Types.VARCHAR);
            proc.executeUpdate();

            RestSpeedCrm restSpeed = new RestSpeedCrm();
            restSpeed.setId(proc.getInt(1));
            restSpeed.setModuleName(proc.getString(2));
            restSpeed.setUrl(proc.getString(3));
            restSpeed.setRestSpeed(proc.getInt(4));
            restSpeed.setOndate(proc.getString(5));
            restSpeed.setOntime(proc.getString(6));
            restSpeed.setAveragetime(proc.getString(7));

            return restSpeed;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    protected PageSpeedAverageCrm getLastPageSpeedAverage() {
        Connection dbConnection;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call GETLASTID_PAGESPEEDAVERAGECRM(?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.registerOutParameter(3, Types.INTEGER);
            proc.registerOutParameter(4, Types.VARCHAR);
            proc.registerOutParameter(5, Types.VARCHAR);
            proc.executeUpdate();

            PageSpeedAverageCrm pageSpeedAverage = new PageSpeedAverageCrm();
            pageSpeedAverage.setId(proc.getInt(1));
            pageSpeedAverage.setUrl(proc.getString(2));
            pageSpeedAverage.setPageSpeed(proc.getInt(3));
            pageSpeedAverage.setOndate(proc.getString(4));
            pageSpeedAverage.setOntime(proc.getString(5));

            return pageSpeedAverage;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    protected RestSpeedAverageCrm getLastRestSpeedAverage() {

        Connection dbConnection;
        try {
            dbConnection = getDBConnection();
            CallableStatement proc = dbConnection.prepareCall("{call GETLASTID_RESTSPEEDAVERAGECRM(?,?,?,?,?)}");
            proc.registerOutParameter(1, Types.INTEGER);
            proc.registerOutParameter(2, Types.VARCHAR);
            proc.registerOutParameter(3, Types.INTEGER);
            proc.registerOutParameter(4, Types.VARCHAR);
            proc.registerOutParameter(5, Types.VARCHAR);
            proc.executeUpdate();

            RestSpeedAverageCrm restSpeedAverage = new RestSpeedAverageCrm();
            restSpeedAverage.setId(proc.getInt(1));
            restSpeedAverage.setUrl(proc.getString(2));
            restSpeedAverage.setRestSpeed(proc.getInt(3));
            restSpeedAverage.setOndate(proc.getString(4));
            restSpeedAverage.setOntime(proc.getString(5));

            return restSpeedAverage;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public PageSpeedCrms getDisplayPageSpeed() throws SQLException {
        PageSpeedCrms pageSpeeds = new PageSpeedCrms();        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String query = "select a.id,a.pagename,a.url,a.pagespeed,a.ondate,a.ontime,a.averagetime from (select b.id,b.pagename,b.url,b.pagespeed,b.ondate,b.ontime,b.averagetime from pagespeedcrm b order by b.id desc) a where rownum<=13 order by id";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PageSpeedCrm pageSpeed = new PageSpeedCrm();
                pageSpeed.setId(Integer.parseInt(rs.getString("ID")));
                pageSpeed.setPageName(rs.getString("PAGENAME"));
                pageSpeed.setUrl(rs.getString("URL"));
                pageSpeed.setPageSpeed(Integer.parseInt(rs.getString("PAGESPEED")));
                pageSpeed.setOndate(rs.getString("ONDATE"));
                pageSpeed.setOntime(rs.getString("ONTIME"));
                pageSpeed.setAveragetime(rs.getString("AVERAGETIME"));
                
                pageSpeeds.addPageSpeedCrm(pageSpeed);
            }
            return pageSpeeds;
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
    
    public RestSpeedCrms getDisplayRestSpeed() throws SQLException {
        RestSpeedCrms restspeeds = new RestSpeedCrms();        
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        String query = "select a.id,a.modulename,a.url,a.restspeed,a.ondate,a.ontime,a.averagetime from (select b.id,b.modulename,b.url,b.restspeed,b.ondate,b.ontime,b.averagetime from RestSpeedCrm b order by b.id desc) a where rownum<=17 order by id";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                RestSpeedCrm restspeed = new RestSpeedCrm();
                restspeed.setId(Integer.parseInt(rs.getString("ID")));
                restspeed.setModuleName(rs.getString("MODULENAME"));
                restspeed.setUrl(rs.getString("URL"));
                restspeed.setRestSpeed(Integer.parseInt(rs.getString("RESTSPEED")));
                restspeed.setOndate(rs.getString("ONDATE"));
                restspeed.setOntime(rs.getString("ONTIME"));
                restspeed.setAveragetime(rs.getString("AVERAGETIME"));
                
                restspeeds.addRestSpeedCrm(restspeed);
            }
            return restspeeds;
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
