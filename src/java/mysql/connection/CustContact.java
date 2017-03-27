package mysql.connection;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustContact extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel datelbl, namelbl, tellbl, maillbl, piclbl, cnamelbl, cadslbl, blanklbl;
	JTextField nametxt, teltxt, mailtxt;
	Icon ani;
	JButton showbtn, editbtn, delbtn, resetbtn;
	Font fn = new Font("Estrangelo Edessa", Font.PLAIN, 18);
	AddPanel y;
	boolean add = true;

	public CustContact(String title) {
		setTitle(title);
		setSize(550, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);
	}

	private void buildPanel() {
		ani = new ImageIcon("SHIPPING.gif");
		piclbl = new JLabel(ani, SwingConstants.LEFT);
		cnamelbl = new JLabel("TELEZZA T.L. Group");
		cadslbl = new JLabel("Bangkok. 088-818-8216");
		Date DateName = new Date();
		SimpleDateFormat df = new SimpleDateFormat("d/M/yyyy");
		String dd = df.format(DateName.getTime());
		datelbl = new JLabel("Date : " + dd + "   ");
		namelbl = new JLabel("Name");
		tellbl = new JLabel("Tel.");
		maillbl = new JLabel("E-mail");
		blanklbl = new JLabel();
		nametxt = new JTextField(25);
		teltxt = new JTextField(25);
		mailtxt = new JTextField(25);
		ani = new ImageIcon("report.gif");
		showbtn = new JButton("Show", ani);
		ani = new ImageIcon("edit.gif");
		editbtn = new JButton("Edit", ani);
		ani = new ImageIcon("delete.gif");
		delbtn = new JButton("Delete", ani);
		ani = new ImageIcon("reset.gif");
		resetbtn = new JButton("Reset", ani);
		nametxt.addActionListener(new CustContact.TextListener());
		mailtxt.addActionListener(new CustContact.TextListener());
		showbtn.addActionListener(new CustContact.ButtonListener());
		editbtn.addActionListener(new CustContact.ButtonListener());
		delbtn.addActionListener(new CustContact.ButtonListener());
		resetbtn.addActionListener(new CustContact.ButtonListener());
		cnamelbl.setFont(fn);
		cadslbl.setFont(fn);
		datelbl.setFont(fn);
		namelbl.setFont(fn);
		tellbl.setFont(fn);
		maillbl.setFont(fn);
		nametxt.setFont(fn);
		teltxt.setFont(fn);
		mailtxt.setFont(fn);
		showbtn.setFont(fn);
		editbtn.setFont(fn);
		delbtn.setFont(fn);
		resetbtn.setFont(fn);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		y = new AddPanel();
		y.addItem(panel, piclbl, 0, 0, 1, 3, GridBagConstraints.WEST);
		y.addItem(panel, cnamelbl, 1, 0, 3, 1, GridBagConstraints.WEST);
		y.addItem(panel, cadslbl, 1, 1, 3, 1, GridBagConstraints.WEST);

		y.addItem(panel, datelbl, 1, 2, 3, 1, GridBagConstraints.EAST);

		y.addItem(panel, namelbl, 0, 3, 1, 1, GridBagConstraints.WEST);
		y.addItem(panel, nametxt, 1, 3, 3, 1, GridBagConstraints.WEST);

		y.addItem(panel, tellbl, 0, 4, 1, 1, GridBagConstraints.WEST);
		y.addItem(panel, teltxt, 1, 4, 3, 1, GridBagConstraints.WEST);

		y.addItem(panel, maillbl, 0, 5, 1, 1, GridBagConstraints.WEST);
		y.addItem(panel, mailtxt, 1, 5, 3, 1, GridBagConstraints.WEST);

		y.addItem(panel, blanklbl, 0, 6, 4, 1, GridBagConstraints.WEST);

		y.addItem(panel, showbtn, 0, 7, 1, 1, GridBagConstraints.WEST);
		y.addItem(panel, editbtn, 1, 7, 1, 1, GridBagConstraints.WEST);
		y.addItem(panel, delbtn, 2, 7, 1, 1, GridBagConstraints.WEST);
		y.addItem(panel, resetbtn, 3, 7, 1, 1, GridBagConstraints.WEST);
	}

	private class TextListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mailtxt) {
				String name, tel, mail;
				name = nametxt.getText();
				tel = teltxt.getText();
				mail = mailtxt.getText();
				if (add) {
					new SQLInsertTable(name, tel, mail);
				} else {
					new SQLUpdateTable(name, tel, mail);
				}
				nametxt.setText("");
				teltxt.setText("");
				mailtxt.setText("");
				nametxt.setEnabled(true);
				nametxt.requestFocus();
				add = true;
			}
			if (e.getSource() == nametxt) {
				customer cust = new customer();
				cust.name = nametxt.getText();
				new SQLSelectTable(cust);
				if ((cust.tel) == null) {
					teltxt.setEnabled(true);
					mailtxt.setEnabled(true);
					teltxt.requestFocus();
				} else {
					nametxt.setEnabled(false);
					teltxt.setEnabled(false);
					mailtxt.setEnabled(false);
					teltxt.setText(cust.tel);
					mailtxt.setText(cust.mail);
				}
			}
		}
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == showbtn) {
				new SQLSelectAllTable();
			}
			if (e.getSource() == editbtn) {
				add = false;
				teltxt.setEnabled(true);
				mailtxt.setEnabled(true);
				teltxt.selectAll();
				teltxt.requestFocus();
			}
			if (e.getSource() == delbtn) {
				String name = nametxt.getText();
				int data = JOptionPane.showConfirmDialog(null, "ต้องการลบข้อมูล ", "ยืนยันการลบข้อมูล ",
						JOptionPane.YES_NO_OPTION);
				if (data == JOptionPane.YES_OPTION) {
					new SQLDeleteTable(name);
					JOptionPane.showMessageDialog(null, "ลบข้อมูลเรียบร้อยแล้ว");
				}
				nametxt.setText("");
				teltxt.setText("");
				mailtxt.setText("");
				nametxt.setEnabled(true);
				nametxt.requestFocus();
				add = true;

			}
			if (e.getSource() == resetbtn) {
				add = true;
				nametxt.setEnabled(true);
				teltxt.setEnabled(true);
				mailtxt.setEnabled(true);
				nametxt.setText("");
				teltxt.setText("");
				mailtxt.setText("");
				nametxt.requestFocus();
			}
		}
	}

	public static void main(String[] args) {
		SQLConnection MyCon = new SQLConnection();
		MyCon.CreateDB("JAVA");
		new SQLCreateTable("contact");
		new CustContact("Customer Data");
	}
}