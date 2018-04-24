package Customer;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Customer {
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	JButton startBtn;
	
	public Customer(){
		startSocket();
	}
	public void startSocket(){
		try {
			socket = new Socket("127.0.0.1", 10001);
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
			
			
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