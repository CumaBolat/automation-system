import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Statement;

public class UserLogin {

	private JFrame frame;
	private JTextField email;
	private JTextField password;
	private Connection con = null;
	private Statement statement = null;
	
	Scanner scanner = new Scanner(System.in);
	public UserLogin() {
		connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.info);
		frame.setBounds(700, 300, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(195, 26, 159, 53);
		frame.getContentPane().add(lblNewLabel);
		
		email = new JTextField();
		email.setBounds(265, 104, 100, 44);
		frame.getContentPane().add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(136, 104, 86, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Passaword");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(136, 182, 86, 44);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(265, 180, 100, 44);
		frame.getContentPane().add(password);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.PINK);
		btnNewButton.setBounds(244, 269, 100, 29);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String mail = email.getText();
				String passWord = password.getText();
				ResultSet rs = null;
				try {
					statement = (Statement) con.createStatement();
					String sql = ("Select * From admins");
					 rs = statement.executeQuery(sql);
					 while (rs.next()) {
						 
						 if (rs.getString("email").equals(mail) && rs.getString("passaword").equals(passWord)) {
							 
							 MainMenu mm = new MainMenu();
							 
						 }
						 
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		frame.getContentPane().add(btnNewButton);

		frame.setVisible(true);
	}
	
	private void connect() {
		
		String url = "jdbc:mysql://localhost:3306/employees";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("driver not found");
		}
		
		try {
			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("could not connect");
		}	
	}
	
}
