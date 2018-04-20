package DoctorsAP;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LogIn_Doctors {

	private JFrame frame;
	private JPasswordField txtDoctorPassword;
	private JTextField txtDoctorID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn_Doctors window = new LogIn_Doctors();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public LogIn_Doctors() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 302, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn_Page window = new LogIn_Page();
				window.frame.setVisible(true);
				window.frame.setResizable(false);
        		frame.dispose();
			}
		});
		button.setBounds(10, 11, 67, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Login");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					try{
					
					int flag = 1;				
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctorsAP", "root" , "");
					Statement st = myConn.createStatement();
					ResultSet rs = st.executeQuery ("select * from d_registration");
		
					while (rs.next())
					{
						if(rs.getString(2).equals(txtDoctorID.getText()) && rs.getString(3).equals(txtDoctorPassword.getText()) )
						{
							flag = 0;
							HomePage_Patients ob1 = new HomePage_Patients();
							ob1.main(null);
							frame.dispose();							
							break;
						}
					}
					if(flag == 0)
					{
						JOptionPane.showMessageDialog(null, "login success !!");
					}
					else{
						JOptionPane.showMessageDialog(null, "login invalid !!");
					}
							
					//JOptionPane.showMessageDialog(null, "connection ok !!");
					
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "connection lost !!");
					//JOptionPane.showMessageDialog(null, e);
				
				}
				
			}
		});
		button_1.setToolTipText("Press to Login");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(111, 283, 141, 29);
		frame.getContentPane().add(button_1);
		
		txtDoctorPassword = new JPasswordField();
		txtDoctorPassword.setToolTipText("Please type correct password");
		txtDoctorPassword.setBounds(111, 250, 141, 22);
		frame.getContentPane().add(txtDoctorPassword);
		
		txtDoctorID = new JTextField();
		txtDoctorID.setToolTipText("Please type correct userID");
		txtDoctorID.setColumns(10);
		txtDoctorID.setBounds(111, 214, 141, 22);
		frame.getContentPane().add(txtDoctorID);
		
		JLabel label = new JLabel("Password");
		label.setForeground(Color.BLACK);
		label.setBounds(10, 237, 67, 48);
		frame.getContentPane().add(label);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setForeground(Color.BLACK);
		lblDoctorId.setBounds(10, 200, 67, 48);
		frame.getContentPane().add(lblDoctorId);
	}
}
