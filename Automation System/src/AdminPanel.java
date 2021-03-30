import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AdminPanel {

	private JFrame frame;
	private String name;
	
	private Connection con = null;
	private Statement statement = null;

	public AdminPanel(String name) {
		this.name = name.toUpperCase();
		connect();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(700, 300, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome " + this.name + "!");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(157, 63, 258, 141);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.setBounds(215, 210, 155, 54);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				AdminPanel.Managements mn = new AdminPanel.Managements();
				
			}
		});
		
		JLabel admin = new JLabel();
		admin.setIcon(new ImageIcon(getClass().getResource("background.jpg")));
		admin.setBounds(0,0,584,372);
		frame.getContentPane().add(admin);
		
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
	
	private static class Managements {

		private JFrame frame;
		private Connection con = null;
		private Statement statement = null;
		
		public Managements() {
			connect();
			initialize();
		}

		private void initialize() {
			frame = new JFrame();
			frame.setBounds(700, 300, 600, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Please choose a transaction.");
			lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 16));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(162, 36, 236, 86);
			frame.getContentPane().add(lblNewLabel);
			
			JButton addEmployee = new JButton("Add Employee");
			addEmployee.setBounds(101, 133, 139, 47);
			addEmployee.setBackground(Color.PINK);
			addEmployee.setForeground(Color.WHITE);
			addEmployee.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String email = JOptionPane.showInputDialog("Please enter the email of the employee.");
					String passaword = JOptionPane.showInputDialog("Please enter the passaword of the employee");
					String brach = JOptionPane.showInputDialog("Please enter the branch");
					
					try {
						
						PreparedStatement stmt =  (PreparedStatement) con.prepareStatement("INSERT INTO " + brach + "(email, passaword) VALUES (?, ?)");
						stmt.setString(1, email);
						stmt.setString(2, passaword);
						stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Employee added succesfuly!");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			frame.getContentPane().add(addEmployee);
			
			JButton removeEmployee = new JButton("Remove Employee");
			removeEmployee.setBounds(101, 235, 139, 47);
			removeEmployee.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String email = JOptionPane.showInputDialog("Please enter the email of the employee.");
					String brach = "";
					boolean mail = false;
					boolean br = false;
					if (email != null) {
						
						brach = JOptionPane.showInputDialog("Please enter the branch");
					} else {
						mail = true;
						JOptionPane.showMessageDialog(null,"You have to input an Answer!");
						
					}
					if (brach == null) {
						br = false;
						JOptionPane.showMessageDialog(null, "Invalid branch!");
						
					} 
					if (!(br && mail)) {
						
						try {
							PreparedStatement stmt =  (PreparedStatement) con.prepareStatement("DELETE FROM "+ brach + " WHERE email = '" + email + "' ");
							stmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "Employee fired succesfuly!");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Employee not found!");
						}
					}
				}
			});
			frame.getContentPane().add(removeEmployee);
			
			JButton btnAddBranch = new JButton("Add Branch");
			btnAddBranch.setBounds(342, 133, 139, 47);
			btnAddBranch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String brach = JOptionPane.showInputDialog("Please enter the branch name");
					String query = "CREATE TABLE IF NOT EXISTS " + brach + " (" +
						    "  email TEXT(25)," +
						    "  passaword TEXT(25))";
					try {
						statement = (Statement) con.createStatement();
						statement.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Branch added successfully");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			frame.getContentPane().add(btnAddBranch);
			
			JButton btnRemoveBrancj = new JButton("Remove Branch");
			btnRemoveBrancj.setBounds(342, 235, 139, 47);
			
			btnRemoveBrancj.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String branch = JOptionPane.showInputDialog("Please enter the branch you want to delete.");
					try {
						
						String sql = "DROP TABLE " + branch ;
						statement = (Statement) con.createStatement();
						statement.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "Branch deleted succesfully");
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			frame.getContentPane().add(btnRemoveBrancj);
			
			
			JButton btnNewButton = new JButton("Return to main menu");
			btnNewButton.setBounds(0, 0, 139, 47);
			frame.getContentPane().add(btnNewButton);
			
			
			JLabel admin = new JLabel();
			admin.setIcon(new ImageIcon(getClass().getResource("background.jpg")));
			admin.setBounds(0,0,584,372);
			frame.getContentPane().add(admin);
			
			
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
