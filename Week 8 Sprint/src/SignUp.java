import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class SignUp {

	private JFrame frame;
	private JTextField tfUsername;
	private JTextField tfPass1;
	private JTextField tfPass2;
	private JLabel lblUsername;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblError;
	private JLabel lblSym1;
	private JLabel lblSym2;
	private JLabel lblSym3;
	private JLabel lblBackground;

	/**
	 * Launch the application.
	 */
	public static void Register() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
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
	public SignUp() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(105, 105, 105));
		frame.getContentPane().setLayout(null);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(170, 187, 190, 20);
		frame.getContentPane().add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPass1 = new JTextField();
		tfPass1.setBounds(170, 238, 190, 20);
		frame.getContentPane().add(tfPass1);
		tfPass1.setColumns(10);
		
		tfPass2 = new JTextField();
		tfPass2.setBounds(170, 286, 190, 20);
		frame.getContentPane().add(tfPass2);
		tfPass2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sign Up.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(30, 144, 255));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblNewLabel.setBounds(170, 33, 190, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnSubmit = new JButton("");
		Image photo = new ImageIcon(this.getClass().getResource("/submit.png")).getImage();
		btnSubmit.setIcon(new ImageIcon(photo));
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int Checker2 = 0;
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
						//If username exists display error
						
						lblError.setText("Username Already Exists! Try Another.");
						ImageIcon iconBackground1 = new ImageIcon("Images/criss-cross.png");
						lblSym1.setIcon(iconBackground1);

						Checker = 1;
						
					}//While End

					
					
				}//Try End

				catch (Exception e1) {

					JOptionPane.showMessageDialog(null, "Big Error Fam!");
					
				}
				
				if (Checker == 0) {
					
					lblError.setText("");
					ImageIcon iconBackground2 = new ImageIcon("Images/correct.png");
					lblSym1.setIcon(iconBackground2);
					
					String Pass1 = tfPass1.getText();
					String Pass2 = tfPass2.getText();
					
					if (Pass1.equals(Pass2)) {
						
						lblError.setText("");
						ImageIcon iconBackground3 = new ImageIcon("Images/correct.png");
						lblSym2.setIcon(iconBackground3);
						ImageIcon iconBackground4 = new ImageIcon("Images/correct.png");
						lblSym3.setIcon(iconBackground4);
						
/////////////////////////////////////////////////////////////////////
						// Create a variable for the connection string.
						String connectionUrl1 = "jdbc:sqlserver://localhost:1433;"
								+ "databaseName= Week8Sprint;integratedSecurity=true;";

						// Declare the JDBC Objects.
						Connection con1 = null;
						Statement stmt = null;

						try {
							// Establish the connection.
							Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
							con1 = DriverManager.getConnection(connectionUrl1);

							String Username = tfUsername.getText();
							String Password = tfPass1.getText();
							

							// Create and execute an SQL statement that returns some data
							String SQLstd = "INSERT INTO [dbo].[Users] ([Username],[Password]) VALUES ('"
									+ Username + "','" + Password + "')";

							stmt = con1.createStatement();
							stmt.execute(SQLstd);

							System.out.println("You Have Successfully Registered An Account");
							JOptionPane.showMessageDialog(null, "Account Registered.");
							Checker2 = 1;

						}

						catch (Exception e1) {

							e1.printStackTrace();
						}
						
					if (Checker2 == 1) {
						
						Homepage Homey = new Homepage();
						Homey.Home();
						
					}
						
						
						
						
/////////////////////////////////////////////////////////////////////						
					}else {
						
						lblError.setText("Passwords Entered Do Not Match! Try Again.");
						ImageIcon iconBackground3 = new ImageIcon("Images/criss-cross.png");
						lblSym2.setIcon(iconBackground3);
						ImageIcon iconBackground4 = new ImageIcon("Images/criss-cross.png");
						lblSym3.setIcon(iconBackground4);
						
					}
					
				}
				
			
			}
		});
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setBounds(170, 334, 190, 67);
		frame.getContentPane().add(btnSubmit);
		
		lblUsername = new JLabel("Enter Prefered Username:");
		lblUsername.setForeground(new Color(30, 144, 255));
		lblUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblUsername.setBounds(170, 168, 190, 14);
		frame.getContentPane().add(lblUsername);
		
		lblNewLabel_1 = new JLabel("Enter Password:");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(170, 218, 190, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Confirm Password:");
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(170, 269, 190, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblError.setForeground(new Color(128, 0, 0));
		lblError.setBounds(98, 132, 336, 14);
		frame.getContentPane().add(lblError);
		
		lblSym1 = new JLabel("");
		lblSym1.setBounds(376, 169, 40, 40);
		frame.getContentPane().add(lblSym1);
		
		lblSym2 = new JLabel("");
		lblSym2.setBounds(376, 218, 40, 40);
		frame.getContentPane().add(lblSym2);
		
		lblSym3 = new JLabel("");
		lblSym3.setBounds(376, 266, 40, 40);
		frame.getContentPane().add(lblSym3);
		
		lblBackground = new JLabel("");
		lblBackground.setBounds(0, 0, 536, 463);
		frame.getContentPane().add(lblBackground);
		frame.setBounds(100, 100, 552, 502);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon iconBackground1 = new ImageIcon("Images/LoginBack.jpg");
		lblBackground.setIcon(iconBackground1);
	}

}
