package Customer;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Create by Inho 2018. 4. 21. 오후 4:56:44
/**
 * 18.04.21 수정내용
 *	1. 버튼에 이미지 추가
 *	2. DB와 연결된 메뉴갯수 만큼 버튼생성 및 이벤트 등록 메서드 구현완료
 *	3. 코드 보기좋게 바꿈ㅎㅎ...
 *
 *	보완해야할 내용
 *	1. sendOrder 메서드의 값들을 확인해보아야 함.
 *	2. 다른 클래스와 연동확인 필요.
 *
 */

public class orderView extends JFrame implements ActionListener{
	
	//	orderView()
	ImageIcon[] sushiIcon,mealIcon,drinkIcon;
	JPanel NorthPane,CenterPane,Tablepanel,TablePanel_South,sendPanel,TablePanel_Center,TablePanel_North,deletePanel;
	JLabel lblBrandName,lblOrderList;
	JTabbedPane	MenuTab;
	JScrollPane	scrollPane_1,scrollPane_2,scrollPane_3;
	JButton[] sushi,meal,drink;
	JButton	bSendorder,bDeleteorder;
	JPanel sushiPane,mealPane,drinkPane;
	String [] columnNames = {"번호","주문명","수량"};

	//	addMenu
	String[] addorder;
	DefaultTableModel tableModel;
	JTable		tableMenu;			
	int[] cntMenu;

	//	sendOrder
	orderModel	Model;
	
	//	getMenuInfo
	MenuVO[] MenuInfo;
	ArrayList<MenuVO> sushiList,mealList,drinkList;
	
