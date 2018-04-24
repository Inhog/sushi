package sushistore;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

import model.CustomerModel;
import model.OrderModel;
import vo.OrderVO;

// Create by Inho 2018. 4. 23. 오후 11:45:10

class Table extends Thread{
	
	Sushi_Store store;
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	Connection con;
	CustomerModel customerModel;
	OrderModel	orderModel;
	OrderVO orderVO;
	
	Table(Sushi_Store store, Socket socket, Connection con) throws Exception{		// 생성자, 소켓환경 구축
		this.store = store;
		this.socket = socket;
		this.con = con;
		customerModel = new CustomerModel();
		orderModel = new OrderModel();
		orderVO = new OrderVO();
		InetAddress inetaddr = socket.getInetAddress();
        System.out.println(inetaddr.getHostAddress()+ " 에서 접속했습니다.");
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
	}
	
	public void addOrder(ArrayList<OrderVO> orderVOList) {
		
		//orderVO를 하나씩 넘겨줌
		for (int i = 0; i < orderVOList.size(); i++) {
			orderModel.addOrder(orderVOList.get(i));	
		}
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
					// TableNo를 받으면 SQL 문으로 CustomerNO를 DB에 생성하고
					// 생성한 CustomerNo을 다시 가져와서 해당 테이블에 던져준다.
					String Table_customerNo = "CUSTOMERNO"+ "|token|";
					customerModel.addCustomerNO(array[1]);
					Table_customerNo += customerModel.getCustomerNO(array[1]);
					pw.println(Table_customerNo);
					pw.flush();
					break;
				case "Order":			// array[1] : OrderNo , array[2] : CustomerNo
										// , array[3] : MenuCode, array[4] : PaymentNo, array[5] :Ordertime
					ArrayList<OrderVO> orderVOList = new ArrayList<OrderVO>();
					orderVO.setOrderNo(array[1]);
					orderVO.setCustomerNo(array[2]);
					orderVO.setMenuCode(array[3]);
					orderVO.setOrdertime(array[5]);
					orderVOList.add(orderVO);
					addOrder(orderVOList);
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