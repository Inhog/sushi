package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.MenuModel;
import model.StockModel;

/*
 * 2018.4.19 written by clap
 * */

public class MenuAddView extends JDialog implements ActionListener{
	private JTextField tfMenuCode;
	private JTextField tfMenuName;
	private JTextField tfPrice;
	private JTextField tfImage;
	
	private JMenuItem mnbtnCategory;
	
	JButton btnStockAdd;
	
	MenuModel model;
	
	// 구성 요소 선언
	
	MenuAddView(){
		addLayout();
		eventProc();	//이벤트 등록
		
		dbConnection();
		
		setSize(600, 500);
		
		/* 데스트 후 주석필요
		 * */
		setVisible(true);
	}
	void dbConnection(){
		model = new MenuModel();
	}
	
	void addLayout(){
		setAlwaysOnTop(true);
		getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(6, 1, 0, 0));
		
		panel.add(new JLabel("메뉴코드"));
		panel.add(new JLabel("메뉴명"));
		panel.add(new JLabel("가격"));
		panel.add(new JLabel("카테고리"));
		panel.add(new JLabel("이미지"));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(6, 1, 0, 0));
		
		tfMenuCode = new JTextField();
		panel_1.add(tfMenuCode);
		tfMenuCode.setColumns(10);
		
		
		tfMenuName = new JTextField();
		tfMenuName.setColumns(10);
		panel_1.add(tfMenuName);
		
		tfPrice = new JTextField();
		tfPrice.setColumns(10);
		panel_1.add(tfPrice);
		
		/*메뉴버튼에 카테고리코드 입력 필요 함 - 수형*/
		mnbtnCategory = new JMenuItem("카테고리선택");
		panel_1.add(mnbtnCategory);
		
		
		/*파일탐색기 추가 필요*/
		tfImage = new JTextField();
		tfImage.setColumns(10);
		tfImage.setEditable(false);
		panel_1.add(tfImage);
		
		btnStockAdd = new JButton("메뉴등록");

		
		panel_1.add(btnStockAdd);
	}
	
	void eventProc(){
		btnStockAdd.addActionListener( this );
		tfImage.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfImage.setText( getFileLocation() );
			}
		});
		
	}
	
	public void actionPerformed(ActionEvent e) {
			Object evt = e.getSource();
			if ( evt == btnStockAdd ){
				insert();
			}		
	} 
	
	void insert(){
		model.insert();
	}
	
	
	/* 
	 * 2018.4.19 파일추져 추가 - CLAP
	 * 
	 * */
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
	
	
	/* 데스트 후 주석필요
	 * */
	public static void main(String args[]){
		new MenuAddView();
	}
}
