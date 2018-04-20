package Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.DBconnection;

// Create by Inho 2018. 4. 19. 오후 5:53:29

public class orderModel {
	static Connection con;
	String url;
	String user;
	String pass;
	PreparedStatement ps;
	public orderModel(){
		dbConnection();
	}
	
	public void dbConnection(){
		try {
			con = DBconnection.getConnection();
		} catch (Exception e) {
			System.out.println("StockModel DB연결 실패" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sendOrder(String[] a) throws Exception{
		// 전송할 메뉴 얻어와서 DB에 보내기..?
		// 3. SQL 문장
		String sql = "INSERT  INTO SUSHI_ORDER(ORDER_NO,CUSTOMER_NO,MENU_CODE,PAYMENT_NO,ORDERTIME)"
				+ "  VALUES(SQ_ORDER_NO.nextval,SQ_CUSTOMER_NO.nextval,?,SQ_PAYMENT_NO.nextval,SYSDATE)";
		
		// 4. SQL 전송객체
		ps = con.prepareStatement(sql);
		
		ps.setString(1,a[0]);
		
		// 5. SQL 전송
		ps.executeQuery();
		
		// 6. 닫기
		ps.close();
	}
	
	public ArrayList<ArrayList<String>> getMenucount() throws Exception{
		// SQL 문장
		String sql = "SELECT MENU_CODE,NAME,PRICE,CATEGORY,IMAGE FROM MENU";
		System.out.println(sql);
		// SQL 전송객체
		ps = con.prepareStatement(sql);
		
		// SQL 전송
		ResultSet rs = ps.executeQuery();
		ArrayList mvo = new ArrayList();
		while(rs.next()){
			ArrayList temp = new ArrayList();
			temp.add(rs.getString("MENU_CODE"));
			temp.add(rs.getString("NAME"));
			temp.add(rs.getString("PRICE"));
			temp.add(rs.getString("CATEGORY"));
			temp.add(rs.getString("IMAGE"));
			
			mvo.add(temp);
		}
		
		// 닫기
		rs.close();
		ps.close();
		
		return mvo;
	}
}