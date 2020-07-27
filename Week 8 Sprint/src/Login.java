import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frame;
	private JPasswordField tfPassword;
	private JTextField tfUsername;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblError;
	private JLabel lblBackground;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		lblError = new JLabel("Incorrect username/password! Please retry.");
		lblError.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblError.setForeground(new Color(128, 0, 0));
		lblError.setBounds(119, 138, 321, 14);
		frame.getContentPane().add(lblError);
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblError.setVisible(false);

		JButton btnLogin = new JButton("Login");
		Image photo = new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		btnLogin.setIcon(new ImageIcon(photo));
		

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Checker = 0;
				String search = tfUsername.getText();
				
				// Create a variable for the connection string.
				String connectionUrl = "jdbc:sqlserver://localhost:1433;"
						+ "databaseName= Week8Sprint;integratedSecurity=true;";

				// Declare the JDBC Objects.
				Connection con = null;
				
				try {
					// Establish the connection.
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(connectionUrl);

					// Create and execute an SQL statement that returns some data
					String SQLSearch = "SELECT * FROM [dbo].[Users] WHERE Username = '" + search + "'";

					ResultSet rs = con.createStatement().executeQuery(SQLSearch);
					while (rs.next()) {

						
						Checker = 1;
						// insert Data into Text fields
						String Password = rs.getString("Password");
						String InputPass = tfPassword.getText();
						
						if (InputPass.equals(Password)) {
							
							Homepage Homey = new Homepage();
							Homey.Home();
							String Display = tfUsername.getText();
							
							System.out.println("Welcome " + Display + "!");
							
						}else {
							
							lblError.setVisible(true);
							
						}
						
						
						
						}//While End
					
					

				}//Try End

				catch (Exception e1) {

					lblError.setVisible(true);
				}
				
				if (Checker == 0) {
					
					lblError.setVisible(true);
					
				}
				
			}
		});
		btnLogin.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setBounds(170, 290, 190, 67);
		frame.getContentPane().add(btnLogin);

		JButton btnSignUp = new JButton("");
		Image photo2 = new ImageIcon(this.getClass().getResource("/add.png")).getImage();
		btnSignUp.setIcon(new ImageIcon(photo2));
		
		
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SignUp Signey = new SignUp();
				Signey.Register();
				
				
				
			}
		});
		btnSignUp.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnSignUp.setBackground(new Color(255, 255, 255));
		btnSignUp.setBounds(459, 11, 65, 65);
		frame.getContentPane().add(btnSignUp);
		
		tfPassword = new JPasswordField();
		tfPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		tfPassword.setBounds(170, 238, 190, 20);
		frame.getContentPane().add(tfPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(170, 187, 190, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		JLabel lblWelcome = new JLabel("WELCOME.");
		lblWelcome.setBackground(new Color(255, 255, 255));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblWelcome.setForeground(new Color(30, 144, 255));
		lblWelcome.setBounds(157, 40, 218, 51);
		frame.getContentPane().add(lblWelcome);
		
		lblNewLabel = new JLabel("USERNAME:");
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel.setBounds(170, 165, 97, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("PASSWORD:");
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setBounds(170, 218, 97, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Sign Up?");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setBounds(459, 77, 65, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 534, 461);
		frame.getContentPane().add(lblBackground);
		ImageIcon iconBackground1 = new ImageIcon("Images/LoginBack.jpg");
		lblBackground.setIcon(iconBackground1);
		
		

		
	
	}
}
