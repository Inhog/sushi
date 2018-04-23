package sushistore;

import java.io.*;
import java.net.*;
import java.sql.*;

// Create by Inho 2018. 4. 23. 오후 11:45:10

class Table extends Thread{
	
	Sushi_Store store;
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	Connection con;
	
	Table(Sushi_Store store, Socket socket, Connection con) throws Exception{		// 생성자, 소켓환경 구축
		this.store = store;
		this.socket = socket;
		this.con = con;
		InetAddress inetaddr = socket.getInetAddress();
        System.out.println(inetaddr.getHostAddress()+ " 에서 접속했습니다.");
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
	}
	
	
	public void run(){
		try{
			String line;
			while((line = br.readLine())!=null){
				System.out.println(line+"읽음");
				
				String array[] = line.split("\\|token\\|");
				// "|token|"으로 String 나눔.
				switch(array[0]){		// array[0] : 키워드
				case "table":			// array[1] : TableNo
					break;
				case "Order":			// array[1] : tOrderNo , array[2] : CustomerNo
										// , array[3] : MenuCode, array[4] : PaymentNo, array[5] :Ordertime
					
					break;
				}
				
			}
			pw.close();
			br.close();
			con.close();
			socket.close();
		} catch(Exception e){
			System.out.println("테이블 접속 실패 : "+e.getMessage());
		}
	}
	
}