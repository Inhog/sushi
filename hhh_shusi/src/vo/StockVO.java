package vo;

import java.sql.Date;
/**
  * Created by clap on 2018. 4. 19. 오후 5:49:11
  */
public class StockVO {
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
	
	
	
	public StockVO(String stockNo, String materialCode, String quantity, String AddDate) {
		super();
		this.stockNo = stockNo;
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.AddDate = AddDate;
	}

	public StockVO(String stockCode, String materialCode, String quantity, String expiredDate, String addDate) {
		super();
		this.stockNo = stockCode;
		this.materialCode = materialCode;
		this.quantity = quantity;
		this.expiredDate = expiredDate;
		AddDate = addDate;
	}
	
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
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
		AddDate = addDate;
	}
	
	
}
