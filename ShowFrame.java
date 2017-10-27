
package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import clients.ContactClient;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowFrame extends JFrame {

	private JPanel contentPane;
	JTable table_2;
	DefaultTableModel model;
	private ContactClient client;

	public ShowFrame(String ip) throws SQLException {
		client = new ContactClient(ip);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);




		//table
         
		String[] columns = new String[] {
				"Id", "Name","family", "tel","mobile" ,"email" };


		try{
			Object[][] contact =client.showContacts();
			System.out.println(contact[0][0]);

			model = new DefaultTableModel(contact,columns);
			table_2 = new JTable(model);
			table_2.setBounds(10, 10, 175, 200);

			//size of contact table
			table_2.getColumnModel().getColumn(0).setPreferredWidth(400);
			table_2.getColumnModel().getColumn(1).setPreferredWidth(400);
			table_2.getColumnModel().getColumn(2).setPreferredWidth(400);
			table_2.getColumnModel().getColumn(3).setPreferredWidth(400);
			table_2.getColumnModel().getColumn(4).setPreferredWidth(400);
			table_2.getColumnModel().getColumn(5).setPreferredWidth(400);
			JScrollPane	pane = new JScrollPane(table_2);
			pane.setBorder(BorderFactory.createTitledBorder ("all conntacts"));
			pane.setSize(360, 550);
			pane.setLocation(21, 21);
			contentPane.add(pane);
		}
		catch(Exception e2){
			e2.printStackTrace();

		}


	}
}


