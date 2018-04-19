package Customer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class orderView extends JFrame implements ActionListener{
	
	JPanel NorthPane,CenterPane,Tablepanel,TablePanel_South,sendPanel,TablePanel_Center,TablePanel_North,deletePanel;
	JLabel lblBrandName,lblOrderList;
	
	JTabbedPane	MenuTab;
	JScrollPane	scrollPane_1,scrollPane_2,scrollPane_3;
	JButton[] sushi,dish,drink;
	JButton	bSendorder,bDeleteorder;
	JPanel sushiPane,dishPane,drinkPane;
	
	String[] b;

	DefaultTableModel tableModel;
	JTable		tableMenu;			
	
	int orderCount=1;
//	orderModel 만들면 그때 생성함.
//	orderModel	Model;
	
//	나중에 String 배열에 있는 내용을 DB에서 가져올 것임.
	String[] StrSushiName = {"소라 초밥","참치 초밥","소고기 초밥","장어 초밥","계란 초밥","광어 초밥","간장새우 초밥","생새우 초밥","연어 초밥"};
	int[] cntSushi = {0,0,0,0,0,0,0,0,0};
	//	MenuTableModel에 컬럼이름
	String [] columnNames = {"번호","주문명","수량"};			
	String[][] a= {{"0","0","0"}};
	public orderView() {
		addLayout();
		eventProc();
		connectDB();
		setVisible(true);
		setSize(500,600);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}
	// 우선 프레임 구성만 한 상태 - 18.04.18 
	// 내부 메서드와 Model 클래스를 만들어서 객체 생성해야함.
	public void addLayout(){
		tableModel = new DefaultTableModel(a,columnNames);
		tableMenu = new JTable(tableModel);
//		tableMenu = new JTable(a,columnNames);
		NorthPane = new JPanel();
		CenterPane = new JPanel();
		Tablepanel = new JPanel();
		TablePanel_South = new JPanel();
		sendPanel = new JPanel();
		deletePanel = new JPanel();
		TablePanel_North = new JPanel();
		FlowLayout flowLayout = (FlowLayout) TablePanel_North.getLayout();
		TablePanel_Center = new JPanel();
		sushiPane = new JPanel();
		dishPane = new JPanel();
		drinkPane = new JPanel();
		sushiPane.setLayout(new GridLayout(3, 3, 0, 0));
		dishPane.setLayout(new GridLayout(3, 3, 0, 0));
		drinkPane.setLayout(new GridLayout(3, 3, 0, 0));
		sushi = new JButton[9];
		dish = new JButton[9];
		drink = new JButton[9];
		
//		sushiPane.add(new JButton("연어 초밥"));
//		sushiPane.add(new JButton("생새우 초밥"));
//		sushiPane.add(new JButton("간장새우 초밥"));
//		sushiPane.add(new JButton("광어 초밥"));
//		sushiPane.add(new JButton("계란 초밥"));
//		sushiPane.add(new JButton("장어 초밥"));
//		sushiPane.add(new JButton("소고기 초밥"));
//		sushiPane.add(new JButton("참치 초밥"));
//		sushiPane.add(new JButton("소라 초밥"));
		
		for(int i=0;i<9;i++){
			sushi[i] = new JButton(StrSushiName[i]);
		}
		
		for(int i=0;i<9;i++){
			sushiPane.add(sushi[i]);
//			dishPane.add(dish[i]);
//			drinkPane.add(drink[i]);
		}

		
		scrollPane_1 = new JScrollPane(sushiPane);
		scrollPane_2 = new JScrollPane(dishPane);
		scrollPane_3 = new JScrollPane(drinkPane);
		
		lblBrandName = new JLabel("ㅎㅎㅎ_Sushi");
		lblOrderList = new JLabel("ㅎㅎㅎSushi 주문내역");

		bSendorder = new JButton("주문전송");
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
		TablePanel_South.add(sendPanel);
		sendPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		sendPanel.add(bSendorder);
		TablePanel_South.add(deletePanel);
		deletePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		deletePanel.add(bDeleteorder);
		Tablepanel.add(TablePanel_North, BorderLayout.NORTH);
		TablePanel_North.add(lblOrderList);
		Tablepanel.add(TablePanel_Center, BorderLayout.CENTER);
		TablePanel_Center.setLayout(new BorderLayout(0, 0));
		TablePanel_Center.add(new JScrollPane(tableMenu),BorderLayout.CENTER);
				
		}
	
//		//화면에 테이블 붙이는 메소드 
//		class MenuTableModel extends AbstractTableModel { 
//			  
//			ArrayList data = new ArrayList();
//			String [] columnNames = {"번호","주문명","수량"};
//	
//			//=============================================================
//			// 1. 기본적인 TabelModel  만들기
//			// 아래 세 함수는 TabelModel 인터페이스의 추상함수인데
//			// AbstractTabelModel에서 구현되지 않았기에...
//			// 반드시 사용자 구현 필수!!!!
//	
//		    public int getColumnCount() { 
//		        return columnNames.length; 
//		    } 
//		     
//		    public int getRowCount() { 
//		        return data.size(); 
//		    } 
//
//		    public Object getValueAt(int row, int col) { 
//				ArrayList temp = (ArrayList)data.get( row );
//		        return temp.get( col ); 
//		    }
//		    
//		    public String getColumnName(int col){
//		    	return columnNames[col];
//		    }
//		}
	public void eventProc(){
		bDeleteorder.addActionListener(this);
		bSendorder.addActionListener(this);
		// 메뉴마다 ActionListener 등록.
		for(int i=0;i<9;i++)
		sushi[i].addActionListener(this);
	}
	
	public void connectDB(){
		try{
//			Model = new orderModel();
			System.out.println("고객주문화면 디비연결 성공");
		}catch(Exception ex){
			System.out.println("고객주문화면 연결실패 :" +ex.getMessage());
		}
		
	}
	public void addMenu(int no){
		String[] b= {String.valueOf(tableModel.getRowCount()),StrSushiName[no],String.valueOf(cntSushi[no])};
//		테이블에 값이 있으면
		if(tableModel.getRowCount() != 0){
			// 테이블의 길이만큼 탐색하여 같은 메뉴의 이름이 있는지 확인
			for(int i=0;i<tableModel.getRowCount();i++){
				if(tableModel.getValueAt(i, 1) == StrSushiName[no]){
					// 같은 메뉴의 이름이 있다면 메뉴번호 그대로, 수량 +1으로 수정한다.
					// 수정한 후 반복문을 빠져나온다.
					tableModel.setValueAt(String.valueOf(++cntSushi[no]), i, 2);
					tableModel.setValueAt(tableModel.getValueAt(i, 0), i, 0);
					break;
				}
				//끝까지 다 돌았는데 없으면 메뉴번호는 순서대로, 수량은 1으로 추가한다.
				if(i == tableModel.getRowCount()-1){
					tableModel.addRow(b);
				}
			}
		}else{
			// 테이블에 아예 값이 없는 상황이라면
			// 해당 메뉴를 메뉴번호는 1, 수량도 1로 추가한다.
			tableModel.addRow(b);
		}
	}
	public void deleteMenu(int row){
//		model.deleteMenu();
//		삭제한row에 해당하는 초밥의 개수를 0으로 초기화
		for(int i=0;i<9;i++){		// 9 는 초밥갯수
		if(tableModel.getValueAt(row, 1) == StrSushiName[i]){
			cntSushi[i] =0;
			}
		}
//		tableModel의 해당 row 삭제
		tableModel.removeRow(row);
		
		// 테이블의 값이 삭제된 후 메뉴번호를 삭제된 컬럼부터 1씩 줄여준다.
		for(int i=row;i<tableModel.getRowCount();i++){
			tableModel.setValueAt(String.valueOf(i), i, 0);
		}
	}
	public void sendOrder(){
//		model.sendOrder();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bDeleteorder){
			deleteMenu(tableMenu.getSelectedRow());
		}else if(evt == bSendorder){
			sendOrder();
		}else if(evt == sushi[0]){
			addMenu(0);
		}else if(evt == sushi[1]){
			addMenu(1);
		}else if(evt == sushi[2]){
			addMenu(2);
		}else if(evt == sushi[3]){
			addMenu(3);
		}else if(evt == sushi[4]){
			addMenu(4);
		}else if(evt == sushi[5]){
			addMenu(5);
		}else if(evt == sushi[6]){
			addMenu(6);
		}else if(evt == sushi[7]){
			addMenu(7);
		}else if(evt == sushi[8]){
			addMenu(8);
		}
	}
	
	public static void main(String[] args) {
		new orderView();
	}
	
}
