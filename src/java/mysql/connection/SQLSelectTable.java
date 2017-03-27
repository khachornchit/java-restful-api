package mysql.connection;

import java.sql.*;

class customer {
	String name;
	String tel;
	String mail;
}

public class SQLSelectTable {
	public SQLSelectTable(customer cust) {
		Connection theConn = null;
		String SQL;
		String n = cust.name;
		try {
			SQLConnection MyCon = new SQLConnection();
			theConn = MyCon.getConnection("JAVA");
			Statement stmt = theConn.createStatement();
			SQL = "select * from contact where cname = '" + n + "'";
			ResultSet rs = stmt.executeQuery(SQL);
			while (rs.next()) {
				String x = rs.getString("ctel") + ";" + rs.getString("cmail");
				String[] y = x.split(";", 2);
				cust.tel = y[0];
				cust.mail = y[1];
			}
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