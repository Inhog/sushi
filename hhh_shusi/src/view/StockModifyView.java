package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.StockModel;

public class StockModifyView extends JDialog{
	JTable table;

	StockTableModel tbModelStock;
	JButton btnModifyStock;
	JButton btnDeleteStock; 
	
	
	StockModel model;
	
	StockModifyView(){
		addLayout();
		eventProc();	//이벤트 등록
		setSize(800, 600);

	}
	
	void addLayout(){
		
		tbModelStock = new StockTableModel();
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		
		table = new JTable(tbModelStock);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		
		btnModifyStock = new JButton("Modify");
		btnDeleteStock  = new JButton("Delete");
		
		getContentPane().add(btnModifyStock);
		getContentPane().add(btnDeleteStock);
	}
	
	void eventProc(){
		btnModifyStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modify();
			}
		});
	}
	
	void modify(){
		model.modify();
	}
	
	
	
//	public static void main(String agrs[]){
//		new StockModifyView();
//	}
}
