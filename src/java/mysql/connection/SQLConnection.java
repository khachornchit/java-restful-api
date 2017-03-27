package mysql.connection;

import java.sql.*;
import javax.swing.*;

public class SQLConnection {

    private Connection conn;
    private SQLConnection sqlconn;
    private Statement statement;
    private String sqltring;
    private DatabaseMetaData dbmetadata;
    private ResultSet resultset;
    private boolean founddata = false;
    private String msg;
    private String host = "192.168.23.186";
    private String username = "sampleprojects";
    private String password = "sample1234";
    private String database = "dashboardconnection";

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getServer() {
        return host;
    }

    public void setServer(String server) {
        this.host = server;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFound() {
        return founddata;
    }

    public void setFound(boolean found) {
        this.founddata = found;
    }

    public ResultSet getResultset() {
        return resultset;
    }

    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }

    public DatabaseMetaData getDbmetadata() {
        return dbmetadata;
    }

    public void setDbmetadata(DatabaseMetaData dbmetadata) {
        this.dbmetadata = dbmetadata;
    }

    public String getSqltring() {
        return sqltring;
    }

    public void setSqltring(String sqltring) {
        this.sqltring = sqltring;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public SQLConnection getSqlconn() {
        return sqlconn;
    }

    public void setSqlconn(SQLConnection sqlconn) {
        this.sqlconn = sqlconn;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void CreateDB(String fn) {

    }

    public void CreateDB() {
        try {
            sqlconn = new SQLConnection();
            conn = sqlconn.getConnection();
            statement = conn.createStatement();
            sqltring = "Show databases";
            resultset = statement.executeQuery(sqltring);
            while (resultset.next()) {
                if (resultset.getString(1).toUpperCase().equals(this.database)) {
                    founddata = true;
                    break;
                }
            }
            if (!founddata) {
                sqltring = String.format("Create database %s", this.database);
                statement.executeUpdate(sqltring);
                sqltring = "Show databases";
                resultset = statement.executeQuery(sqltring);
                msg = "DataBase >>> ";
                while (resultset.next()) {
                    if (resultset.getString(1).toUpperCase().equals(this.database)) {
                        msg = msg + resultset.getString(1).toUpperCase();
                        break;
                    }
                }
                msg = String.format("%s is Created", msg);
                JOptionPane.showMessageDialog(null, msg);
            } else {
                msg = String.format("DataBase >>> %s is already created", this.database);
                JOptionPane.showMessageDialog(null, msg);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s", host);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successful connected !");
        } catch (Exception e) {
            System.out.println("Cannot connect database !");
            System.out.println(e);
        }
        return conn;
    }

    public Connection getConnection(String database) {
        try {
            this.database=database;
            Class.forName("com.mysql.jdbc.Driver");
            String url = String.format("jdbc:mysql://%s/%s", host, this.database);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Successful connected !");
        } catch (Exception e) {
            System.out.println("Cannot connect database !");
            System.out.println(e);
        }
        return conn;
    }
}
