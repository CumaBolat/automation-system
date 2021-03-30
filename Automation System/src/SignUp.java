import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mysql.jdbc.Statement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class SignUp {
	
	
	private Connection con = null;
	private Statement statement = null;
	private JFrame frame;
	private JTextField name;
	private JTextField surName;
	private JTextField emaill;
	private JTextField passaword;
	private JTextField age;

	public SignUp() {
		connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 300, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sign Up");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(64, 11, 180, 88);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(38, 120, 110, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surname");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(38, 175, 110, 44);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(38, 230, 110, 44);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Passaword");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(38, 285, 110, 44);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Age");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(309, 120, 110, 44);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		name = new JTextField();
		name.setBounds(158, 127, 98, 33);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		surName = new JTextField();
		surName.setColumns(10);
		surName.setBounds(158, 188, 98, 33);
		frame.getContentPane().add(surName);
		
		emaill = new JTextField();
		emaill.setColumns(10);
		emaill.setBounds(158, 243, 98, 33);
		frame.getContentPane().add(emaill);
		
		passaword = new JTextField();
		passaword.setColumns(10);
		passaword.setBounds(158, 298, 98, 33);
		frame.getContentPane().add(passaword);
		
		age = new JTextField();
		age.setColumns(10);
		age.setBounds(414, 127, 98, 33);
		frame.getContentPane().add(age);
		
		JButton btnNewButton = new JButton("Create account");
		btnNewButton.setFont(new Font("SimSun", Font.BOLD, 14));
		btnNewButton.setBounds(371, 242, 162, 79);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String isim = name.getText();
				String soyisim = surName.getText();
				String mail = emaill.getText();
				String sifre = passaword.getText();
				String yas = age.getText();
				
				if (isim.equals("") || soyisim.equals("") || mail.equals("") || sifre.equals("") || yas.equals("")) {
					
					JOptionPane.showMessageDialog(null, "Please fill all the spaces!");
					name.setText("");
					surName.setText("");
					emaill.setText("");
					passaword.setText("");
					age.setText("");
					
				} else if (Integer.valueOf(yas) < 18) {
					
					JOptionPane.showMessageDialog(null, "You have to be at least 18 years old to sign up!");
					name.setText("");
					surName.setText("");
					emaill.setText("");
					passaword.setText("");
					age.setText("");
					
				} else {
					
					String sql = "INSERT INTO `users`(`Name`, `Surname`, `Passaword`, `E-mail`, `Age`) VALUES ("+
					"'" + isim + "', " +
					"'" + soyisim + "'," + 
					"'" + sifre + "'," +
					"'" + mail + "'," +
					"'" + Integer.valueOf(yas) + "') ";
					
					try {
						Statement stm = (Statement) con.createStatement();
						stm.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Account created successfully");
						frame.dispose();
						MainMenu mm = new MainMenu();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
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