	public orderView() {		// 생성자
		connectDB();
		getMenuInfo();
		addLayout();
		eventProc();
		setVisible(true);
		setSize(500,600);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	
	public void addLayout(){		// 화면 구성 메서드
		tableModel = new DefaultTableModel(null,columnNames);
		tableMenu = new JTable(tableModel);
		NorthPane = new JPanel();
		CenterPane = new JPanel();
		Tablepanel = new JPanel();
		TablePanel_South = new JPanel();
		sendPanel = new JPanel();
		deletePanel = new JPanel();
		TablePanel_North = new JPanel();
		TablePanel_North.setLayout(new FlowLayout());
		TablePanel_Center = new JPanel();
		sushiPane = new JPanel();
		mealPane = new JPanel();
		drinkPane = new JPanel();
		sushiPane.setLayout(new GridLayout(3, 3));
		mealPane.setLayout(new GridLayout(3, 3, 0, 0));
		drinkPane.setLayout(new GridLayout(3, 3, 0, 0));
		// 화면을 구성할 때 DB에서 메뉴테이블의 갯수를 받아와서 화면구성을 해야함.
				
		sushi = new JButton[sushiList.size()];
		meal = new JButton[mealList.size()];
		drink = new JButton[drinkList.size()];
		
		cntMenu = new int[MenuInfo.length];
		// 여까지 했는데 생객해보니 갯수만가져올게 아니라 메뉴 객체로 메뉴코드 등 모든컬럼에 대한 내용을 받아와야 함.
		
		for(int i=0;i<MenuInfo.length;i++){
			cntMenu[i] = 1;
		}
		
		// 버튼객체에 Text와 Image 삽입
		for(int i = 0; i<sushiList.size();i++){
			sushi[i] = new JButton(sushiList.get(i).getName(),new ImageIcon(sushiList.get(i).getImageloc()));
		}
		for(int i = 0; i<drinkList.size();i++){
			drink[i] = new JButton(drinkList.get(i).getName(),new ImageIcon(drinkList.get(i).getImageloc()));
		}
		for(int i = 0; i<mealList.size();i++){
			meal[i] = new JButton(mealList.get(i).getName(),new ImageIcon(mealList.get(i).getImageloc()));
		}
		//TabPane에 버튼 삽입
		for(int i=0;i<sushi.length;i++){
			sushiPane.add(sushi[i]);
		}
		for(int i=0;i<meal.length;i++){
			mealPane.add(meal[i]);
		}
		for(int i=0;i<drink.length;i++){
			drinkPane.add(drink[i]);
		}

		
		scrollPane_1 = new JScrollPane(sushiPane);
		scrollPane_2 = new JScrollPane(mealPane);
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
	
	public void getMenuInfo(){	// DB에서 메뉴테이블의 갯수를 가져오는 메서드
		try {
			ArrayList<ArrayList<String>> mvo =Model.getMenucount();
			MenuInfo = new MenuVO[mvo.size()];
			sushiList = new ArrayList<MenuVO>();
			mealList = new ArrayList<MenuVO>();
			drinkList = new ArrayList<MenuVO>();
			for(int i=0;i<mvo.size();i++){
				MenuInfo[i] = new MenuVO();
				MenuInfo[i].setMenuCode(mvo.get(i).get(0));
				MenuInfo[i].setName(mvo.get(i).get(1));
				MenuInfo[i].setPrice(mvo.get(i).get(2));
				MenuInfo[i].setCategory(mvo.get(i).get(3));
				MenuInfo[i].setImageloc(mvo.get(i).get(4));
				//여기서 가져올 때 아예 카테고리 별로 데이터를 넣고,(초밥류,식사류,주류) 각 객체의 크기만큼 메모리를 할당한다.
				// ArrayList로 데이터를 받음.
				if(mvo.get(i).get(3).equals("초밥류")){
					sushiList.add(MenuInfo[i]);
				}else if(mvo.get(i).get(3).equals("식사류")){
					mealList.add(MenuInfo[i]);
				}else if(mvo.get(i).get(3).equals("주류")){
					drinkList.add(MenuInfo[i]);
				}
			}
		}catch (Exception e) {
			System.out.println("메뉴 데이터 가져오기 실패 :" + e.getMessage());
		}
	}
	
	public void eventProc(){	// 이벤트 등록 메서드
		bDeleteorder.addActionListener(this);
		bSendorder.addActionListener(this);
		// 메뉴마다 ActionListener 등록.
		for(int i=0;i<sushi.length;i++){
			sushi[i].addActionListener(this);
		}
		for(int i=0;i<meal.length;i++){
			meal[i].addActionListener(this);
		}
		for(int i=0;i<drink.length;i++){
			drink[i].addActionListener(this);
		}
	}
	
	public void connectDB(){	// DB 연결 메서드
		try{
			Model = new orderModel();
			System.out.println("고객주문화면 디비연결 성공");
		}catch(Exception ex){
			System.out.println("고객주문화면 연결실패 :" +ex.getMessage());
		}
		
	}
	
	public void addMenu(int no){	// 왼쪽 버튼을 누르면 메뉴테이블에 메뉴를 추가하는 메서드
		String[] addorder= {String.valueOf(tableModel.getRowCount()+1),MenuInfo[no].getName(),String.valueOf(cntMenu[no])};
//		테이블에 값이 있으면
		if(tableModel.getRowCount() != 0){
			// 테이블의 길이만큼 탐색하여 같은 메뉴의 이름이 있는지 확인
			for(int i=0;i<tableModel.getRowCount();i++){
				if(tableModel.getValueAt(i, 1) == MenuInfo[no].getName()){
					// 같은 메뉴의 이름이 있다면 메뉴번호 그대로, 수량 +1으로 수정한다.
					// 수정한 후 반복문을 빠져나온다.
					tableModel.setValueAt(String.valueOf(cntMenu[no]++), i, 2);
					tableModel.setValueAt(tableModel.getValueAt(i, 0), i, 0);
					break;
				}
				//끝까지 다 돌았는데 없으면 메뉴번호는 순서대로, 수량은 1으로 추가한다.
				if(i == tableModel.getRowCount()-1){
					tableModel.addRow(addorder);
				}
			}
		}else{
			// 테이블에 아예 값이 없는 상황이라면
			// 해당 메뉴를 메뉴번호는 1, 수량도 1로 추가한다.
			tableModel.addRow(addorder);
		}
	}
	
	public void deleteMenu(int row){	// 메뉴테이블에 있는 데이터를 삭제하고 수량을 초기화하는 메서드
//		삭제한row에 해당하는 초밥의 개수를 0으로 초기화
		for(int i=0;i<MenuInfo.length;i++){		// 메뉴의 총개수만큼 반복
		if(tableModel.getValueAt(row, 1) == MenuInfo[i].getName()){
			cntMenu[i] =1;
			}
		}
//		tableModel의 해당 row 삭제
		tableModel.removeRow(row);
		
		// 테이블의 값이 삭제된 후 메뉴번호를 삭제된 컬럼부터 1씩 줄여준다.
		for(int i=row;i<tableModel.getRowCount();i++){
			tableModel.setValueAt(String.valueOf(i+1), i, 0);
		}
	}
	
	
	public void sendOrder(DefaultTableModel tableModel){	// DB에 연결한 테이블에 고객이 주문한 내역을 전송하는 메서드
		// 버튼을 누르면 String[]으로  값을 하나 하나 받아서 model.sendOrder()로 보내준다.
		// 우선 테이블안에 값을 받을 객체 선언.
		// 테이블의 데이터 수 만큼 반복
		for(int i=0;i<tableModel.getRowCount();i++){
			String[] a = {(String) tableModel.getValueAt(i, 0),(String) tableModel.getValueAt(i, 1),(String) tableModel.getValueAt(i, 2)};
			try {
				Model.sendOrder(a);
			} catch (Exception e) {
				System.out.println("주문 전솔실패 : " + e.getMessage());
			}
		}
		for(int i=0;i<tableModel.getRowCount();){
			tableModel.removeRow(i);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	// Action이 발생하는 객체마다 실행될 명령을 선언하는 메서드
		Object evt = e.getSource();
		if(evt == bDeleteorder){
			deleteMenu(tableMenu.getSelectedRow());
		}else if(evt == bSendorder){
			sendOrder(tableModel);
		}else{
			int j =0;
			for(int i =0;i<sushiList.size();i++){
				if(evt == sushi[i])
					addMenu(j);
					j++;
			}
			for(int i =0;i<mealList.size();i++){
				if(evt == meal[i])
				addMenu(j);
				j++;
			}
			for(int i=0;i<drinkList.size();i++){
				if(evt == drink[i])
				addMenu(j);
				j++;
			}
		}
	}
	
	public static void main(String[] args) {
		new orderView();
	}
	
}
