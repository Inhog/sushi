package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

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