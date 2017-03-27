package mysql.connection;

import java.sql.*;

public class SQLDeleteTable {
	public SQLDeleteTable(String n) {
		Connection theConn = null;
		String SQL;
		try {
			SQLConnection MyCon = new SQLConnection();
			theConn = MyCon.getConnection("JAVA");
			Statement stmt = theConn.createStatement();
			SQL = "delete from contact where cname='" + n + "'";
			stmt.executeUpdate(SQL);
			new SQLSelectAllTable();
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			try {
				if (theConn != null)
					theConn.close();
			} catch (Exception e) {
			}
		}
	}
}