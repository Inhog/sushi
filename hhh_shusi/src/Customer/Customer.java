package Customer;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Create by Inho 2018. 4. 23. 오후 11:43:17

public class Customer {
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	
	public Customer(){
		startSocket();
	}
	public void startSocket(){
		try {
			// Socket(IPaddress,Portno)
			socket = new Socket("127.0.0.1", 10001);
			// 소켓통신에 output, input Stream을 정해준다.
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			// Runnable 객체로 생성하여 Thread로 구동시킨다??
			Runnable r = new orderView(socket);
			Thread t = new Thread(r);
			t.start();
		} catch (Exception e) {
			System.out.println("소켓통신 실패:"+e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Customer();					
	}
}