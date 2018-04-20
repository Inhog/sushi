package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import model.MenuModel;
import vo.MenuVO;


/*
 *  메뉴에서 클릭하면 수정페이지로 넘어가도록 할 것
 * 
 * 
 * */
public class MenuModifyView extends JDialog implements ActionListener{
	JTable table;
	MenuTableModel tbModelMenu;

	JButton btnModifyMenu; 
	JButton btnDeleteMenu;
	
	MenuModel model;
	
	MenuModifyDialog menuModifyDialog;
	
	MenuVO	  menu = null;
	
	public MenuModifyView(){
		addLayout();
		eventProc();	//이벤트 등록
		dbConnection();
		
		setSize(800, 600);
		/* 데스트 후 주석필요
		 * */
//		setVisible(true);
	}
	
	void dbConnection(){
		model = new MenuModel();
	}
	
	void addLayout(){
		
		tbModelMenu = new MenuTableModel();
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		table = new JTable(tbModelMenu);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane);
		
		btnModifyMenu = new JButton("메뉴수정");
		btnDeleteMenu = new JButton("메뉴삭제");
		
		getContentPane().add(btnModifyMenu);
		getContentPane().add(btnDeleteMenu);
		
		
	}
	
		
	void eventProc(){
		btnModifyMenu.addActionListener( this );
		btnDeleteMenu.addActionListener( this );
		//테이블에 이벤트 등록
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				
				//테이블의 선택된 한 행의 값을 차례로 menuVO에 입력
					
					String menuCode = (String)table.getValueAt(row, 0);
					String name		= (String)table.getValueAt(row, 1);
					String price	= (String)table.getValueAt(row, 2);
					String category	= (String)table.getValueAt(row, 3);
					String image 	= (String)table.getValueAt(row, 4);
					
					menu = new MenuVO(menuCode, name, price, category, image);
				
			}
		});
	}
	
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		
		//메뉴수정 버튼을 클릭 시
		if ( evt == btnModifyMenu ){
			//menuModifyDialog가 생성 (혹은 보임 상태가 됨)
			if ( menuModifyDialog == null ) {
				menuModifyDialog = new MenuModifyDialog();
				
				//tf채워주기
				menuModifyDialog.tfMenuCode.setText(menu.getMenuCode());
				menuModifyDialog.tfMenuName.setText(menu.getName());
				menuModifyDialog.tfPrice.setText(menu.getPrice());
				menuModifyDialog.category.setSelectedItem(menu.getCategory());
				menuModifyDialog.tfImage.setText(menu.getImage());
				
				//저장하고 있던 선택된 메뉴의 정보를 전달 
				
			}
			menuModifyDialog.setVisible(true);
			
		//삭제버튼 클릭 시	
		}else if ( evt == btnDeleteMenu){
			delete(menu);
			tbModelMenu.fireTableDataChanged();
		}
	}
	
	void delete(MenuVO menu){
		try {
			model.delete(menu);
			tbModelMenu.fireTableDataChanged();
			displayAll();
		} catch (Exception e) {
			System.out.println("메뉴삭제 실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getFileLocation(){
		String filePath;
		
		JFileChooser fileChooser = new JFileChooser();

		// filechooser 초기 위치를 현재 위치로 변경
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		
		int result = fileChooser.showOpenDialog( this );

		//미입력 및 오류 시 filePath에 null값 넣기
		if ( result != JFileChooser.APPROVE_OPTION ) filePath = null;
		filePath = fileChooser.getSelectedFile().getPath();
		
		return filePath; 
	}
	
	public void displayAll(){
		try {
			//menuList를 table에 넣기.
			ArrayList<ArrayList<String>> menuList = model.displayAll();
			tbModelMenu.data = menuList;
			tbModelMenu.fireTableDataChanged();
			
		} catch (Exception e) {
			System.out.println("전체메뉴display실패 :" + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* 데스트 후 주석필요
	 * */
//	public static void main(String agrs[]){
//		new MenuModifyView();
//	}
	
}

class MenuModifyDialog extends MenuAddView {
	
	MenuModifyDialog(){
		super();
		/* MenuAddView를 변형하여 Modify dialog로 사용하기 위해 버튼 이름을 변경 및 기존 정보 display*/
		super.btnStockAdd.setText("메뉴수정");
		super.tfMenuCode.setEnabled(false);
	}
	/*add를 위한 view이지만, Modify에서 수정을 위한 포멧과 동일하므로 이용함.*/
	
	public void actionPerformed(ActionEvent e) {
		Object evt = e.getSource();
		if ( evt == btnStockAdd ){
			MenuVO menu = new MenuVO();
			
			menu.setMenuCode(tfMenuCode.getText());
			menu.setName(tfMenuName.getText());
			menu.setPrice(tfPrice.getText());
			menu.setCategory((String)category.getSelectedItem());
			menu.setImage(tfImage.getText());
			
			modifyMenu(menu);

		}
	} 
	
	void modifyMenu(MenuVO menu){
		try {
			model.modify(menu);
			dispose();
		} catch (Exception e) {
			System.out.println("메뉴수정 실패 : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}

class MenuTableModel extends AbstractTableModel {

	ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
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