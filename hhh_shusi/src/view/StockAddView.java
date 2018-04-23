package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Customer.MenuVO;
import model.StockModel;
import vo.StockVO;

public class StockAddView extends JDialog {
	private JLabel laMix;
	private JComboBox titleCombo, eachMenuCombo;
	private JTextField tfQuantity;
	private JTextField tfAddDate;
	private JTextField tfExpiredDate;

	private JMenuItem mnbtnMaterialCode;

	String[] title = { "초밥류", "식사류", "주류", "비품" };
//	String[] eachMenu = { "광어지느러미", "참다랑어대뱃살", "간장새우", "다랑어", "대하", "아보카도참치와사비", "유부", "낫또", 
//            "새우후라이", "hhh우동", "새우튀김우동", "계란찜","타코야끼",
//            "생맥주","아사히","카스","클라우드",
//            "휴지","물비누"};
	JButton btnStockAdd;

	StockModel model;

	StockView parent;

	// 구성 요소 선언

	StockAddView(StockView parent) {
		this.parent = parent;
		addLayout();
		eventProc(); // 이벤트 등록
		dbConnection();
		setSize(800, 600);
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
		eachMenuCombo = new JComboBox();
		this.add(eachMenuCombo);
		// jcMaterialName.setColumns(10);

		laMix = new JLabel();
		panel_1.add(laMix);

		tfQuantity = new JTextField();
		tfQuantity.setColumns(10);
		panel_1.add(tfQuantity);

		tfExpiredDate = new JTextField();
		tfExpiredDate.setColumns(10);
		panel_1.add(tfExpiredDate);

		btnStockAdd = new JButton("입고등록");
		panel_1.add(btnStockAdd);
	}

	void eventProc() {
		//입고등록 버튼을 누르면 콤보선택한 데이터와 수량, 유통기한이 입력할 수 있다.
		btnStockAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.insert(new StockVO(String.valueOf(eachMenuCombo.getSelectedItem()),
						                 tfQuantity.getText(),
						                 tfExpiredDate.getText()));
				
				// 재고화면의 전체목록 보기
				parent.search();
				// 화면 닫기
			}
		});
		
		titleCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				String cate = (String) titleCombo.getSelectedItem();
				selectCate(cate);
				initial();
			}
		});
		eachMenuCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initial();
			}
		});
	}
	
	void selectCate(String cate){
		// 비니지스 모델을 통해 DB(menu테이블)의 name값들을 가져와서 콤보에 출력
		//	- 예외처리
		//  - 리턴된 ArrayList를 받아서 
		//  - 반복문으로 ArrayList의 아이템을 하나씩 얻어와서 콤보박스에  추가 
		try {
			ArrayList<String> ara = new ArrayList<>();
			ara = model.menuCode(cate);
			eachMenuCombo.removeAllItems();
			for(int i=0; i<ara.size(); i++){
				eachMenuCombo.addItem(ara.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//-ComboBox의 값을 laMix텍스트에 찍어줌
	void initial() {
		laMix.setText(titleCombo.getSelectedItem() + " / " + eachMenuCombo.getSelectedItem());
	}
}