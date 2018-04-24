package view;

import java.awt.event.*;
import javax.swing.*;

import Customer.orderModel;
import model.PaymentModel;
import sushistore.Sushi_Store;

public class PaymentView extends JFrame implements ActionListener {
	private JTextField tfPaymentNum, tfTotalCash, tfReceiveCash, tfReturnCash;
	private JTextArea taChang;
	private JLabel lbPaymentNum, lbTotalCash, lbReceiveCash, lbReturnCash;
	private JButton btCashPayment, btCardPayment;
	public JTable orderLIst;
	public PaymentModel paymentModel;
	String customerNo,totalPrice;
	Table_orderView table_orderView;
	
	public void addLayout() {
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(25, 10, 268, 322);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lbPaymentNum = new JLabel("결제번호");
		lbPaymentNum.setBounds(25, 25, 62, 18);
		panel_1.add(lbPaymentNum);

		tfPaymentNum = new JTextField();
		tfPaymentNum.setEditable(false);
		tfPaymentNum.setText(customerNo);
		tfPaymentNum.setBounds(116, 22, 116, 24);
		panel_1.add(tfPaymentNum);
		
		taChang = new JTextArea();
		taChang.setText("현금 결제시 받은 금액을 입력한 뒤 \n 현금 결제 버튼을 눌러주세요. \n\n 카드 결제시 카드 결제 버튼을 누른 뒤 \n 카드를 이용해 결제를 시도해주세요.");
		
		taChang.setEditable(false);
		taChang.setBounds(25, 66, 207, 110);
		panel_1.add(taChang);

		JLabel lbTotalCash = new JLabel("총 금액");
		lbTotalCash.setBounds(25, 189, 62, 18);
		panel_1.add(lbTotalCash);

		JLabel lbReceiveCash = new JLabel("받은금액");
		lbReceiveCash.setBounds(25, 221, 62, 18);
		panel_1.add(lbReceiveCash);

		JLabel lbReturnCash = new JLabel("반환금액");
		lbReturnCash.setBounds(25, 251, 62, 18);
		panel_1.add(lbReturnCash);

		tfTotalCash = new JTextField();
		tfTotalCash.setEditable(false);
		tfTotalCash.setText(totalPrice);
		tfTotalCash.setBounds(116, 186, 116, 24);
		panel_1.add(tfTotalCash);

		tfReceiveCash = new JTextField();
		tfReceiveCash.setBounds(116, 218, 116, 24);
		panel_1.add(tfReceiveCash);

		tfReturnCash = new JTextField();
		tfReturnCash.setEditable(false);
		tfReturnCash.setBounds(116, 248, 116, 24);
		panel_1.add(tfReturnCash);

		btCashPayment = new JButton("현금 결제");
		btCashPayment.setBounds(12, 279, 105, 27);
		panel_1.add(btCashPayment);

		btCardPayment = new JButton("카드 결제 ");
		btCardPayment.setBounds(131, 279, 105, 27);
		panel_1.add(btCardPayment);
	}

	public void eventProc() {
		btCashPayment.addActionListener(this);
		btCardPayment.addActionListener(this);
		tfReceiveCash.addActionListener(this);
	}

	public PaymentView(String customerNo,Table_orderView table_orderView) {
		this.customerNo = customerNo;
		this.table_orderView = table_orderView;
		connectDB();
		getTotalPrice();
		addLayout();
		eventProc();
		setSize(326, 375);
		setResizable(false);
		setVisible(true);
		
	}
	
	public void connectDB(){	// DB 연결 메서드
		try{
			paymentModel = new PaymentModel();
			System.out.println("고객주문화면 디비연결 성공");
		}catch(Exception ex){
			System.out.println("고객주문화면 연결실패 :" +ex.getMessage());
		}
		
	}
	public void getPaymentNo(String method){
		paymentModel.getPaymentNo(totalPrice,method);

	}
	public void setPaymentNo(){
		paymentModel.setPaymentNo(customerNo);
	}
	
	public void getTotalPrice(){
		totalPrice = paymentModel.getTotalPrice(customerNo);
	}
	
	public void actionPerformed(ActionEvent ev) {
		Object evt = ev.getSource();
		if(evt == btCashPayment){
			int ItotalPrice = Integer.valueOf(tfTotalCash.getText());
			int IreceivePrice = Integer.valueOf(tfReceiveCash.getText());
			
			tfReturnCash.setText(String.valueOf(IreceivePrice-ItotalPrice));
			JOptionPane.showMessageDialog(null, "반환금액 : "+(IreceivePrice-ItotalPrice));
			setPaymentNo();
			getPaymentNo("현금");
			setVisible(false);
			table_orderView.setVisible(false);

		}else if(evt == btCardPayment){
			int ItotalPrice = Integer.valueOf(tfTotalCash.getText());
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("결제실패...:"+e.getMessage());
			}
			setPaymentNo();
			getPaymentNo("카드");
			JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.");
			setVisible(false);
			table_orderView.setVisible(false);

		}
	}
}