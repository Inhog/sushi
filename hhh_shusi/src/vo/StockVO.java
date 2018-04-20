package vo;

import java.sql.Date;
/**
  * Created by clap on 2018. 4. 19. 오후 5:49:11
  */
public class StockVO {
	String 	stockCode, materialCode;
	int 	quantity;
	Date	expiredDate, AddDate;
	
	public StockVO(){}
	public StockVO(String stockCode, String materialCode, int quantity, Date expiredDate, Date addDate) {
		super();
		this.stockCode = stockCode;
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.expiredDate = expiredDate;
		AddDate = addDate;
	}
	
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
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
		AddDate = addDate;
	}
	
	
}
