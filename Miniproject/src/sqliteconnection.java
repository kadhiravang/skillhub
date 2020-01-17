import java.sql.*;
import javax.swing.*;
public class sqliteconnection {
	public static Connection dbConnector()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\kadmug\\Downloads\\projectdatabase.sqlite");
			JOptionPane.showMessageDialog(null, "Connected Sucessfully");
			return conn;
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}

}
