package sushistore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import view.*;

public class Sushi_Store{
	StoreMgtView	storeMgt;
	StockView		stock;
//	Table_orderView	table_order;
//	PaymentView		payment;
	
//	Sushi_StoreModel클래스 만들면 생성해서 DB와 연결하는 객체
//	Sushi_StoreModel model;
//	여기에 만드는거 아닌거 같음.
	
	public Sushi_Store(){

		// 각각의 화면을 구성하는 클래스 객체 생성
		storeMgt = new StoreMgtView();
		stock	 = new StockView();			//준홍이 작업
//		table_order = new Table_orderView();	//수형 작업
//		payment = new PaymentView();		//준홍 작업
		
//		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	
	public static void main(String[] args) {
		new Sushi_Store();
	}
}
