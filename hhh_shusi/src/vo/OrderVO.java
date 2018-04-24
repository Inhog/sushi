package vo;

/**
  * Created by clap on 2018. 4. 19. 오후 5:49:19
  */
public class OrderVO {
	String 	orderNo, customerNo, menuCode, paymentNo, ordertime, menuName, menuPrice;
	
	public OrderVO(){}
	public OrderVO(String customerNo, String menuCode){
		super();
		this.customerNo = customerNo;
		this.menuCode = menuCode;
	}

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
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}
}
