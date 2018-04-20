package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.StockModel;
<<<<<<< HEAD
/**
  * Created by clap on 2018. 4. 19. 오후 5:49:31
  */
=======
import vo.StockVO;

>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733
public class StockModifyView extends JDialog{

	JLabel laMix;
	JComboBox titleCombo, eachMenuCombo;
	JTextField tfQuantity;
	JTextField tfAddDate;
	JTextField tfExpiredDate;
	JTextField tfStockCode;

	JMenuItem mnbtnMaterialCode;

	String[] title = { "초밥류", "식사류", "주류", "비품" };
	String[] eachMenu = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" };

	JButton btnModifyUpdate;

	StockModel model;
	StockView parent;
	
//	JTable table;
//
//	StockTableModel tbModelStock;
//	JButton btnModifyStock; 
//	
//	
//	StockModel model;
	
	StockModifyView(){
		addLayout();
		eventProc();	//이벤트 등록
		dbConnection();
		setSize(800, 200);

	}
	
	
	void dbConnection(){
		model = new StockModel();
	}
	
	void addLayout(){
		setAlwaysOnTop(true);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 1, 0, 0));

		panel.add(new JLabel("재고코드"));
		panel.add(new JLabel("수량"));
		panel.add(new JLabel("입고일"));
		panel.add(new JLabel("유통기한"));

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(5, 1, 0, 0));

		/* 메뉴버튼에 자재코트 입력 필요 함 - 수형 */
		
		titleCombo = new JComboBox(title);
		this.add(titleCombo);
		eachMenuCombo = new JComboBox(eachMenu);
		this.add(eachMenuCombo);
		// jcMaterialName.setColumns(10);

		tfStockCode = new JTextField();
		tfStockCode.setColumns(10);
		panel_1.add(tfStockCode);

		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		panel_1.add(tfQuantity);
		
		tfAddDate = new JTextField();
		tfAddDate.setColumns(10);
		panel_1.add(tfAddDate);
		
		tfExpiredDate = new JTextField();
		tfExpiredDate.setColumns(10);
		panel_1.add(tfExpiredDate);
		
		btnModifyUpdate = new JButton("수  정");
		panel_1.add(btnModifyUpdate);
	}
		
	void eventProc(){
		btnModifyUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockVO stock = new StockVO();
				stock.setStockNo(tfStockCode.getText());
				stock.setQuantity(tfQuantity.getText());
				stock.setAddDate(tfAddDate.getText());
				stock.setExpiredDate(tfExpiredDate.getText());
				model.modify(stock);
			}
		});
	}
	
//	public static void main(String agrs[]){
//		new StockModifyView();
//	}
}