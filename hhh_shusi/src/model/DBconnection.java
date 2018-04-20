package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	/*오타수정 getConnectio -> getConnection */
	public static Connection getConnection() throws Exception{
		if(con == null) new DBconnection();
		return con;
	}
}
