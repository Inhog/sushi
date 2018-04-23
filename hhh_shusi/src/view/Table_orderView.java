package view;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Customer.orderView;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.GroupLayout.Alignment;

public class Table_orderView  extends JFrame implements ActionListener{
	
	JPanel NorthPane,CenterPane,Tablepanel,TablePanel_South,paymentPanel,TablePanel_Center,TablePanel_North,deletePanel;
	JLabel lblBrandName,lblOrderList;
	
	JTabbedPane	MenuTab;
	JScrollPane	scrollPane_1,scrollPane_2,scrollPane_3;
	JButton[] sushi,dish,drink;
	JButton	bPayment,bDeleteorder;
	JPanel sushiPane,dishPane,drinkPane;
	
	String[] b;

	DefaultTableModel tableModel;
	JTable		tableMenu;			
	
	int orderCount=1;
//	orderModel 만들면 그때 생성함.
//	orderModel	Model;
	
//	나중에 String 배열에 있는 내용을 DB에서 가져올 것임.
//	String[] StrSushiName = {"소라 초밥","참치 초밥","소고기 초밥","장어 초밥","계란 초밥","광어 초밥","간장새우 초밥","생새우 초밥","연어 초밥","테스트"};
	String[] StrSushiName = {"소라 초밥","참치 초밥","소고기 초밥","장어 초밥","계란 초밥"};
	int[] cntSushi = {0,0,0,0,0,0,0,0,0};
	//	MenuTableModel에 컬럼이름
	String [] columnNames = {"번호","주문명","수량"};			
	String[][] a= {{"0","0","0"}};

	
	public Table_orderView() {
		addLayout();
		eventProc();
		connectDB();
		setVisible(true);
		setSize(800,600);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}
	// 우선 프레임 구성만 한 상태 - 18.04.18 
	// 내부 메서드와 Model 클래스를 만들어서 객체 생성해야함.
	public void addLayout(){
		tableModel = new DefaultTableModel(a,columnNames);
		tableMenu = new JTable(tableModel);
		NorthPane = new JPanel();
		CenterPane = new JPanel();
		Tablepanel = new JPanel();
		TablePanel_South = new JPanel();
		paymentPanel = new JPanel();
		deletePanel = new JPanel();
		TablePanel_North = new JPanel();
		
		
		FlowLayout flowLayout = (FlowLayout) TablePanel_North.getLayout();
		
		TablePanel_Center = new JPanel();
		sushiPane = new JPanel();
		dishPane = new JPanel();
		drinkPane = new JPanel();
		
		/*menuDB에서 총 보여줄 메뉴들이 몇 개 인지 파악하여 버튼의 갯수 및 레이아웃을 결정*/
		
		sushiPane.setPreferredSize(new Dimension(10,10));
		
		int sushiCount, dishCount, drinkCount;
		sushiPane.setLayout(new FlowLayout());
//		sushiPane.setLayout(new FlowLayout());
		dishPane.setLayout(new GridLayout(3, 3, 5, 5));
		drinkPane.setLayout(new GridLayout(3, 3, 5, 5));
		sushi = new JButton[10];
		dish = new JButton[9];
		drink = new JButton[9];
		
		for(int i=0;i<StrSushiName.length;i++){
			sushi[i] = new JButton(StrSushiName[i]);
		}
		

		sushi[0].setPreferredSize(new Dimension(150, 150));
		
		for(int i=0;i<StrSushiName.length;i++){
			sushiPane.add(sushi[i]);
//			dishPane.add(dish[i]);
//			drinkPane.add(drink[i]);
		}

		
		scrollPane_1 = new JScrollPane(sushiPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2 = new JScrollPane(dishPane);
		scrollPane_3 = new JScrollPane(drinkPane);
		
		;

		lblBrandName = new JLabel("ㅎㅎㅎ_Sushi");
		
		/*	테이블 번호 보여주기 	*/
		lblOrderList = new JLabel("##번 테이블 주문내역");

		bPayment = new JButton("결제");
		bDeleteorder = new JButton("품목삭제");

		
		MenuTab = new JTabbedPane(JTabbedPane.TOP);

		getContentPane().add(NorthPane, BorderLayout.NORTH);
		getContentPane().add(CenterPane, BorderLayout.CENTER);
		NorthPane.setLayout(new GridLayout(0, 1, 0, 0));

		NorthPane.add(lblBrandName);
		
		CenterPane.setLayout(new GridLayout(1, 2, 0, 0));
		
		CenterPane.add(MenuTab);
		
		MenuTab.addTab("스시류", scrollPane_1);
		MenuTab.addTab("식사류", scrollPane_2);
		MenuTab.addTab("주류", scrollPane_3);
		
		
		CenterPane.add(Tablepanel);
		Tablepanel.setLayout(new BorderLayout(0, 0));
		Tablepanel.add(TablePanel_South, BorderLayout.SOUTH);
		
		TablePanel_South.setLayout(new GridLayout(0, 2, 0, 0));
		TablePanel_South.add(paymentPanel);
		paymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		paymentPanel.add(bPayment);
		TablePanel_South.add(deletePanel);
		deletePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		deletePanel.add(bDeleteorder);
		Tablepanel.add(TablePanel_North, BorderLayout.NORTH);
		TablePanel_North.add(lblOrderList);
		Tablepanel.add(TablePanel_Center, BorderLayout.CENTER);
		TablePanel_Center.setLayout(new BorderLayout(0, 0));
		TablePanel_Center.add(new JScrollPane(tableMenu),BorderLayout.CENTER);
		


		
		
		}
	
	public void eventProc(){

	}
	
	public void connectDB(){
		try{
//			Model = new stockModel();
			System.out.println("점원테이블화면 디비연결 성공");
		}catch(Exception ex){
			System.out.println("점원테이블화면 연결실패 :" + ex.getMessage());
		}
		
	}
	public void addMenu(int no){
	
	}
	public void deleteMenu(int row){
//	
	}
	public void payment(){
//		model.payment();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}
	
	public static void main(String[] args) {
		new Table_orderView();
	}
}
