package model;

import java.sql.Connection;

public class StockModel {
	static Connection con;
	String url;
	String user;
	String pass;
	
	public void dbConnection(){
		try {
			con = DBconnection.getConnection();
		} catch (Exception e) {
			System.out.println("StockModel DB연결 실패" + e.getMessage());// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//재고 입력
	public void insert(){
	}
	//재고 수정
	public void modify(){
			
	}
	public void delete(){
	}
	
}
