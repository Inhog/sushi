package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.StockModel;
import model.vo.Stock;

public class StockView extends JFrame implements ActionListener {

	JButton insertRoll, searchBtr;
	JTextField stockCode, matName, matCode;
	JLabel laStockCode, laMatName, laMatCode;
	JCheckBox matCheck;
	
	StockAddView stockAdd;
	StockModifyView stockModify;
	
	JTable tableList;
	StockTableModel tbModelStock;
	
	StockModel model;
	
	public void addLayout() {

		stockCode = new JTextField(10);
		matName = new JTextField(10);
		matCode = new JTextField(10);
		insertRoll = new JButton("  입고등록   ");
		searchBtr = new JButton("  검     색    ");
		matCheck = new JCheckBox("자재별 조회");

		tbModelStock = new StockTableModel();
		tableList = new JTable(tbModelStock);

		JPanel p_north_west = new JPanel(new BorderLayout()); // 위쪽에 왼쪽
		JPanel p_north_center = new JPanel(new GridLayout(4, 1)); // 위쪽에 가운데
		JPanel p_north_east = new JPanel(new BorderLayout());

		// 윗쪽의 왼쪽 버튼 영역
		p_north_west.add(insertRoll);

		// 윗쪽의 가운데 텍스트필드와 체크박스 영역
		JPanel p_north_center1 = new JPanel();
		p_north_center1.add(new JLabel("재고코드"));
		p_north_center1.add(stockCode);
		JPanel p_north_center2 = new JPanel();
		p_north_center2.add(new JLabel("자 재 명"));
		p_north_center2.add(matName);
		JPanel p_north_center3 = new JPanel();
		p_north_center3.add(new JLabel("자재코드"));
		p_north_center3.add(matCode);
		JPanel p_north_center4 = new JPanel();
		p_north_center4.add(matCheck);

		p_north_center.add(p_north_center1);
		p_north_center.add(p_north_center2);
		p_north_center.add(p_north_center3);
		p_north_center.add(p_north_center4);

		p_north_east.add(searchBtr);

		JPanel p_south = new JPanel();
		p_south.setLayout(new BorderLayout());
		p_south.add(new JScrollPane(tableList));

		// setLayout(new BorderLayout());
		// add(p_south,BorderLayout.CENTER);

		getContentPane().add(p_north_west, BorderLayout.WEST);
		getContentPane().add(p_north_center, BorderLayout.CENTER);
		getContentPane().add(p_north_east, BorderLayout.EAST);
		getContentPane().add(p_south, BorderLayout.SOUTH);
		
	}

	public StockView() {
		ConnectionDB();
		addLayout();
		eventProc();
		setSize(800, 600);
		setVisible(true);
		search();

	}
	
	

	void eventProc() {
		insertRoll.addActionListener(this);
		searchBtr.addActionListener(this);
		
		tableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				int row = tableList.getSelectedRow();
				int col = 0;
				String stockNum = (String) tableList.getValueAt(row, col);
				//System.out.println(stockNum);
				Stock st;
				try {
					// 데이타베이스에서 데이타 가져옴
					st = model.selectStock(stockNum);
					
					StockModifyView stockModify = new StockModifyView();
					//*
					stockModify.tfStockCode.setText( st.getStock_no());
					stockModify.eachMenuCombo.setSelectedItem(  st.getMaterial_Code());
					stockModify.tfQuantity.setText(    st.getQuantity() );
					stockModify.tfAddDate.setText( st.getAdd_date());
					stockModify.tfExpiredDate.setText( st.getExpiredDate());
					// 화면 출력
					stockModify.setVisible(true);
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object evt = ev.getSource();
		if (evt == insertRoll) {
			// stockAddView는 처음 호출 시 한 번만 생성
			if (stockAdd == null)
				stockAdd = new StockAddView(this);	
			stockAdd.setVisible(true);
			}
		// else if (evt == updateRoll) {
		// // StockModifyView는 처음 호출 시 한 번만 생성
		// if (stockModify == null) stockModify = new StockModifyView();
		// stockModify.setVisible(true);
	}
	
	void delete(){
		
	}
	void search(){
		
		ArrayList data = new ArrayList();
		try{
		data = model.search();
		}catch(Exception e){
		}
		tbModelStock.data = data;
		tableList.setModel(tbModelStock);
		tbModelStock.fireTableDataChanged();
	}
	
	void ConnectionDB(){
		model = new StockModel();
	}
	public static void main(String[] args) {
		new StockView();
	}
}

	