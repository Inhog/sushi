package sushistore;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.MenuAddView;
import view.MenuModifyView;
<<<<<<< HEAD
=======
import view.StockView;
>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733

public class StoreMgtView extends JFrame implements ActionListener{

	
	JPanel NorthPanel,North_SouthPanel,CenterPanel,SouthPanel,WestPanel,EastPanel,South_NorthPanel;
	JLabel lblStoreName,lblBrand,lblSpace1,lblSpace2,lblSpace3,lblSpace4;
	JButton bTable1,bTable2,bTable3,bStock,bExit,bMenuManagement;
	JPanel South_SouthPanel,North_NorthPanel,South_CenterPanel,panel_space1,panel_space2,panel_space3;
	
	JButton bAddMenu,bModifyMenu;
	
	MenuAddView		menuAdd;
	MenuModifyView	menuModify;
<<<<<<< HEAD
=======
	StockView		stock;
>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733
	
	JDialog	 menuMgt;
	
	public StoreMgtView(){
		// JFrame 레이아웃 추가
		addLayout();
		
		eventProc();
		connectDB();
		
		setSize(519,497);
		setVisible(true);

		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	}
	

	public void addLayout(){
		getContentPane().setLayout(new BorderLayout(0, 0));

		NorthPanel = new JPanel();
		North_SouthPanel = new JPanel();
		CenterPanel = new JPanel();
		SouthPanel = new JPanel();
		South_NorthPanel = new JPanel();
		WestPanel = new JPanel();
		EastPanel = new JPanel();
		South_SouthPanel = new JPanel();
		North_NorthPanel = new JPanel();
		South_CenterPanel = new JPanel();
		panel_space2 = new JPanel();
		panel_space1 = new JPanel();
		panel_space3 = new JPanel();

		bTable1 = new JButton("테이블1");
		bTable2 = new JButton("테이블2");
		bTable3 = new JButton("테이블3");
		bMenuManagement = new JButton("메뉴관리");
		bExit = new JButton("종료");
		bStock = new JButton("재고");
		bAddMenu = new JButton("메뉴추가");
		bModifyMenu = new JButton("메뉴 수정/삭제");
		
		lblStoreName = new JLabel("ㅎㅎㅎSushi 멀티캠퍼스점");
		lblBrand = new JLabel("=ㅎㅎㅎSushi=");
		lblSpace1 = new JLabel(" ");
		lblSpace2 = new JLabel(" ");
		lblSpace3 = new JLabel(" ");
		lblSpace4 = new JLabel(" ");
		
		getContentPane().add(NorthPanel, BorderLayout.NORTH);
		getContentPane().add(CenterPanel, BorderLayout.CENTER);
		getContentPane().add(SouthPanel, BorderLayout.SOUTH);
		getContentPane().add(WestPanel, BorderLayout.WEST);
		getContentPane().add(EastPanel, BorderLayout.EAST);
		
		NorthPanel.setLayout(new BorderLayout(0, 0));
		CenterPanel.setLayout(new GridLayout(0, 3, 10, 12));
		SouthPanel.setLayout(new BorderLayout(0, 0));
		
		NorthPanel.add(North_SouthPanel, BorderLayout.SOUTH);
		SouthPanel.add(South_NorthPanel, BorderLayout.NORTH);
		SouthPanel.add(South_SouthPanel, BorderLayout.SOUTH);

		NorthPanel.add(lblStoreName, BorderLayout.WEST);
		NorthPanel.add(lblBrand, BorderLayout.EAST);
		NorthPanel.add(North_NorthPanel, BorderLayout.NORTH);
		North_SouthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		North_SouthPanel.add(lblSpace1);
		South_NorthPanel.add(lblSpace2);
		South_SouthPanel.add(lblSpace3);
		
		SouthPanel.add(South_CenterPanel, BorderLayout.CENTER);
		South_CenterPanel.setLayout(new GridLayout(1, 2, 5, 0));
		South_CenterPanel.add(bStock);
		South_CenterPanel.add(bMenuManagement);
		
		South_CenterPanel.add(panel_space1);
		South_CenterPanel.add(panel_space2);
		South_CenterPanel.add(panel_space3);
		South_CenterPanel.add(bExit);
		North_NorthPanel.add(lblSpace4);


		CenterPanel.add(bTable1);
		CenterPanel.add(bTable2);
		CenterPanel.add(bTable3);
		
		//메뉴관리 버튼 누르면 나오는 dialog
		menuMgt = new JDialog();
		menuMgt.setLayout(new GridLayout(1,2));

		menuMgt.add(bAddMenu);
		menuMgt.add(bModifyMenu);
		menuMgt.setSize(300,100);
		
	}
	
	public void eventProc() {
		bTable1.addActionListener(this);
		bTable2.addActionListener(this);
		bTable3.addActionListener(this);
		bStock.addActionListener(this);
		bExit.addActionListener(this);
		bMenuManagement.addActionListener(this);
		bModifyMenu.addActionListener(this);
		bAddMenu.addActionListener(this);
	}
	
	public void connectDB(){
		try{
//			Model = new Sushi_StoreModel();
			System.out.println("점원메인화면 디비연결 성공");
		}catch(Exception ex){
			System.out.println("점원메인화면 연결실패 :" +ex.getMessage());
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if(evt == bTable1){
//			new Table_orderView();
		}else if(evt == bTable2){
//			new Table_orderView();
		}else if(evt == bTable3){
//			new Table_orderView();
		}else if(evt == bStock){
			if ( stock == null ) stock = new StockView();
			stock.setVisible(true);
		}else if(evt == bExit){
			System.exit(0);
		}else if(evt == bMenuManagement){
<<<<<<< HEAD
		
			menuMgt.setVisible(true);
		}else if(evt == bAddMenu){
			menuMgt.dispose();
			if (menuAdd == null ) menuAdd = new MenuAddView();
			//add버튼을 누르면 기존 MenuManagement창 닫힘
=======
			
			menuMgt.setVisible(true);
		}else if(evt == bAddMenu){
			//add버튼을 누르면 기존 MenuManagement창 닫힘
			menuMgt.dispose();
			if (menuAdd == null ) menuAdd = new MenuAddView();
>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733
			menuAdd.setVisible(true);
			
		}else if(evt == bModifyMenu){
			menuMgt.dispose();
			if (menuModify == null ) menuModify = new MenuModifyView();
			
//		 	DB에 입력된 메뉴를 모두 보여줌
			menuModify.displayAll();
			menuModify.setVisible(true);
			
		}
	}

}
