package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentView extends JFrame implements ActionListener {
	private JTextField tfPaymentNum, tfChang, tfTotalCash, tfReceiveCash, tfReturnCash;
	private JLabel lbPaymentNum, lbTotalCash, lbReceiveCash, lbReturnCash;
	private JButton btCashPayment, btCardPayment;

	public void addLayout() {
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(14, 12, 268, 371);
		getContentPane().add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(287, 12, 268, 371);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lbPaymentNum = new JLabel("결제번호");
		lbPaymentNum.setBounds(25, 25, 62, 18);
		panel_1.add(lbPaymentNum);

		tfPaymentNum = new JTextField();
		tfPaymentNum.setBounds(116, 22, 116, 24);
		panel_1.add(tfPaymentNum);
		tfPaymentNum.setColumns(10);

		tfChang = new JTextField();
		tfChang.setBounds(25, 66, 207, 86);
		panel_1.add(tfChang);
		tfChang.setColumns(10);

		JLabel lbTotalCash = new JLabel("총 금액");
		lbTotalCash.setBounds(25, 182, 62, 18);
		panel_1.add(lbTotalCash);

		JLabel lbReceiveCash = new JLabel("받은금액");
		lbReceiveCash.setBounds(25, 214, 62, 18);
		panel_1.add(lbReceiveCash);

		JLabel lbReturnCash = new JLabel("반환금액");
		lbReturnCash.setBounds(25, 244, 62, 18);
		panel_1.add(lbReturnCash);

		tfTotalCash = new JTextField();
		tfTotalCash.setColumns(10);
		tfTotalCash.setBounds(116, 179, 116, 24);
		panel_1.add(tfTotalCash);

		tfReceiveCash = new JTextField();
		tfReceiveCash.setColumns(10);
		tfReceiveCash.setBounds(116, 211, 116, 24);
		panel_1.add(tfReceiveCash);

		tfReturnCash = new JTextField();
		tfReturnCash.setColumns(10);
		tfReturnCash.setBounds(116, 241, 116, 24);
		panel_1.add(tfReturnCash);

		btCashPayment = new JButton("현금 결제");
		btCashPayment.setBounds(14, 306, 105, 27);
		panel_1.add(btCashPayment);

		btCardPayment = new JButton("카드 결제 ");
		btCardPayment.setBounds(133, 306, 105, 27);
		panel_1.add(btCardPayment);
	}

	public void eventProc() {
		btCashPayment.addActionListener(this);
		btCardPayment.addActionListener(this);
		tfReceiveCash.addActionListener(this);
	}

	public PaymentView() {
		addLayout();
		eventProc();
		setSize(800, 600);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		Object evt = ev.getSource();
		if (evt == btCashPayment) {
			int total = Integer.parseInt(tfTotalCash.getText());							//총 금액을 저장
			int rece = Integer.parseInt(tfReceiveCash.getText());							//받은 금액을 문자열로 저장
			int Rt = Integer.parseInt(tfReturnCash.getText());
			Rt = rece - total;
			JOptionPane.showMessageDialog(null, "총금액:"+total+"\n받은금액:"+rece+"\n잔돈:"+Rt);
		}else if(evt == btCashPayment){
			
		}else if(evt ==  tfReceiveCash){
			int total = Integer.parseInt(tfTotalCash.getText());
			int rece = Integer.parseInt(tfReceiveCash.getText());
			int Rt = rece - total;																//거슬러줄 금액
			tfReturnCash.setText(String.valueOf(Rt));
		}
	}

	public static void main(String[] args) {
		new PaymentView();
	}

}
