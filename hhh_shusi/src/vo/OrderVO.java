package vo;

import java.sql.Date;
/**
  * Created by clap on 2018. 4. 19. 오후 5:49:19
  */
public class OrderVO {
	String 	orderNo, customerNo, menuCode, paymentNo;
	Date 	ordertime;
	
	public OrderVO(){}
	public OrderVO(String orderNo, String customerNo, String menuCode, String paymentNo, Date ordertime) {
		super();
		this.orderNo = orderNo;
		this.customerNo = customerNo;
		this.menuCode = menuCode;
		this.paymentNo = paymentNo;
		this.ordertime = ordertime;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
	
}
