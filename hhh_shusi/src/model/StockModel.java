package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.PartialResultException;

import vo.MenuVO;
import vo.StockVO;

public class StockModel {
	static Connection con;
	String url;
	String user;
	String pass;

	public StockModel() {
		try {
			dbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dbConnection() throws Exception {
		con = DBconnection.getConnection();
	}

	// 재고 입력
	public void insert(StockVO stock) {
		String sql = " INSERT INTO STOCK (STOCK_NO, MATERIAL_CODE, QUANTITY, EXPIRED_DATE, ADD_DATE) "
				+ " VALUES ( SQ_STOCK_NO.NEXTVAL,            ?,         ?,            ?,  SYSDATE)";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stock.getMaterialCode());
			ps.setString(2, stock.getQuantity());
			ps.setString(3, stock.getExpiredDate());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println("DB연결 실패:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 재고 수정
	public void modify(StockVO stock) {
		String sql = "UPDATE STOCK SET QUANTITY=? " + "  WHERE STOCK_NO =?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, stock.getQuantity());
			// ps.setString(2, mo.getExpiredDate());
			ps.setString(2, stock.getStockNo());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 재고수정창이 뜨면 JTable에 입력되있는 데이터들이 재고수정 화면에 출력되는거
	public StockVO selectStock(String stockNum) throws Exception {
		StockVO stock = new StockVO();
		String sql = "SELECT * FROM STOCK WHERE STOCK_NO=?";
		// 각각의 컬럼들을 STOCK객체에 저장하고 리턴
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, stockNum);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			stock.setQuantity(rs.getString("QUANTITY"));
			stock.setMaterialCode(rs.getString("MATERIAL_CODE"));
			stock.setExpiredDate(rs.getString("EXPIRED_DATE"));
			stock.setStockNo(rs.getString("STOCK_NO"));
			stock.setAddDate(rs.getString("ADD_DATE"));
		}
		ps.close();
		return stock;
	}

	public void delete() {

	}

	// JTabled에 재고데이터를 띄우는 코드
	public ArrayList<ArrayList<String>> search() throws Exception {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		String sql = " SELECT S.STOCK_NO STOCK_NO, S.MATERIAL_CODE MATERIAL_CODE ,M.NAME NAME ,S.QUANTITY QUANTITY, "
				   + " S.EXPIRED_DATE EXPIRED_DATE, S.ADD_DATE ADD_DATE   " + "FROM MENU M INNER JOIN STOCK S  "
				   + " ON M.MENU_CODE = S.MATERIAL_CODE  ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ArrayList<String> data = new ArrayList<String>();
			data.add(rs.getString("STOCK_NO"));
			data.add(rs.getString("MATERIAL_CODE"));
			data.add(rs.getString("NAME"));
			data.add(rs.getString("QUANTITY"));
			data.add(rs.getString("ADD_DATE"));
			data.add(rs.getString("EXPIRED_DATE"));
			list.add(data);
		}
		ps.close();
		return list;
	}

	public ArrayList<String> menuCode(String cate) throws Exception {
		ArrayList<String> list = new ArrayList<>();
		String sql = "SELECT MENU_CODE  FROM MENU WHERE Category=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(rs.getString("MENU_CODE"));
		}
		return list;
	}

	public ArrayList searchData(String data) {
		//재고번호,재고코드,자재명,수량,입고일,유통기한이 담긴 데이터를 ArrayList에 담는다
		ArrayList list = new ArrayList<>();
		String sql = " SELECT S.STOCK_NO STOCK_NO, S.MATERIAL_CODE MATERIAL_CODE, M.NAME NAME, S.QUANTITY QUANTITY,"
			      +  " S.ADD_DATE ADD_DATE, S.EXPIRED_DATE EXPIRED_DATE" + " FROM MENU M INNER JOIN STOCK S"
				  +  " ON S.MATERIAL_CODE = M.MENU_CODE"
			      //자재명에 입력하여서 해당 자재명만 검색/ 유통기한이 적게 남은 데이터별로 정렬
				  +  " WHERE NAME LIKE '%" + data + "%' ORDER BY EXPIRED_DATE ASC";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//ArrayList의 재고번호,재고코드,자재명,수량,입고일,유통기한 입력
				ArrayList temp = new ArrayList();
				temp.add(rs.getString("STOCK_NO"));
				temp.add(rs.getString("MATERIAL_CODE"));
				temp.add(rs.getString("NAME"));
				temp.add(rs.getString("QUANTITY"));
				temp.add(rs.getString("ADD_DATE"));
				temp.add(rs.getString("EXPIRED_DATE"));
				list.add(temp);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}