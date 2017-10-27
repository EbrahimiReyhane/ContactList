package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clients.ContactClient;
import entites.ContactEntity;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuFrame extends JFrame {

	private JPanel contentPane;
   private String ip;

	
	public MenuFrame(String ip) {
		this.ip=ip;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("insert  new contact");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertFrame insert = new InsertFrame(ip);
				insert.setVisible(true);
				dispose();
			
				

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(237, 21, 173, 31);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("delete contact");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(237, 79, 173, 31);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("show  contacts");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowFrame show = null;
				try {
					show = new ShowFrame(ip);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				show.setVisible(true);
				dispose();
				

			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBounds(237, 147, 173, 31);
		contentPane.add(btnNewButton_2);
	}

}
