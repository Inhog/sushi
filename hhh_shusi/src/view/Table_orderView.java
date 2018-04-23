package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.MenuModel;
import model.OrderModel;
import vo.MenuVO;
import vo.OrderVO;

public class Table_orderView extends JFrame implements ActionListener {
	final int MENU_ROW_SIZE = 4;
	final int MENU_COL_SIZE = 3;
	final int DEFAULT_MENU_PANEL_SIZE = MENU_ROW_SIZE * MENU_COL_SIZE ;
	
	private String tableNo;
	private String customerNo;
	
	JPanel NorthPane, CenterPane, Tablepanel, TablePanel_South, paymentPanel, TablePanel_Center, TablePanel_North,
			deletePanel;
	JLabel lblBrandName, lblOrderList;

	JTabbedPane MenuTab;
	JScrollPane scrollPane_1, scrollPane_2, scrollPane_3, scrollPaneOrderTable, scrollPaneOrderHistoryTable;
	JButton[] sushiBtn, dishBtn, drinkBtn;
	JButton paymentBtn, addBtn, deleteBtn;
	JPanel sushiPanel, dishPanel, drinkPanel;

	OrderTableModel tableModel, orderHistoryTableModel;
	JTable tableMenu, orderHistoryTable;

	MenuModel model;
	OrderModel orderModel;
	
	// 전체 메뉴리스트를 담아두는 변수
	ArrayList<ArrayList<MenuVO>> wholeMenuList;

	// 주문할 메뉴 리스트를 담아두는 변수
	ArrayList<MenuVO> orderList;
	
	// 주문할 메뉴 리스트를 중복을 제거하여 표에 표시하기 위한 hashMap
	HashMap<MenuVO, Integer> orderListHashMap;	
	

