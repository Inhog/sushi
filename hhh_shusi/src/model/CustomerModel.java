package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.OrderVO;

public class CustomerModel {


	Connection 	con;
	PreparedStatement 	ps = null;
	ResultSet 			rs = null;
	
	public CustomerModel(){
		try {
			dbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dbConnection()throws Exception{
			con = DBconnection.getConnection();
	}

	
	public void addCustomerNO(String tableNO)throws Exception{
		// SQL 문장 1
		String sql = "INSERT INTO customer(customer_no,table_no) VALUES (SQ_CUSTOMER_NO.NEXTVAL,?)";
		System.out.println(sql);
		// SQL 전송객체
		ps = con.prepareStatement(sql);
		ps.setString(1, tableNO);
		// SQL 전송
		ps.executeQuery();

		
		// 닫기
		ps.close();
		
	}
	
	public String getCustomerNO(String tableNO)throws Exception{
		String customerNo = null;
		// SQL 문장 1
		String sql2 ="SELECT customer_no FROM (SELECT * FROM customer WHERE table_no = ? ORDER BY customer_no desc) WHERE rownum =1";
		System.out.println(sql2);
		
		// SQL 전송객체 
		ps = con.prepareStatement(sql2);
		ps.setString(1, tableNO);
		// SQL 전송
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		customerNo = rs.getString("CUSTOMER_NO");
		}
		rs.close();
		ps.close();
		
		return customerNo;
	}
}
