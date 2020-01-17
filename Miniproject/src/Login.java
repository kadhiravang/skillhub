import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Canvas;
import java.awt.ScrollPane;
import java.awt.CardLayout;
import java.awt.Button;


@SuppressWarnings("unused")
public class Login {
   
	private JFrame frame;
	int count=0,flag=1,flag1=0;

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
	public void register(int flag1) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register(flag1);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
	Connection connection=null;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtYoume;
	private JTextField txtWhoWasThe;
	private final ButtonGroup buttonGroup_5 = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_6 = new ButtonGroup();
	private JTextField question4;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtWhichCricketPalyer;
	private JTextField txtThankYouFor;
	private JTextArea txtThankYouFor1;
	 JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);

	JLabel label = new JLabel("");
	String name;
	String password;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=sqliteconnection.dbConnector();
		try {
			PreparedStatement ps1;
			String query1="select * from kallu";
			ps1 = connection.prepareStatement(query1);
			ResultSet rs1=ps1.executeQuery();
			while(rs1.next())
			{flag=flag+1;}
			ps1.close();
			flag1=flag;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();

		JTextField txtRajSaysRameshs_1;
	    JTextField txtVinodIsThe;
		
		
		frame.setBounds(100, 100, 797, 576);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblwelcome = new JLabel("Welcome to the Online Quiz !!!!");
		lblwelcome.setBounds(138, 36, 407, 54);
		lblwelcome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblwelcome.setForeground(Color.DARK_GRAY);
		frame.getContentPane().add(lblwelcome);
		
		JLabel lblUsername = new JLabel("UserName:");
		lblUsername.setBounds(138, 199, 135, 35);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(377, 199, 208, 35);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(138, 304, 135, 35);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(377, 304, 208, 35);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("Login!!!");
		btnLogin.setBounds(298, 407, 118, 35);
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
				String query="select * from kallu where name=? and Password=?";
				PreparedStatement ps=connection.prepareStatement(query);
				name=textField.getText();
				password=passwordField.getText();
				ps.setString(1,textField.getText());
				ps.setString(2,passwordField.getText());
				ResultSet rs=ps.executeQuery();
				int count=0;
				while(rs.next()){
			count=count+1;}
		if(count==1) {
			JOptionPane.showMessageDialog(null, "Logged in sucessfully");
			frame.getContentPane().removeAll();
			frame.repaint();
			panel.setVisible(true);
			frame.getContentPane().add(panel);
			frame.getIgnoreRepaint();
			
		}
		else    {
		JOptionPane.showMessageDialog(null,"Incorrect Username or Password");}
		rs.close();
		ps.close();	
		}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,e);
				}
	
						
}
		});
		JButton btnNewButton = new JButton("Next>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel2.setVisible(false);
				frame.repaint();
				frame.getContentPane().add(panel3);
				panel3.setVisible(true);
				JOptionPane.showMessageDialog(null, "Score is:"+count);
				
				
				try {String query="UPDATE kallu SET marks = ? WHERE name=?";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setInt(1, count);
					ps.setString(2,name);
					int rowAffected = ps.executeUpdate();
					ps.close();
					try {
						PreparedStatement ps1;
						String query1="select * from kallu";
						ps1 = connection.prepareStatement(query1);
						ResultSet rs1=ps1.executeQuery();
						ResultSetMetaData rs1md=rs1.getMetaData();
						int colno=rs1md.getColumnCount();
						StringBuilder str=new StringBuilder("ScoreCard:\nSno Name  Phno           Age     score\n");
						String str1=new String(); 
						while(rs1.next())
							{for(int i = 1 ; i <=colno; i++){
								if(i!=colno-1)
						      str.append(rs1.getString(i) + " ");
						      }str.append("\n");
						str1=str.toString();
						txtThankYouFor1.setText(str1);
							}
						ps1.close();	
					} catch (SQLException e) {
						e.printStackTrace();
					}

				} catch (SQLException e) {
				e.printStackTrace();
				}
				
			
				
			}
		});
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(632, 56, 89, 23);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				register(flag);
		
			}
		});
		frame.getContentPane().add(btnRegister);
		
		
		panel.setBounds(0, 0, 779, 540);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtYoume = new JTextField();
		txtYoume.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtYoume.setText("Which state government in india declares 2020 as the Artificial year?");
		txtYoume.setBounds(38, 11, 586, 96);
		panel.add(txtYoume);
		txtYoume.setColumns(10);
		
		JRadioButtonMenuItem rdbtnmntmTelengana = new JRadioButtonMenuItem("Telengana");
		buttonGroup.add(rdbtnmntmTelengana);
		JRadioButtonMenuItem rdbtnmntmTamilNadu = new JRadioButtonMenuItem("Tamil Nadu");
		buttonGroup.add(rdbtnmntmTamilNadu);
		JRadioButtonMenuItem rdbtnmntmGujarat = new JRadioButtonMenuItem("Gujarat");
		buttonGroup.add(rdbtnmntmGujarat);
		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Bihar");
		buttonGroup.add(rdbtnmntmNewRadioItem);
		rdbtnmntmTelengana.addActionListener(new ActionListener() {
			int f;
			public void actionPerformed(ActionEvent e) {
				if(f==0)
				count++;
				f++;
				}
		});
		rdbtnmntmTelengana.setBounds(38, 118, 125, 22);
		panel.add(rdbtnmntmTelengana);
		
		
		rdbtnmntmTamilNadu.setBounds(38, 151, 125, 22);
		panel.add(rdbtnmntmTamilNadu);
		
		
		rdbtnmntmGujarat.setBounds(38, 184, 125, 22);
		panel.add(rdbtnmntmGujarat);
		
		Button button = new Button("Next>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				frame.repaint();
				frame.getContentPane().add(panel1);
				panel1.setVisible(true);
				}
		});
		button.setBounds(654, 467, 70, 22);
		panel.add(button);
		
		
		rdbtnmntmNewRadioItem.setBounds(38, 227, 125, 22);
		panel.add(rdbtnmntmNewRadioItem);
		
		txtWhoWasThe = new JTextField();
		txtWhoWasThe.setText("Who was the first woman chief minister of Tamil Nadu?");
		txtWhoWasThe.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtWhoWasThe.setBounds(38, 274, 586, 72);
		panel.add(txtWhoWasThe);
		txtWhoWasThe.setColumns(10);
		
		JRadioButtonMenuItem rdbtnmntmJjayalalitha = new JRadioButtonMenuItem("J.Jayalalitha");
		buttonGroup_1.add(rdbtnmntmJjayalalitha);
		JRadioButtonMenuItem rdbtnmntmJanakiRamachandran = new JRadioButtonMenuItem("Janaki Ramachandran");
		buttonGroup_1.add(rdbtnmntmJanakiRamachandran);
		JRadioButtonMenuItem rdbtnmntmValarmathiRamasamy = new JRadioButtonMenuItem("Valarmathi Ramasamy");
		buttonGroup_1.add(rdbtnmntmValarmathiRamasamy);
		JRadioButtonMenuItem rdbtnmntmRajaRajeshwari = new JRadioButtonMenuItem("Raja Rajeshwari");
		buttonGroup_1.add(rdbtnmntmRajaRajeshwari);
		rdbtnmntmJanakiRamachandran.addActionListener(new ActionListener() {
			int b=0;
			public void actionPerformed(ActionEvent e) {
				if(b==0)
				count++;
				b++;
				}
		});
		rdbtnmntmJjayalalitha.setBounds(38, 357, 125, 22);
		panel.add(rdbtnmntmJjayalalitha);
		
		
		rdbtnmntmJanakiRamachandran.setBounds(38, 390, 125, 22);
		panel.add(rdbtnmntmJanakiRamachandran);
		
	
		rdbtnmntmValarmathiRamasamy.setBounds(38, 424, 187, 22);
		panel.add(rdbtnmntmValarmathiRamasamy);
		
		
		rdbtnmntmRajaRajeshwari.setBounds(38, 457, 125, 22);
		panel.add(rdbtnmntmRajaRajeshwari);
		panel.setVisible(false);
		panel1.setBounds(0, 0, 779, 540);
		panel1.setLayout(null);
	    panel1.setVisible(false);
		txtRajSaysRameshs_1 = new JTextField();
		txtRajSaysRameshs_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtRajSaysRameshs_1.setText("Raj says Ramesh's grand Father is the only son of my father ? how is Ramesh related to Raj?");
		txtRajSaysRameshs_1.setBounds(22, 11, 747, 88);
		panel1.add(txtRajSaysRameshs_1);
		txtRajSaysRameshs_1.setColumns(10);
		
		JRadioButtonMenuItem rdbtnmntmGrandSon = new JRadioButtonMenuItem("Grand Son");
		rdbtnmntmGrandSon.setBounds(22, 123, 125, 22);
		panel1.add(rdbtnmntmGrandSon);
		
		JRadioButtonMenuItem rdbtnmntmFather = new JRadioButtonMenuItem("Father");
		rdbtnmntmFather.setBounds(22, 156, 125, 22);
		panel1.add(rdbtnmntmFather);
		
		JRadioButtonMenuItem rdbtnmntmGrandFather = new JRadioButtonMenuItem("Grand Father");
		rdbtnmntmGrandFather.setBounds(22, 189, 125, 22);
		panel1.add(rdbtnmntmGrandFather);
		rdbtnmntmGrandFather.addActionListener(new ActionListener() {
			int c=0;
			public void actionPerformed(ActionEvent e) {
				if(c==0)
				count++;
				c++;
			}
		});
		
		JRadioButtonMenuItem rdbtnmntmCousin = new JRadioButtonMenuItem("Cousin");
		rdbtnmntmCousin.setBounds(22, 222, 125, 22);
		panel1.add(rdbtnmntmCousin);
		
		txtVinodIsThe = new JTextField();
		txtVinodIsThe.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtVinodIsThe.setText("Vinod is the brother of ram.Madhu is the sister of vinod.Aakash is the brother of varsha and varsha is the daughter of Ram. Who is the Uncle of Aakash?");
		txtVinodIsThe.setBounds(22, 255, 747, 88);
		panel1.add(txtVinodIsThe);
		txtVinodIsThe.setColumns(10);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem1 = new JRadioButtonMenuItem("Vinod");
		rdbtnmntmNewRadioItem1.setBounds(22, 370, 125, 22);
		panel1.add(rdbtnmntmNewRadioItem1);
		rdbtnmntmNewRadioItem1.addActionListener(new ActionListener() {
			int ex=0;
			public void actionPerformed(ActionEvent e) {
				if(ex==0)
				count++;
				ex++;

			}
		});
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_4 = new JRadioButtonMenuItem("Ram");
		rdbtnmntmNewRadioItem_4.setBounds(22, 403, 125, 22);
		panel1.add(rdbtnmntmNewRadioItem_4);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_2 = new JRadioButtonMenuItem("Kishore");
		rdbtnmntmNewRadioItem_2.setBounds(22, 441, 125, 22);
		panel1.add(rdbtnmntmNewRadioItem_2);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_3 = new JRadioButtonMenuItem("Madhan");
		rdbtnmntmNewRadioItem_3.setBounds(22, 485, 125, 22);
		panel1.add(rdbtnmntmNewRadioItem_3);
		JButton btnNext = new JButton("Next>");
		btnNext.setBounds(641, 484, 89, 23);
		panel1.add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel1.setVisible(false);
				frame.repaint();
				frame.getContentPane().add(panel2);
				panel2.setVisible(true);
			}
});
		buttonGroup_4.add(rdbtnmntmGrandSon);		
		buttonGroup_4.add(rdbtnmntmFather);
		buttonGroup_4.add(rdbtnmntmGrandFather);
		buttonGroup_4.add(rdbtnmntmCousin);
		buttonGroup_3.add(rdbtnmntmNewRadioItem1);
		buttonGroup_3.add(rdbtnmntmNewRadioItem_4);
		buttonGroup_3.add(rdbtnmntmNewRadioItem_2);
		buttonGroup_3.add(rdbtnmntmNewRadioItem_3);
		panel2.setBounds(0, 0, 779, 540);
		panel2.setLayout(null);
		
		question4 = new JTextField();
		question4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		question4.setText("Whats the position of india in FIFA Ranking");
		question4.setBounds(24, 11, 719, 88);
		panel2.add(question4);
		question4.setColumns(10);
		
		JRadioButton radioButtonq5 = new JRadioButton("104");
		buttonGroup_5.add(radioButtonq5);
		radioButtonq5.setBounds(24, 137, 109, 23);
        panel2.add(radioButtonq5);
        radioButtonq5.addActionListener(new ActionListener() {int d=0;
			public void actionPerformed(ActionEvent arg0) {
				if(d==0)
				count++;
				d++;
			}
		});

		
		JRadioButton radioButton = new JRadioButton("103");
		buttonGroup_5.add(radioButton);
		radioButton.setBounds(24, 163, 109, 23);
		panel2.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("113");
		buttonGroup_5.add(radioButton_1);
		radioButton_1.setBounds(24, 189, 109, 23);
		panel2.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("75");
		buttonGroup_5.add(radioButton_2);
		radioButton_2.setBounds(24, 215, 109, 23);
		panel2.add(radioButton_2);
		
		txtWhichCricketPalyer = new JTextField();
		txtWhichCricketPalyer.setText("Which cricket palyer is known as the \"Haryana's Hurricane\"?");
		txtWhichCricketPalyer.setBounds(24, 245, 719, 72);
		panel2.add(txtWhichCricketPalyer);
		txtWhichCricketPalyer.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Harbhajan Singh");
		rdbtnNewRadioButton.setBounds(24, 324, 150, 23);
		panel2.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Kapil Dev");
		rdbtnNewRadioButton_1.setBounds(24, 350, 109, 23);
		panel2.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			int a=0;
			public void actionPerformed(ActionEvent e) {
				if(a==0)
				count++;
				a++;
			}
		});
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Virendar Shewag");
		rdbtnNewRadioButton_2.setBounds(24, 376, 150, 30);
		panel2.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Virat Kohli");
		rdbtnNewRadioButton_3.setBounds(24, 402, 109, 23);
		panel2.add(rdbtnNewRadioButton_3);
		
		
		
		btnNewButton.setBounds(654, 447, 89, 23);
		panel2.add(btnNewButton);
		buttonGroup_6.add(rdbtnNewRadioButton);
		buttonGroup_6.add(rdbtnNewRadioButton_1);
		buttonGroup_6.add(rdbtnNewRadioButton_2);
		buttonGroup_6.add(rdbtnNewRadioButton_3);		
		panel3.setBounds(0, 0, 779, 540);
		panel3.setLayout(null);
		
		
		
		label.setBounds(333, 156, 103, 62);
		panel3.add(label);
		
		txtThankYouFor = new JTextField();
		txtThankYouFor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtThankYouFor.setText("Thank You for taking part in the Quiz competition Check the score Board below!!");
		txtThankYouFor.setBounds(82, 31, 600, 76);
		panel3.add(txtThankYouFor);
		txtThankYouFor.setColumns(10);
		txtThankYouFor1 = new JTextArea();
		txtThankYouFor1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtThankYouFor1.setBounds(82, 85, 600, 400);
		panel3.add(txtThankYouFor1);
		}
}