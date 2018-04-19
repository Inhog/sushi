package view;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.StockModel;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class StockAddView extends JDialog{
	private JTextField tfStockCode;
	private JTextField tfMaterialName;
	private JTextField tfQuantity;
	private JTextField tfEnterDate;
	private JTextField tfExpiredDate;
	
	private JMenuItem mnbtnMaterialCode;
	
	JButton btnStockAdd;
	
	StockModel model;
	
	// 구성 요소 선언
	
	StockAddView(){
		addLayout();
		eventProc();	//이벤트 등록
		setSize(600, 500);
	}
	
	void addLayout(){
		setAlwaysOnTop(true);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(7, 1, 0, 0));
		
		panel.add(new JLabel("재고코드"));
		panel.add(new JLabel("자재코드"));
		panel.add(new JLabel("자재명"));
		panel.add(new JLabel("수량"));
		panel.add(new JLabel("입고일"));
		panel.add(new JLabel("유통기한"));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(7, 1, 0, 0));
		
		tfStockCode = new JTextField();
		panel_1.add(tfStockCode);
		tfStockCode.setColumns(10);
		
		
		/*메뉴버튼에 자재코트 입력 필요 함 - 수형*/
		mnbtnMaterialCode = new JMenuItem("자재코드선택");
		panel_1.add(mnbtnMaterialCode);
		
		tfMaterialName = new JTextField();
		tfMaterialName.setColumns(10);
		panel_1.add(tfMaterialName);
		
		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		panel_1.add(tfQuantity);
		
		tfEnterDate = new JTextField();
		tfEnterDate.setColumns(10);
		panel_1.add(tfEnterDate);
		
		tfExpiredDate = new JTextField();
		tfExpiredDate.setColumns(10);
		panel_1.add(tfExpiredDate);
		
		btnStockAdd = new JButton("입고등록");

		
		panel_1.add(btnStockAdd);
	}
	
	void eventProc(){
		btnStockAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.insert();
			}
		});
	}
	
//	public static void main(String args[]){
//		new StockAddView();
//	}
}
