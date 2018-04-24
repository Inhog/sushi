package Customer;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Create by Inho 2018. 4. 23. 오후 11:43:17

public class Customer2 extends JFrame{
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	
	public Customer2(){
		try {
			// Socket(IPaddress,Portno)
			socket = new Socket("M150112", 10001);

			// 소켓통신에 output, input Stream을 정해준다.
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			// Runnable 객체로 생성하여 Thread로 구동시킨다??
			JButton Join_Button = new JButton("접속");
			getContentPane().add(Join_Button, BorderLayout.CENTER);
			Join_Button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						Runnable r = new orderView2(socket);
						Thread t = new Thread(r);
						t.start();
						setVisible(false);
					} catch (Exception e) {
						System.out.println("Customer 실패:"+e.getMessage());
					}
				}
			});
			
		} catch (Exception e) {
			System.out.println("소켓통신 실패:"+e.getMessage());
		}		setSize(800,600);
		setVisible(true);
	}


	public static void main(String[] args) {
		new Customer2();					
	}
}