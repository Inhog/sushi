package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.StockVO;

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
	public void insert(StockVO stock){
		String sql="INSERT INTO STOCK (STOCK_NO, QUANTITY, EXPIRED_DATE, ADD_DATE,MATERIAL_CODE) "
				+ "VALUES ( SQ_STOCK_NO.NEXTVAL,        ?,            ?,  SYSDATE,            ?)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stock.getQuantity());
			ps.setString(2, stock.getExpiredDate());
			ps.setString(3, stock.getMaterialCode());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("DB연결 실패:"+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//재고 수정
	public void modify(StockVO stock){
		String sql="UPDATE STOCK SET QUANTITY=? "
				+  "  WHERE STOCK_NO =?";
		System.out.println("1>"+stock.getStockNo());
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stock.getQuantity());
			//ps.setString(2, mo.getExpiredDate());
			ps.setString(2, stock.getMaterialCode());
			ps.setString(3, stock.getStockNo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public StockVO selectStock(String stockNum) throws Exception{
	StockVO stock = new StockVO();
	String sql = "SELECT * FROM STOCK WHERE STOCK_NO=?";
	//각각의 컬럼들을 STOCK객체에 저장하고 리턴
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, stockNum);
	ResultSet rs = ps.executeQuery();
	if(rs.next()){
		stock.setQuantity(rs.getString("QUANTITY"));
		stock.setMaterialCode(rs.getString("MATERIAL_CODE"));
		stock.setExpiredDate(rs.getString("EXPIRED_DATE"));
		stock.setStockNo(rs.getString("STOCK_NO"));
		stock.setAddDate(rs.getString("ADD_DATE"));
	
	}
	
	ps.close();
	return stock;
	}


	public void delete(){
		
	}
	
	public ArrayList<ArrayList<String>> search() throws Exception{
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = "SELECT S.STOCK_NO STOCK_NO, S.MATERIAL_CODE MATERIAL_CODE ,M.NAME NAME ,S.QUANTITY QUANTITY, "
				+ "S.EXPIRED_DATE EXPIRED_DATE, S.ADD_DATE ADD_DATE   "
				+ "FROM MENU M INNER JOIN STOCK S  "
				+ "ON M.MENU_CODE = S.MATERIAL_CODE  ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			ArrayList<String> data = new ArrayList<String>();
			data.add(rs.getString("STOCK_NO"));
			data.add(rs.getString("MATERIAL_CODE"));
			data.add(rs.getString("NAME"));
			data.add(rs.getString("QUANTITY"));
			data.add(rs.getString("EXPIRED_DATE"));
			data.add(rs.getString("ADD_DATE"));
			list.add(data);
		}
		
		ps.close();
		return list;
		
	}
	
}