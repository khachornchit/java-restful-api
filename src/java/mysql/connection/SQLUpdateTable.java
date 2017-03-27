package mysql.connection;

import java.sql.*;

public class SQLUpdateTable {
	public SQLUpdateTable(String n, String t, String m) {
		Connection theConn = null;
		String SQL;
		try {
			SQLConnection MyCon = new SQLConnection();
			theConn = MyCon.getConnection("JAVA");
			Statement stmt = theConn.createStatement();
			SQL = "update contact set ctel='" + t + "', " + "cmail='" + m + "'" + " where cname='" + n + "'";
			stmt.executeUpdate(SQL);
			SQL = "select * from contact where cname='" + n + "'";
			ResultSet rs = stmt.executeQuery(SQL);
			System.out.println("=====================================");
			System.out.printf("%-7s%-12s%-20s\n", "NAME", "TELE NO.", "E-MAIL");
			System.out.println("=====================================");
			while (rs.next())
				System.out.printf("%-7s%-12s%-20s\n", rs.getString("cname"), rs.getString("ctel"),
						rs.getString("cmail"));
		} catch (SQLException ex) {
			System.out.println(ex);
		} finally {
			try {
				if (theConn != null)
					theConn.close();
				System.out.println("=====================================");
			} catch (Exception e) {
			}
		}
	}
}