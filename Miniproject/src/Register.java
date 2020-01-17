
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Register {
   
    JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnSubmit;
	String uname,password;
	String age;
	String phno;
	int flag1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Enter all your details in this window");
	
	EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
			Register window = new Register(0);
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
	Connection connection=null;
	public Register(int flag1) {
		initialize();
		this.flag1=flag1;
		
			
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection=sqliteconnection.dbConnector();
		frame = new JFrame();
		frame.setBounds(100, 100, 452, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(35, 42, 109, 32);
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(186, 39, 138, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(35, 93, 87, 26);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(186, 93, 138, 26);
		frame.getContentPane().add(passwordField);
		
		JLabel lblPhoneNo = new JLabel("Phone no:");
		lblPhoneNo.setBounds(35, 149, 76, 29);
		frame.getContentPane().add(lblPhoneNo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(186, 146, 138, 32);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(186, 200, 138, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblAge = new JLabel("Age:");
		lblAge.setBounds(35, 203, 62, 20);
		frame.getContentPane().add(lblAge);
		
		btnSubmit = new JButton("Submit!!!");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				uname=textField.getText();
				password=passwordField.getText();
				phno=textField_1.getText();
				age=textField_2.getText();
				
				try {
					
					String query="insert into kallu (empid,name,phno,age,password,marks) values(?,?,?,?,?,?)";
					PreparedStatement ps;
						ps = connection.prepareStatement(query);
						ps.setInt(1,flag1);
						ps.setString(2,uname);;
						ps.setString(5,password);
						ps.setString(3,phno);
						ps.setString(4,age);
						ps.setInt(6,0);

						@SuppressWarnings("unused")
						int rowAffected = ps.executeUpdate();						
						ps.close();
					    frame.setVisible(false);
						
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
			}
		});
		btnSubmit.setBounds(131, 254, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
	
	}
}
