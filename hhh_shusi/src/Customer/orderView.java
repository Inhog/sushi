package Customer;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class orderView extends JFrame implements ActionListener{
	
	JPanel NorthPane,CenterPane,Tablepanel,TablePanel_South,sendPanel,TablePanel_Center,TablePanel_North,deletePanel;
	JLabel lblBrandName,lblOrderList;
	JTabbedPane	MenuTab;
	JScrollPane	scrollPane_1,scrollPane_2,scrollPane_3;
	JButton	bSendorder,bDeleteorder;
	public orderView() {
		addLayout();
		eventProc();
		connectDB();
	}
	// 우선 프레임 구성만 한 상태 - 18.04.18 
	// 내부 메서드와 Model 클래스를 만들어서 객체 생성해야함.
	public void addLayout(){
		NorthPane = new JPanel();
		CenterPane = new JPanel();
		Tablepanel = new JPanel();
		TablePanel_South = new JPanel();
		sendPanel = new JPanel();
		deletePanel = new JPanel();
		TablePanel_North = new JPanel();
		TablePanel_Center = new JPanel();

		lblBrandName = new JLabel("ㅎㅎㅎ_Sushi");
		lblOrderList = new JLabel("ㅎㅎㅎSushi 주문내역");

		bSendorder = new JButton("주문전송");
		bDeleteorder = new JButton("품목삭제");

		MenuTab = new JTabbedPane(JTabbedPane.TOP);

		scrollPane_1 = new JScrollPane();
		scrollPane_2 = new JScrollPane();
		scrollPane_3 = new JScrollPane();

		getContentPane().add(NorthPane, BorderLayout.NORTH);
		getContentPane().add(CenterPane, BorderLayout.CENTER);

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
	}
	public void eventProc(){
		bDeleteorder.addActionListener(this);
		bSendorder.addActionListener(this);
	}
	public void connectDB(){
		
	}
	public void addMenu(){
		
	}
	public void deleteMenu(){
		
	}
	public void sendOrder(){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
