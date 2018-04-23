package sushistore;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

import vo.OrderVO;

class Table extends Thread{
	// 정보를 받을 객체? 선언해야하고
	Sushi_Store store;
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	Connection con;
	
	Table(Sushi_Store store, Socket socket, Connection con) throws Exception{
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
				/* 인호*** array배열에 어떤형식으로 string이 쪼개지는지 알려주삼*/
				/* 수형*** customerModel 생성 for "table"
				 * 		  "order는 .. orderModel에 있는거에 쓰면 됨.*/
				switch(array[0]){
				case "table":
					
					break;
				case "Order":
					/*Table_orderView의 
					 * addOrder(ArrayList<OrderVO> orderVOList)쓰면 직방임, orderVOList 체크 바람.
					 * 참고 : sourceCode: 389~408 */
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