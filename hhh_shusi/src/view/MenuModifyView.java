package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.MenuModel;

public class MenuModifyView extends JDialog{
	JTable table;
	MenuTableModel tbModelMenu;

	JButton btnModifyMenu; 
	JButton btnDeleteMenu;
	
	MenuModel model;
	
	MenuModifyView(){
		addLayout();
		eventProc();	//이벤트 등록
		setSize(800, 600);
		/* 데스트 후 주석필요
		 * */
		setVisible(true);
	}
	
	void addLayout(){
		
		tbModelMenu = new MenuTableModel();
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		table = new JTable(tbModelMenu);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		
		btnModifyMenu = new JButton("Modify");
		btnDeleteMenu = new JButton("Delete");
		
		getContentPane().add(btnModifyMenu);
		getContentPane().add(btnDeleteMenu);
	}
	
	void eventProc(){
		btnModifyMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				modify();
//				
//				delete();
			}
		});
	}
	
	void modify(){
		model.modify();
	}
	
	void delete(){
		model.delete();
	}
	/* 데스트 후 주석필요
	 * */
	public static void main(String agrs[]){
		new MenuModifyView();
	}
}


class MenuTableModel extends AbstractTableModel {

	ArrayList data = new ArrayList();
	String[] columnNames = { "메뉴코드", "메뉴명", "가격", "카테고리", "이미지"};

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