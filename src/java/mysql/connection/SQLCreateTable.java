package mysql.connection;

import java.sql.*;
import javax.swing.*;

public class SQLCreateTable {
	public SQLCreateTable(String tname) {
		Connection c;
		SQLConnection MyCon;
		Statement stmt;
		String SQL = "";
		ResultSet rs;
		DatabaseMetaData md;
		boolean found = false;
		String msg = "";
		try {
			MyCon = new SQLConnection();
			c = MyCon.getConnection("JAVA");
			md = c.getMetaData();
			rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				if (rs.getString(3).equals(tname)) {
					found = true;
					break;
				}
			}
			stmt = c.createStatement();
			if (!found) {
				if (tname.equals("contact"))
					SQL = "create table contact (cname varchar(20),ctel varchar(10),cmail varchar(30), primary key (cname))";
				stmt.executeUpdate(SQL);
				md = c.getMetaData();
				rs = md.getTables(null, null, "%", null);
				msg = "Table >>> ";
				while (rs.next()) {
					if (rs.getString(3).equals(tname)) {
						msg = msg + rs.getString(3).toUpperCase();
						break;
					}
				}
				msg = msg + " is Created";
				JOptionPane.showMessageDialog(null, msg);
			} else {
				msg = msg + "Table >>> " + tname.toUpperCase() + " is already Created";
				JOptionPane.showMessageDialog(null, msg);
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}