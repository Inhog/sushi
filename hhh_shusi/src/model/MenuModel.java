package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.MenuVO;

public class MenuModel {
	
	static Connection 	con;
	PreparedStatement 	ps = null;
	ResultSet 			rs = null;
	
	public MenuModel(){
		dbConnection();
	}
	
	public void dbConnection(){
		try {
			con = DBconnection.getConnection();
		} catch (Exception e) {
			System.out.println("MenuModel DB 연결 실패 " + e.getMessage());
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}

	/**
	  * Created by clap on 2018. 4. 20. 오전 10:00:14
	  * 재고입력
	  * 매개변수로 menu를 받아서 db에 추가.
	  */ 
	public void addMenu(MenuVO menu) throws Exception{
		
		String sql = "INSERT INTO MENU(menu_code, name, price, category, image) "
				+ "   VALUES		  (		   ?,	 ?,     ?,        ?,     ?) ";
		
		ps = con.prepareStatement(sql);
		
		ps.setString(1, menu.getMenuCode());
		ps.setString(2, menu.getName());
		ps.setString(3, menu.getPrice());
		ps.setString(4, menu.getCategory());
		ps.setString(5, menu.getImage()); 		// .\img\ <-- 상대경로 형태로 저장
		
		int result = ps.executeUpdate();
		ps.close();
		
		
		
	}
	//재고 수정
	public void modify(MenuVO menu) throws Exception{
			
		String sql = "UPDATE MENU "
				+ "	  SET name = ?, price = ?, category = ?, image = ? "
				+ "	  WHERE menu_code = ?";
		
		ps = con.prepareStatement(sql);
		
		
		ps.setString(1, menu.getName());
		ps.setString(2, menu.getPrice());
		ps.setString(3, menu.getCategory());
		ps.setString(4, menu.getImage()); 		// .\img\ <-- 상대경로 형태로 저장
		ps.setString(5, menu.getMenuCode());
		
		int result = ps.executeUpdate();
		ps.close();
		
	}
	
	public ArrayList<ArrayList<String>> displayAll() throws Exception{
		ArrayList<ArrayList<String>> menuList = new ArrayList<ArrayList<String>>();
		
		String sql = "SELECT 	menu_code, name, price, category, image "
				+ "	  FROM 	 	menu "
				+ "   ORDER BY 	menu_code ";
		
		ps =  con.prepareStatement(sql);
		rs =  ps.executeQuery();
		
		while(rs.next()){
			
//			[MENU_CODE, NAME, PRICE, CATEGORY, IMAGE]
			ArrayList<String> menu = new ArrayList<String>();
			menu.add(rs.getString("MENU_CODE"));
			menu.add(rs.getString("NAME"));
			menu.add(rs.getString("PRICE"));
			menu.add(rs.getString("CATEGORY"));
			menu.add(rs.getString("IMAGE"));
			
			menuList.add(menu);
		}
		ps.close();
		return menuList;
	}
	
	public void delete(MenuVO menu) throws Exception{
		String sql = "DELETE FROM  MENU "
				+ "   WHERE        MENU_CODE = ?";
		
		ps = con.prepareStatement(sql);
		ps.setString(1, menu.getMenuCode());
		
		int result = ps.executeUpdate();
		
		ps.close();
	}
	


	
/* 동작 체크를 위함 */	
//	public static void main(String args[]){
//		MenuModel mm = new MenuModel();
//		System.out.println(mm.getFileLocation());
//	}
}
