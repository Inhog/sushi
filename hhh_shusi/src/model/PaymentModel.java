package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.StockVO;

public class PaymentModel {
	static Connection 	con;
	PreparedStatement 	ps = null;
	ResultSet 			rs = null;
	
	public PaymentModel(){
		try {
			dbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dbConnection()throws Exception{
			con = DBconnection.getConnection();
	}
	public String getTotalPrice(String CustomerNo){
		String totalPrice ="";
		int ItotalPrice=0;
		String sql = "SELECT PRICE,SUM(PRICE) as SUM_PRICE "
				+ "FROM (SELECT MENU_CODE FROM SUSHI_ORDER WHERE CUSTOMER_NO = ?) a"
				+ " INNER JOIN MENU menu "
				+ "ON menu.MENU_CODE = a.MENU_CODE GROUP BY menu.PRICE";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, CustomerNo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ItotalPrice+=Integer.valueOf(rs.getString("SUM_PRICE"));
			}
			
			ps.close();
		} catch (Exception e) {
			System.out.println("총 금액 구하기실패 : " + e.getMessage());
		}
		totalPrice = String.valueOf(ItotalPrice);
		return totalPrice;
	}
	
	public void getPaymentNo(String totalPrice, String method){
		String sql = "INSERT INTO PAYMENT(PAYMENT_NO,TOTAL_PRICE,METHOD) VALUES (SQ_PAYMENT_NO.NEXTVAL,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, totalPrice);
			ps.setString(2, method);
			ps.executeQuery();
			ps.close();
		} catch (SQLException e) {
			System.out.println("결제번호 생성실패: " +e.getMessage());
		}


	}
	public void setPaymentNo(String CustomerNo) {
		String sql2 = "SELECT SQ_PAYMENT_NO.CURRVAL FROM DUAL";
		int SQ_PAYMENT_NO = 0;
		try {
			ps = con.prepareStatement(sql2);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SQ_PAYMENT_NO = rs.getInt("CURRVAL");
			}
			System.out.println(String.valueOf(SQ_PAYMENT_NO));
			ps.close();
		} catch (SQLException e1) {
			System.out.println("시퀀스번호 생성 실패:"+e1.getMessage());
		}
		
		String sql = "UPDATE SUSHI_ORDER SET PAYMENT_NO = ? WHERE CUSTOMER_NO =?";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, SQ_PAYMENT_NO);
			ps.setString(2, CustomerNo);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("결제 실패 : " + e.getMessage());
		}
	}
}
