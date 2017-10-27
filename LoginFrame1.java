package view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import clients.UserClient;
import entites.UserEntity;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JButton;

public class LoginFrame1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passF;
	private UserClient userclient;
	  private UserEntity user;
	  public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginFrame1 frame = new LoginFrame1();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});

		}

	public LoginFrame1() {
		userclient = new UserClient("localhost");
        user = new UserEntity();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(157, 24, 139, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblIp = new JLabel("IP");
		lblIp.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIp.setBounds(76, 27, 37, 14);
		contentPane.add(lblIp);
		
		textField_1 = new JTextField();
		textField_1.setBounds(157, 80, 139, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(59, 83, 74, 14);
		contentPane.add(lblNewLabel);
		
		passF = new JPasswordField();
		passF.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passF.setBounds(157, 133, 139, 20);
		contentPane.add(passF);
		passF.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(52, 138, 74, 20);
		contentPane.add(lblNewLabel_1);
		
		JButton btnLogin = new JButton("login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userclient.setIP(textField.getText());
				if (userclient.ExitUser(textField_1.getText(), String.valueOf(passF.getPassword()))) {
		            user.setUsername(textField.getText());
		            user.setPassword(String.valueOf(passF.getPassword()));
					JOptionPane.showInternalMessageDialog(contentPane, "hello " + textField_1.getText());
					MenuFrame menu = new MenuFrame(textField.getText());
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showInternalMessageDialog(contentPane, "not valid");
				}

			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBounds(157, 197, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnShowContacts = new JButton("show contacts");
		btnShowContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userclient.setIP(textField.getText());
				ShowFrame show = null;
				try {
					show = new ShowFrame(textField.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				show.setVisible(true);
				dispose();
				

			}
		});
				
		btnShowContacts.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnShowContacts.setBounds(308, 197, 116, 23);
		contentPane.add(btnShowContacts);
	}
}
