package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class StockView extends JFrame implements ActionListener {
	JButton insertRoll, updateRoll, searchBtr;
	JTextField stockCode, matName, matCode;
	JLabel laStockCode, laMatName, laMatCode;
	JCheckBox matCheck;

	JTable tableList;
	StockTableModel tbModelStock;

	public void addLayout() {

		stockCode = new JTextField(10);
		matName = new JTextField(10);
		matCode = new JTextField(10);
		insertRoll = new JButton("입고등록");
		updateRoll = new JButton("재고수정");
		searchBtr = new JButton("검색");
		matCheck = new JCheckBox("자재별 조회");

		tbModelStock = new StockTableModel();
		tableList = new JTable(tbModelStock);

		JPanel p_north_west = new JPanel(new GridLayout(1, 2)); // 위쪽에 왼쪽
		JPanel p_north_center = new JPanel(new GridLayout(4, 1)); // 위쪽에 가운데
		JPanel p_north_east = new JPanel(new BorderLayout());

		// 윗쪽의 왼쪽 버튼 영역
		p_north_west.add(insertRoll);
		p_north_west.add(updateRoll);

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

	class StockTableModel extends AbstractTableModel {

		ArrayList data = new ArrayList();
		String[] columnNames = { "재고코드", "자재코드", "자재명", "수량", "입고일", "유통기한" };

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

	public StockView() {
		addLayout();
		eventProc();
		setSize(800, 600);
		setVisible(true);

	}

	void eventProc() {
		insertRoll.addActionListener(this);
		updateRoll.addActionListener(this);
		searchBtr.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object evt = ev.getSource();

	}

	public static void main(String[] args) {
		new StockView();
	}
}
