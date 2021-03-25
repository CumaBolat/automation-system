import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {

	private JFrame frame;
	private JLabel lblNewLabel;

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.getContentPane().setForeground(Color.RED);
		frame.setBounds(700, 300, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel("<html> Welcome to<br/>Fuhus Kaplani Furnitures!</html>",  SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setBounds(92, 51, 404, 94);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Admin Login");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(60, 183, 112, 41);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				AdminLogin admin = new AdminLogin();
				//System.exit(0);
			}
		});
		
		frame.getContentPane().add(btnNewButton);
		
		JButton btnEmployeeLogin = new JButton("Employee Login");
		btnEmployeeLogin.setBackground(new Color(0, 0, 0));
		btnEmployeeLogin.setForeground(Color.WHITE);
		btnEmployeeLogin.setBounds(214, 183, 127, 41);
		btnEmployeeLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				EmployeeLogin emp = new EmployeeLogin();
			}
		});
		frame.getContentPane().add(btnEmployeeLogin);
		
		JButton btnUserLogin = new JButton("User Login");
		btnUserLogin.setForeground(Color.WHITE);
		btnUserLogin.setBackground(Color.BLACK);
		btnUserLogin.setBounds(385, 183, 112, 41);
		btnUserLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				UserLogin user = new UserLogin();
				
			}
		});
		frame.getContentPane().add(btnUserLogin);
		
		JButton btnSingUp = new JButton("Sing Up");
		btnSingUp.setForeground(Color.WHITE);
		btnSingUp.setBackground(Color.BLACK);
		btnSingUp.setBounds(284, 254, 112, 41);
		frame.getContentPane().add(btnSingUp);
		
		JLabel lblNewLabel_1 = new JLabel("Don't have an account?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(92, 262, 182, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JLabel kaplan = new JLabel();
		ImageIcon icon = new ImageIcon("tiger.png");
		kaplan.setIcon(icon);
		kaplan.setBounds(0,0,584,372);
		frame.getContentPane().add(kaplan);
		
		//frame.add(new JLabel(new ImageIcon("C:\\Users\\cuma_\\Desktop\\workspace\\Otomation System")));		
		
		frame.setVisible(true);
	}
}