	{
		// 창크기 고정 180421 1332
		setResizable(false);
		dbConnection();

		// 전체 메뉴리스트를 db에서 불러오기
		wholeMenuList = getMenuList();

		//orderList 초기화
		orderList = new ArrayList<MenuVO>();
		orderListHashMap = new HashMap<MenuVO, Integer>();
		
		addLayout();
		eventProc();

		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Table_orderView() {
	}

	//sushi_store에서 테이블 클릭시, 테이블 번호와 함께 전달, Char(2)
	public Table_orderView(String tableNo){
		
		this.tableNo = tableNo;
	}
	
	void dbConnection() {
		model = new MenuModel();
		orderModel = new OrderModel();
	}

	// 카테고리 사용을 위한 menuCategory 선언
	enum MenuCategory {
		SUSHI("초밥류", 0), DISH("식사류", 1), DRINK("주류", 2);

		final String name;
		final int    num;

		MenuCategory(String name, int num) {
			this.name = name;
			this.num  = num;
		}
		public String getCategory() {
			return name;
		}
		public int getOrder() {
			return num;
		}
	}


	// 전체 메뉴리스트를 db에서 가져옴. => 메뉴 버튼들을 만들기 위함.
	// wholeMenuList는 각 탭별 메뉴를 저장하는 ArrayList를 저장
	public ArrayList<ArrayList<MenuVO>> getMenuList() {

		wholeMenuList = new ArrayList<ArrayList<MenuVO>>();
		ArrayList<MenuVO> menuSushiList = new ArrayList<MenuVO>();
		ArrayList<MenuVO> menuDishList = new ArrayList<MenuVO>();
		ArrayList<MenuVO> menuDrinkList = new ArrayList<MenuVO>();

		// enum MenuCategory를 이용하여, 실제 카테고리의 한글명(스시류, 식사류 등)을 매개변수로 전달.
		try {
			
			menuSushiList = model.searchByCategory(MenuCategory.SUSHI.getCategory());
			menuDishList = model.searchByCategory(MenuCategory.DISH.getCategory());
			menuDrinkList = model.searchByCategory(MenuCategory.DRINK.getCategory());

			// 받은 각 메뉴리스트를 wholeMenuList에 넣기
			wholeMenuList.add(menuSushiList);
			wholeMenuList.add(menuDishList);
			wholeMenuList.add(menuDrinkList);

		} catch (Exception e) {
			System.out.println("주문패널을 위한 버튼 생성 실패 : " + e.getMessage());
			e.printStackTrace();
		}

		return wholeMenuList;
	}

	// 우선 프레임 구성만 한 상태 - 18.04.18
	// 내부 메서드와 Model 클래스를 만들어서 객체 생성해야함.
	public void addLayout() {

		//신규 추가 시 메뉴 표시를 위한 테이블
		tableModel = new OrderTableModel();
		tableMenu = new JTable(tableModel);
		
		//전체 주문내역을 표시하기 위한 테이블
		orderHistoryTableModel = new OrderTableModel();
		orderHistoryTable = new JTable(orderHistoryTableModel);
				
		NorthPane = new JPanel();
		CenterPane = new JPanel();
		Tablepanel = new JPanel();
		TablePanel_South = new JPanel();
		paymentPanel = new JPanel();
		deletePanel = new JPanel();
		TablePanel_North = new JPanel();

		FlowLayout flowLayout = (FlowLayout) TablePanel_North.getLayout();

		TablePanel_Center = new JPanel();
		sushiPanel = new JPanel();
		dishPanel = new JPanel();
		drinkPanel = new JPanel();

//		***************** 카테고리별 메뉴 버튼 생성  ********************
		/**
		  * Created by clap on 2018. 4. 23. 오후 12:14:43
		  */
		
		// 각 카테고리별 메뉴의 총 갯수 구하기
		int sushiMenuCount, dishMenuCount, drinkMenuCount;
		
		sushiMenuCount = wholeMenuList.get(MenuCategory.SUSHI.getOrder()).size(); // 초밥류
		dishMenuCount = wholeMenuList.get(MenuCategory.DISH.getOrder()).size(); // 식사류
		drinkMenuCount = wholeMenuList.get(MenuCategory.DRINK.getOrder()).size(); // 주류

		int sushiMenuRowsize = (sushiMenuCount / 3) + 1 < MENU_ROW_SIZE ? MENU_ROW_SIZE : (sushiMenuCount / 3) + 1 ;
		int dishMenuRowsize  = (dishMenuCount / 3) + 1  < MENU_ROW_SIZE ? MENU_ROW_SIZE : ( dishMenuCount / 3) + 1 ;
		int drinkMenuRowsize = (drinkMenuCount / 3) + 1 < MENU_ROW_SIZE ? MENU_ROW_SIZE : (drinkMenuCount / 3) + 1 ;
		
//		sushiPanel.setLayout(new FlowLayout());
//		dishPanel.setLayout(new FlowLayout());
//		drinkPanel.setLayout(new FlowLayout());
		sushiPanel.setLayout(new GridLayout(sushiMenuRowsize,MENU_COL_SIZE,0,0));
		dishPanel.setLayout(new GridLayout(dishMenuRowsize,MENU_COL_SIZE,0,0));
		drinkPanel.setLayout(new GridLayout(drinkMenuRowsize,MENU_COL_SIZE,0,0));

		/* menuDB에서 총 보여줄 메뉴들이 몇 개 인지 파악하여 버튼의 갯수 및 레이아웃을 결정 
		 * 각 카테고리별 버튼은 반드시 12개 이상 생성 ( 버튼 크기의 향상성을 위해)
		 * 만일 실제 존재하는 카테고리의 메뉴 수보다 버튼이 많으면 NULL값 넣어야함.*/
		
		sushiBtn = new JButton[sushiMenuCount > DEFAULT_MENU_PANEL_SIZE ? sushiMenuCount : DEFAULT_MENU_PANEL_SIZE ];
		dishBtn = new JButton[dishMenuCount > DEFAULT_MENU_PANEL_SIZE ? dishMenuCount : DEFAULT_MENU_PANEL_SIZE];
		drinkBtn = new JButton[drinkMenuCount > DEFAULT_MENU_PANEL_SIZE ? drinkMenuCount : DEFAULT_MENU_PANEL_SIZE];

		// 카테고리별 버튼생성 및 해당 패널에 붙이기
		setMenuBtn(wholeMenuList.get(MenuCategory.SUSHI.getOrder()), sushiBtn, sushiPanel);
		setMenuBtn(wholeMenuList.get(MenuCategory.DISH.getOrder()), dishBtn, dishPanel);
		setMenuBtn(wholeMenuList.get(MenuCategory.DRINK.getOrder()), drinkBtn, drinkPanel);
	

		
		scrollPane_1 = new JScrollPane(sushiPanel);
		scrollPane_2 = new JScrollPane(dishPanel);
		scrollPane_3 = new JScrollPane(drinkPanel);
		scrollPane_1.setPreferredSize(new Dimension(450, 300));
//		***************** 카테고리별 메뉴 버튼 생성 끝  ********************

		//상단 (ㅎㅎㅎ 스시 라벨)
		lblBrandName = new JLabel("ㅎㅎㅎ_Sushi");

		MenuTab = new JTabbedPane(JTabbedPane.TOP);
		NorthPane.setLayout(new GridLayout(0, 1, 0, 0));
		NorthPane.add(lblBrandName);

		getContentPane().add(NorthPane, BorderLayout.NORTH);
		
		//중앙
		getContentPane().add(CenterPane, BorderLayout.CENTER);
		CenterPane.setLayout(new GridLayout(1, 2, 0, 0));

		//중앙 좌측 상단
		CenterPane.add(MenuTab);

		MenuTab.addTab("초밥류", scrollPane_1);
		MenuTab.addTab("식사류", scrollPane_2);
		MenuTab.addTab("주류", scrollPane_3);

		
		
		//중앙 우측 상단 (테이블 번호 보여주는 라벨) 
		lblOrderList = new JLabel("##번 테이블 주문내역");
		TablePanel_North.add(lblOrderList);
		
		
		//중앙 우측 중간
		CenterPane.add(Tablepanel);
		
		Tablepanel.setLayout(new BorderLayout(0, 0));
		Tablepanel.add(TablePanel_North, BorderLayout.NORTH);
		
		Tablepanel.add(TablePanel_Center, BorderLayout.CENTER);

		
		scrollPaneOrderTable = new JScrollPane(tableMenu);
		scrollPaneOrderHistoryTable = new JScrollPane(orderHistoryTable);
		
		scrollPaneOrderHistoryTable.setPreferredSize(new Dimension(200,270));
		scrollPaneOrderTable.setPreferredSize(new Dimension(200,190));
		
		TablePanel_Center.setLayout(new BorderLayout(0, 0));
		TablePanel_Center.add(scrollPaneOrderHistoryTable, BorderLayout.NORTH);
		TablePanel_Center.add(new JLabel("------------------------------------------"
				+ "추가 주문"
				+ "------------------------------------------"), BorderLayout.CENTER);
		TablePanel_Center.add(scrollPaneOrderTable, BorderLayout.SOUTH);


		//중앙 우측 하단 (버튼 )
		addBtn = new JButton("품목추가");
		deleteBtn = new JButton("품목삭제");
		paymentBtn = new JButton("결제");
		
		Tablepanel.add(TablePanel_South, BorderLayout.SOUTH);

		TablePanel_South.setLayout(new GridLayout(0, 2, 0, 0));
		TablePanel_South.add(deletePanel);
		TablePanel_South.add(paymentPanel);
			
			deletePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			deletePanel.add(addBtn);
			deletePanel.add(deleteBtn);
		
			paymentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			paymentPanel.add(paymentBtn);
	}

	public void eventProc() {
		//카테고리별 주문 버튼에 이벤트 등록
		
		//초밥류
		for (int i = 0; i < sushiBtn.length; i++) {
			sushiBtn[i].addActionListener( this );
		}
		//식사류
		for (int i = 0; i < dishBtn.length; i++) {
			dishBtn[i].addActionListener( this );
		}
		//주류
		for (int i = 0; i < drinkBtn.length; i++) {
			drinkBtn[i].addActionListener( this );
		}

		//add, delete, payment 버튼에 이벤트리스너 등록
		addBtn.addActionListener( this );
		deleteBtn.addActionListener( this );
		paymentBtn.addActionListener( this );
		
	}


	
	//카테고리별 메뉴버튼 생성 및 패널에 부착
	public void setMenuBtn(ArrayList<MenuVO> menuList, JButton[] btns, JPanel panel){
		for (int i = 0; i < menuList.size(); i++) {
			String menuName = menuList.get(i).getName();
			String image 	= menuList.get(i).getImage();

			btns[i] = new JButton(menuName, new ImageIcon(image));
			btns[i].setPreferredSize(new Dimension(120, 130));
			
			panel.add(btns[i]);
		}
		
		/* 만약 카테고리의 메뉴의 수가 12개보다 적으면 나머지 버튼에는 널값을 부여 */
		if ( menuList.size() < DEFAULT_MENU_PANEL_SIZE ){
			for (int i = menuList.size() ; i < DEFAULT_MENU_PANEL_SIZE; i++) {
				btns[i] = new JButton("EMPTY");
				btns[i].setPreferredSize(new Dimension(120, 130));
				
				panel.add(btns[i]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		
		if ( evt == paymentBtn ) payment();
		else if ( evt == addBtn ){
			//makeOrderList(orderList) =>	orderList를 받아 OrderVO들로 구성된 orderVOList를 생성
			addOrder(makeOrderList(orderList));		//orderVO로 구성된 ArrayList를 model을 통해 db에 추가(insert)
		}
		else if ( evt == deleteBtn ){
			deleteMenu(tableMenu.getSelectedRow());
			
		}else{
			//눌려진 버튼의 인덱스를 바로 찾아냄.
			//만일 창의 크기, 버튼의 크기가 변경시 반드시 확인 필요***************
			JButton btn = (JButton)evt;
			int btnIndex = getBtnIndex(btn.getX(), btn.getY());
			
			//클릭된 메뉴를 바로 orderList라는 ArrayList에 추가. 
			if ( evt == sushiBtn[btnIndex] ){
				orderList.add(wholeMenuList.get(MenuCategory.SUSHI.getOrder()).get(btnIndex));
				displayOrder();
			}
			else if ( evt == dishBtn[btnIndex] ){
				orderList.add(wholeMenuList.get(MenuCategory.DISH.getOrder()).get(btnIndex));
				displayOrder();
			}
			else if ( evt == drinkBtn[btnIndex] ){
				orderList.add(wholeMenuList.get(MenuCategory.DRINK.getOrder()).get(btnIndex));
				displayOrder();
			}
		}
	}
	public int getBtnIndex(int x, int y){
		int index = y/130*3 + (x/120);
		return index;
	}
	
	public void payment() {
		// model.payment();
	}

	//*****테스트 필요
	//고객이 테이블에 앉아 시작을 누르면 customer DB에 insert하여 customerNo를 받아옴
	public void getCustomerNo(String tableNo){
		try {
			orderModel.getCustomerNo(tableNo);
		} catch (Exception e) {
			System.out.println("고객넘버 찾아오기 실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	//MenuVO로 이루어진 orderList를 주문을 넣기 위한 OrderVO의 리스트로 변환
	public ArrayList<OrderVO> makeOrderList(ArrayList<MenuVO> orderList){
		
		ArrayList<OrderVO> orderVOList = new ArrayList<OrderVO>();
		
		
		//임시 테스트 값
		customerNo = "02";
		
		//입력받은 총 menu의 갯수만큼 수행
		for (int i = 0; i < orderList.size(); i++) {
			//customerNo와 menuCode를 매개변수로 전달
			OrderVO order = new OrderVO(customerNo, orderList.get(i).getMenuCode());
			orderVOList.add(order);
		}
		
		return orderVOList;
	}
	
	//추가 주문을 ordertable에 추가.
	public void addOrder(ArrayList<OrderVO> orderVOList) {
		
		//orderVO를 하나씩 넘겨줌
		for (int i = 0; i < orderVOList.size(); i++) {
			orderModel.addOrder(orderVOList.get(i));	
		}
		
		//추가하기위해 저장해두었던 orderList와 orderListHashMap을 모두 초기화, 테이블도 초기화
		orderListHashMap = null;
		orderList = null;
		tableModel.data = new ArrayList<String>();
		tableModel.fireTableDataChanged();
	}

	public void deleteMenu(int row) {
		//
	}
	
	//주문을 한 개 씩 저장하고 있는 orderList를 Hashmap에 저장하여, 중복될 경우 수량을 1 늘려줌
	public void displayOrder(){
		orderListHashMap = new HashMap<MenuVO, Integer>();
		for (int i = 0; i < orderList.size(); i++) {
			
			//Hashmap에 해당 주문내역이 포함되어 있지 않으면 카운터에 1을 대입
			if ( orderListHashMap.containsKey(orderList.get(i)) == false ){
				orderListHashMap.put(orderList.get(i),1);
			}else{
				//hashmap에 해당 주문내역이 이미 포함되어 있으면  카운터의 숫자를 1씩 더 함.
				orderListHashMap.put(orderList.get(i), orderListHashMap.get(orderList.get(i))+1);
			}
		}
		//테이블 열 이름 생성
//		ArrayList<String> columnVec = new ArrayList<String>();
//		Collections.addAll(columnVec, columnNames);
		
		//hasMap의 정보를 화면(번호, 상품명, 수량)에 보여주기 위한 arrayList생성
		ArrayList<ArrayList<String>> orderListWithCounter = new ArrayList<ArrayList<String>>();
		Iterator<MenuVO> itr = orderListHashMap.keySet().iterator();
		int rowOrder= 0;
		
		while ( itr.hasNext() ){
			//각 행의 데이터 입력 (순번, 메뉴명, 수량)
			ArrayList<String> row = new ArrayList<String>();
			
			row.add(String.valueOf(rowOrder+1));
			
			MenuVO menu = itr.next();
			
			row.add(menu.getName());
			row.add(orderListHashMap.get(menu).toString());
			
			orderListWithCounter.add(row);
			
			rowOrder++;
		}
		//테이블에 전체 주문한 리스트 넣기
		tableModel.data = orderListWithCounter;
		tableModel.fireTableDataChanged();
		
		/* 정렬이 안되고 그냥 나오는데. 이거 그냥 순서대로 넣게 할 수 없나 ?*/
	}
	
	//나중에 sushi_store에 연동하고 주석처리하기.
	//01번 테이블로 실행한 scene
	public static void main(String[] args) {
		new Table_orderView("01");
	}
	
	public class OrderTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "번호", "주문명", "수량" };

		public int getRowCount() {
			return data.size();
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public Object getValueAt(int row, int col) {
			ArrayList temp = (ArrayList) data.get(row);
			return temp.get(col);
		}

	}
}
