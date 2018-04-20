package vo;

import java.sql.Date;
/**
  * Created by clap on 2018. 4. 19. 오후 5:49:11
  */
public class StockVO {
<<<<<<< HEAD
	String 	stockCode, materialCode;
	int 	quantity;
	Date	expiredDate, AddDate;
	
	public StockVO(){}
	public StockVO(String stockCode, String materialCode, int quantity, Date expiredDate, Date addDate) {
		super();
		this.stockCode = stockCode;
=======
	String 	stockNo, materialCode, quantity, expiredDate, AddDate;
	
	public StockVO(){}
	
	public StockVO(String stockNo) {
		super();
		this.stockNo = stockNo;
	}


	public StockVO(String materialCode, String quantity, String expiredDate) {
		super();
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.expiredDate =expiredDate;
	}
	
	public StockVO(String stockCode, String materialCode, String quantity, String expiredDate, String addDate) {
		super();
		this.stockNo = stockCode;
>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.expiredDate = expiredDate;
		AddDate = addDate;
	}
	
<<<<<<< HEAD
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
=======
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
<<<<<<< HEAD
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	public Date getAddDate() {
		return AddDate;
	}
	public void setAddDate(Date addDate) {
=======
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getAddDate() {
		return AddDate;
	}
	public void setAddDate(String addDate) {
>>>>>>> 2ac9d067450a4a91dc7b1bd61bad6c5422e4e733
		AddDate = addDate;
	}
	
	
}
