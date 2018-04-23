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
import vo.StockVO;

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
		stockCode.setEditable(false); // setEditable은 편집불가(값은 나옴)/setEnable은 아예
										// 못쓰는거
		matName = new JTextField(10);
		matCode = new JTextField(10);
		matCode.setEditable(false);
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
		p_north_center1.add(new JLabel("재고번호"));
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
		// DB연결
		ConnectionDB();
		addLayout();
		eventProc();
		setSize(800, 600);
		search();

	}

	void eventProc() {
		insertRoll.addActionListener(this);
		searchBtr.addActionListener(this);

		tableList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableList.getSelectedRow();
				int col = 0;
				String stockNum = (String) tableList.getValueAt(row, col);
				// System.out.println(stockNum);
				StockVO stock;
				try {
					// 데이타베이스에서 데이타 가져옴
					stock = model.selectStock(stockNum);

					StockModifyView stockModify = new StockModifyView();
					// *
					stockModify.tfStockCode.setText(stock.getStockNo());
					stockModify.eachMenuCombo.setSelectedItem(stock.getMaterialCode());
					stockModify.tfQuantity.setText(stock.getQuantity());
					stockModify.tfAddDate.setText(stock.getAddDate());
					stockModify.tfExpiredDate.setText(stock.getExpiredDate());
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
		// 화면에서 입고등록 버튼을 누르는 이벤트
		Object evt = ev.getSource();
		if (evt == insertRoll) {
			// stockAddView는 처음 호출 시 한 번만 생성
			// stockAddView 창을 불러옴
			if (stockAdd == null)
				stockAdd = new StockAddView(this);
			stockAdd.setVisible(true);

			// 검색 버튼을 누름
		} else if (evt == searchBtr) {
			// 입력받은 자재명을 얻어옴
			ArrayList data = model.searchData(matName.getText());
			// 자재명을 데이터 베이스에서 받아와서 찾은 데이터를 JTable에 출력
			tbModelStock.data = data;
			tbModelStock.fireTableDataChanged();
		}
	}

	void search() {
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		try {
			data = model.search();
			tbModelStock.data = data;
			tbModelStock.fireTableDataChanged();
		} catch (Exception e) {
			System.out.println("재고list출력 실패 :" + e.getMessage());
		}
	}

	void ConnectionDB() {
		model = new StockModel();
	}
}
