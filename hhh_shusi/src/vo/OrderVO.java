package vo;

/**
  * Created by clap on 2018. 4. 19. 오후 5:49:19
  */
public class OrderVO {
<<<<<<< HEAD
	String 	orderNo, customerNo, menuCode, paymentNo, ordertime;
	
	public OrderVO(){}
	public OrderVO(String customerNo, String menuCode){
		super();
		this.customerNo = customerNo;
		this.menuCode = menuCode;
	}
=======
	String 	orderNo, customerNo, menuCode, paymentNo,ordertime;
	
	public OrderVO(){}
>>>>>>> 7e54b9d0aa2d67b248e7bda55e5d6e53f1c8fdae
	public OrderVO(String orderNo, String customerNo, String menuCode, String paymentNo, String ordertime) {
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
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	
	
}
