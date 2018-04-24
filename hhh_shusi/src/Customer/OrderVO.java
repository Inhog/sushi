package Customer;

public class OrderVO {
	String	order_no;
	String	customer_no;
	String	menu_code;
	String	payment_no;
	
	public OrderVO(String order_no, String customer_no, String menu_code, String payment_no) {
		this.order_no = order_no;
		this.customer_no = customer_no;
		this.menu_code = menu_code;
		this.payment_no = payment_no;
	}
	
	public OrderVO() {}
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(String customer_no) {
		this.customer_no = customer_no;
	}
	public String getMenu_code() {
		return menu_code;
	}
	public void setMenu_code(String menu_code) {
		this.menu_code = menu_code;
	}
	public String getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}
	
	
	
}
