package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	static Connection con;
	String url;
	String user;
	String pass;
	
	private DBconnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		url = "jdbc:oracle:thin:@70.12.115.62:1521:orcl";
		user = "inhog";
		pass = "inhog";
		
		con = DriverManager.getConnection(url,user,pass);
	}
	public static Connection getConnectio() throws Exception{
		if(con == null) new DBconnection();
		return con;
	}
}