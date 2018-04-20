package view;

import javax.swing.JDialog;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.StockModel;
import model.vo.Stock;

import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;

public class StockAddView extends JDialog {
	private JLabel laMix;
	private JComboBox titleCombo, eachMenuCombo;
	private JTextField tfQuantity;
	private JTextField tfEnterDate;
	private JTextField tfExpiredDate;

	private JMenuItem mnbtnMaterialCode;

	String[] title = { "초밥류", "식사류", "주류", "비품" };
	String[] eachMenu = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" };

	JButton btnStockAdd;

	StockModel model;

	StockView parent;

	// 구성 요소 선언

	StockAddView(StockView parent) {
		this.parent = parent;
		addLayout();
		eventProc(); // 이벤트 등록
		dbConnection();
		setSize(600, 500);
		initial();
	}

	void dbConnection() {
		model = new StockModel();
	}

	void addLayout() {
		setAlwaysOnTop(true);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 1, 0, 0));

		panel.add(new JLabel("자재명"));
		panel.add(new JLabel("수량"));
		panel.add(new JLabel("유통기한(년-월-일)"));

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(4, 1, 0, 0));

		/* 메뉴버튼에 자재코트 입력 필요 함 - 수형 */

		titleCombo = new JComboBox(title);
		this.add(titleCombo);
		eachMenuCombo = new JComboBox(eachMenu);
		this.add(eachMenuCombo);
		// jcMaterialName.setColumns(10);

		laMix = new JLabel();
		panel_1.add(laMix);

		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		panel_1.add(tfQuantity);

//		tfEnterDate = new JTextField();
//		tfEnterDate.setColumns(10);
//		panel_1.add(tfEnterDate);
//
		tfExpiredDate = new JTextField();
		tfExpiredDate.setColumns(10);
		panel_1.add(tfExpiredDate);

		btnStockAdd = new JButton("입고등록");

		panel_1.add(btnStockAdd);
	}

	void eventProc() {
		btnStockAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.insert(new Stock(String.valueOf(eachMenuCombo.getSelectedItem()),
						          tfQuantity.getText(),tfExpiredDate.getText()));
				// 재고화면의 전체목록 보기
				parent.search();
				// 화면 닫기
			}
		});
		titleCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				initial();
			}
		});
		eachMenuCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initial();
			}
		});
	}

	void initial() {
		laMix.setText(titleCombo.getSelectedItem() + " / " + eachMenuCombo.getSelectedItem());
	}

	// public static void main(String args[]){
	// new StockAddView();
	// }
}
