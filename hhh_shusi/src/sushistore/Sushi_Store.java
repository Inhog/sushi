package sushistore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import view.*;

// Create by Inho 2018. 4. 23. 오후 11:43:38

public class Sushi_Store{
	Connection con;		// 이부분은 model 패키지에 있는 DBconnection을 활용하면 될듯하다.
	StoreMgtView	storeMgt;
	StockView		stock;
//	Table_orderView	table_order;
//	PaymentView		payment;
	
	public Sushi_Store(){

		// 각각의 화면을 구성하는 클래스 객체 생성
		storeMgt = new StoreMgtView();
		stock	 = new StockView();				//준홍이 작업
//		table_order = new Table_orderView();	//수형 작업
//		payment = new PaymentView();			//준홍 작업
		
//		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	void initNet() throws Exception {
		
		// 1. 드라이버로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. Connection 연결객체 얻어오기
		con = DriverManager.getConnection("jdbc:oracle:thin:@70.12.115.62:1521:orcl","inhog","inhog");
		// 서버소켓 Port번호 지정.
		ServerSocket ss = new ServerSocket(10001);

		while (true) {
			Socket s = ss.accept();

			Table t = new Table(this, s, con); //내 아이피

			t.start();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Sushi_Store store = new Sushi_Store();
		store.initNet();
	}
}
