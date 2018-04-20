package model.vo;

public class Stock {
	
	String stock_no;
	String quantity;
	String expiredDate;
	String material_Code;
	String add_date;
	
	public Stock(){
	}
	
	
	public Stock(String stock_no) {
		super();
		this.stock_no = stock_no;
		this.quantity = quantity;
		this.expiredDate = expiredDate;
		this.material_Code = material_Code;
		this.add_date = add_date;
	}


	public Stock(String material_Code, String quantity, String expiredDate) {
		super();
		this.material_Code = material_Code;
		this.quantity = quantity;
		this.expiredDate =expiredDate;
	}
	
	public void setMaterial_Code(String material_Code) {
		this.material_Code = material_Code;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getStock_no() {
		return stock_no;
	}


	public void setStock_no(String stock_no) {
		this.stock_no = stock_no;
	}


	public String getAdd_date() {
		return add_date;
	}


	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}


	public void setExpiredDate(String expiredDate){
		this.expiredDate = expiredDate;
	}
	public String getMaterial_Code() {
		return material_Code;
	}
	public String getQuantity() {
		return quantity;
	}
	public String getExpiredDate(){
		return expiredDate;
		
	}
	
}