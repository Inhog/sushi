package sushistore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import view.*;

public class Sushi_Store{
	Connection con;
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
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		con = DriverManager.getConnection("jdbc:oracle:thin:@m150112:1521:orcl","inhog","inhog");

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
