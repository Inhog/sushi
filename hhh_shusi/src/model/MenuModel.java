package model;

import java.io.File;
import java.sql.Connection;

import javax.swing.JFileChooser;

public class MenuModel {
	
	static Connection con;
	String url;
	String user;
	String pass;
	
	public void dbConnection(){
		try {
			con = DBconnection.getConnection();
		} catch (Exception e) {
			System.out.println("MenuModel DB 연결 실패 " + e.getMessage());
			// TODO Auto-generated catch block
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
	


	
/* 동작 체크를 위함 */	
//	public static void main(String args[]){
//		MenuModel mm = new MenuModel();
//		System.out.println(mm.getFileLocation());
//	}
}
