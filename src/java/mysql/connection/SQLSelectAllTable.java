package mysql.connection;

import java.sql.*;

public class SQLSelectAllTable {
	public SQLSelectAllTable() {
		Connection theConn = null;
		String SQL;
		try {
			SQLConnection MyCon = new SQLConnection();
			theConn = MyCon.getConnection("JAVA");
			Statement stmt = theConn.createStatement();
			SQL = "select * from contact order by cname";
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