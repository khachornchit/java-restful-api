package mysql.connection;

import java.sql.*;

public class SQLInsertTable {
	public SQLInsertTable(String n, String t, String m) {
		Connection theConn = null;
		String SQL;
		try {
			SQLConnection MyCon = new SQLConnection();
			theConn = MyCon.getConnection("JAVA");
			Statement stmt = theConn.createStatement();
			SQL = "insert into contact (cname,ctel,cmail) values " + "('" + n + "','" + t + "','" + m + "')";
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