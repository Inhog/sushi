package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.Stock;

public class StockModel {
	static Connection con;
	String url;
	String user;
	String pass;
	
	public StockModel(){
		try {
			dbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dbConnection()throws Exception{
			con = DBconnection.getConnection();
	}
	//재고 입력
	public void insert(Stock st){
		String sql="INSERT INTO STOCK (STOCK_NO, QUANTITY, EXPIRED_DATE, ADD_DATE,MATERIAL_CODE) "
				+ "VALUES ( SQ_STOCK_NO.NEXTVAL,        ?,            ?,  SYSDATE,            ?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(st.getQuantity()));
			ps.setString(2, st.getExpiredDate());
			ps.setString(3, st.getMaterial_Code());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("DB연결 실패:"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//재고 수정
	public void modify(Stock mo){
		String sql="UPDATE STOCK SET QUANTITY=? "
				+  "  WHERE STOCK_NO =?";
		System.out.println("1>"+mo.getStock_no());
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(mo.getQuantity()));
			//ps.setString(2, mo.getExpiredDate());
			ps.setString(2, mo.getMaterial_Code());
			ps.setString(3, mo.getStock_no());
			ps.executeUpdate();
			System.out.println("2>"+mo.getQuantity());
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public Stock selectStock(String stockNum) throws Exception{
	Stock sk = new Stock();
	String sql = "SELECT * FROM STOCK WHERE STOCK_NO=?";
	//각각의 컬럼들을 STOCK객체에 저장하고 리턴
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, stockNum);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		sk.setQuantity(rs.getString("QUANTITY"));
		sk.setMaterial_Code(rs.getString("MATERIAL_CODE"));
		sk.setExpiredDate(rs.getString("EXPIRED_DATE"));
		sk.setStock_no(rs.getString("STOCK_NO"));
		sk.setAdd_date(rs.getString("ADD_DATE"));
	
	}
	
	ps.close();
	return sk;
	}


	public void delete(){
		
	}
	
	public ArrayList search() throws Exception{
		ArrayList list = new ArrayList();
		String sql = "SELECT S.STOCK_NO STOCK_NO, S.MATERIAL_CODE MATERIAL_CODE ,M.NAME NAME ,S.QUANTITY QUANTITY ,"
				+ "S.EXPIRED_DATE EXPIRED_DATE, S.ADD_DATE ADD_DATE   "
				+ "FROM MENU M INNER JOIN STOCK S  "
				+ "ON M.MENU_CODE = S.MATERIAL_CODE  "; 
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ArrayList data = new ArrayList();
			data.add(rs.getString("STOCK_NO"));
			data.add(rs.getString("MATERIAL_CODE"));
			data.add(rs.getString("NAME"));
			data.add(rs.getInt("QUANTITY"));
			data.add(rs.getString("EXPIRED_DATE"));
			data.add(rs.getString("ADD_DATE"));
			list.add(data);
		}
		ps.close();
		return list;
		
	}
	
}
