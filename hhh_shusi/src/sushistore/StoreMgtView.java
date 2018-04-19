package sushistore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StoreMgtView extends JFrame implements ActionListener{

	
	JPanel NorthPanel,North_SouthPanel,CenterPanel,SouthPanel,WestPanel,EastPanel,South_NorthPanel;
	JLabel lblStoreName,lblBrand,lblSpace2;
	JButton bTable1,bTable2,bTable3,bStock,bExit;
	JPanel South_SouthPanel;
	JLabel lblSpace3;
	JPanel North_NorthPanel;
	JLabel lblSpace4;
	JLabel lblSpace1;
	

	
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

		bTable1 = new JButton("테이블1");
		bTable2 = new JButton("테이블2");
		bTable3 = new JButton("테이블3");
		bStock = new JButton("재고");
		bExit = new JButton("종료");
		
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
		
		SouthPanel.add(bStock, BorderLayout.WEST);
		SouthPanel.add(bExit, BorderLayout.EAST);
		North_SouthPanel.add(lblSpace1);
		South_NorthPanel.add(lblSpace2);
		South_SouthPanel.add(lblSpace3);
		North_NorthPanel.add(lblSpace4);


		CenterPanel.add(bTable1);
		CenterPanel.add(bTable2);
		CenterPanel.add(bTable3);
	}
	
	public void eventProc() {
		bTable1.addActionListener(this);
		bTable2.addActionListener(this);
		bTable3.addActionListener(this);
		bStock.addActionListener(this);
		bExit.addActionListener(this);
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
//			new StockView();
		}else if(evt == bExit){
			System.exit(0);
		}
	}

}
