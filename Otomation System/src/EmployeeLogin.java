import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Color;

public class EmployeeLogin {

	private JFrame frame;
	private JTextField email;
	private JTextField password;
	private Connection con = null;
	private Statement statement = null;
	private JTextField emailField;
	private JTextField passawordField;
	/**
	 * Create the application.
	 */
	public EmployeeLogin() {
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
		
		JLabel lblEmployeeLogin = new JLabel("Employee Login");
		lblEmployeeLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmployeeLogin.setBounds(178, 23, 205, 53);
		frame.getContentPane().add(lblEmployeeLogin);
		
		JLabel lblNewLabel = new JLabel("Please select your branch");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel.setBackground(SystemColor.info);
		lblNewLabel.setBounds(128, 87, 198, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(336, 87, 76, 25);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ankara", "Istanbul", "Mugla", "Elbistan"}));
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(128, 147, 86, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(257, 145, 100, 44);
		frame.getContentPane().add(emailField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Passaword");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(128, 217, 86, 44);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		passawordField = new JTextField();
		passawordField.setColumns(10);
		passawordField.setBounds(257, 215, 100, 44);
		frame.getContentPane().add(passawordField);
		
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 239, 213));
		btnNewButton.setBounds(197, 294, 118, 44);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String branch = comboBox.getSelectedItem().toString();
				String sql = "";
				switch (branch){
					case "Ankara": 
						sql = "SELECT * FROM ankaraemployees";
						break;
					case "Istanbul":
						sql = "SELECT * FROM istanbulemployees";
						break;
					case "Mugla":
						sql = "SELECT * FROM muglaemployees";
						break;
					case "Elbistan":
						sql = "SELECT * FROM elbistanemployees";
				} 
				
				
				try {
					statement = (Statement) con.createStatement();
					
					ResultSet rs = statement.executeQuery(sql);
					while (rs.next()) {
						
						
						if (rs.getString("email").equals(emailField.getText()) && rs.getString("passaword").equals(passawordField.getText())) {
							
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
		
		JLabel employee = new JLabel();
		ImageIcon icon = new ImageIcon("employees.png");
		employee.setIcon(icon);
		employee.setBounds(0,0,584,372);
		frame.getContentPane().add(employee);
		
		
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
