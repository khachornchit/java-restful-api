/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.connection;

import java.sql.*;

/**
 *
 * @author acer
 */
public class BaseMySQLDatabase {

    protected String databaseurl;
    protected String server;
    protected String database;
    protected String user;
    protected String password;
    protected Connection conn;

    protected void Open() throws ClassNotFoundException, SQLException {
        conn = (Connection) DriverManager.getConnection(databaseurl, user, password);
    }

    protected void Close() throws SQLException {
        conn.close();
    }

    protected ResultSet Read(String query) throws ClassNotFoundException {
        try {
            Open();
//            System.out.println(query);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            return result;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    protected int Update(String query) throws ClassNotFoundException {
        try {
            Open();
//            System.out.println(query);
            PreparedStatement statement = conn.prepareStatement(query);
            int result = statement.executeUpdate();
//            CloseConnection();
            return result;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return -1;
        }
    }

    protected boolean Delete(String query) throws ClassNotFoundException {
        try {
            Open();
            PreparedStatement statement = conn.prepareStatement(query);
            boolean result = statement.execute();
//            CloseConnection();
            return result;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    private String ReadSampleData(String tableName) throws ClassNotFoundException, SQLException {
        String query = String.format("select * from %s", tableName);
        String outString = null;

        try {
            Open();
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            int i = 0;
            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2));
                i++;
                if (i > 10) {
                    break;
                }
            }
            Close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return outString;
    }
}
