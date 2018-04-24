package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.MenuVO;
import vo.OrderVO;

public class OrderModel {

	static Connection 	con;
	PreparedStatement 	ps = null;
	ResultSet 			rs = null;
	
	public OrderModel(){
		try {
			dbConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dbConnection()throws Exception{
			con = DBconnection.getConnection();
	}
	//주문 입력
	public void addOrder(OrderVO order){
		String sql="INSERT INTO SUSHI_ORDER (ORDER_NO, CUSTOMER_NO, MENU_CODE, PAYMENT_NO, ORDERTIME ) "
				+ "VALUES ( SQ_ORDER_NO.NEXTVAL,      			  ?,        ?, 		    ?,   SYSDATE ) ";
		
		PreparedStatement ps;
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, order.getCustomerNo());
			ps.setString(2, order.getMenuCode());
			ps.setString(3, order.getPaymentNo());
			ps.executeUpdate();
			
			ps.close();
			
		} catch (SQLException e) {
			System.out.println("주문 추가 실패 : "+ e.getMessage());
			e.printStackTrace();
		}
	}
	
	//해당 컬럼명의 키 값으로 검색된 결과값을 arrayList로 돌려줌 ( inner join으로 메뉴명 함께 가져옴)
	public ArrayList<OrderVO> searchByKey(String key) throws Exception{

		ArrayList<OrderVO> orderVOList = new ArrayList<OrderVO>();
		
		String sql = "SELECT 	O.ORDER_NO ORDER_NO, O.CUSTOMER_NO CUSTOMER_NO, "
				+ "				O.MENU_CODE MENU_CODE, O.PAYMENT_NO PAYMENT_NO, "
				+ "				O.ORDERTIME ORDERTIME, M.NAME NAME "
				+ "	  FROM 	 	SUSHI_ORDER O INNER JOIN MENU M "
				+ "				ON O.MENU_CODE = M.MENU_CODE "
				+ "	  WHERE		O.CUSTOMER_NO = ?  ";
		
		ps =  con.prepareStatement(sql);
		ps.setString(1,  key);	//검색할 키 값
		
		rs =  ps.executeQuery();

		while(rs.next()){
			OrderVO order = new OrderVO();
			order.setOrderNo(rs.getString("ORDER_NO"));
			order.setCustomerNo(rs.getString("CUSTOMER_NO"));
			order.setMenuCode(rs.getString("MENU_CODE"));
			order.setPaymentNo(rs.getString("PAYMENT_NO"));
			order.setOrdertime(rs.getString("ORDERTIME"));
			order.setMenuName(rs.getString("NAME"));
		
			orderVOList.add(order);
		}
		
		ps.close();
		
		
		return orderVOList;
	}
	
	//CustomerModel
	//테이블 넘버를 기준으로 가장 최근의 고객번호를 검색하여 리턴
	public String getCustomerNo(String tableNo) throws Exception{	
		
		String customerNo = null;
		//가장 최근의 해당 테이블의 customerNo를 찾아옴
		String sql = "SELECT 	CUSTOMER_NO, TABLE_NO "
				+ "	  FROM 	 	CUSTOMER "
				+ "	  WHERE		TABLE_NO = ? and rownum = 1 "
				+ "   ORDER BY 	CUSTOMER_NO DESC ";
		
		ps =  con.prepareStatement(sql);
		ps.setString(1,  tableNo);
		
		rs =  ps.executeQuery();

		while(rs.next()){
			customerNo = rs.getString("CUSTOMER_NO");
		}
		ps.close();
		
		return customerNo;
	}
	
}
