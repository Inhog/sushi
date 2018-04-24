package Customer;

public class MenuVO {
	String menuCode;
	String name;
	String price;
	String category;
	String imageloc;
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageloc() {
		return imageloc;
	}
	public void setImageloc(String imageloc) {
		this.imageloc = imageloc;
	}
	public MenuVO() {
	}
	public MenuVO(String menuCode, String name, String price, String category, String imageloc) {
		super();
		this.menuCode = menuCode;
		this.name = name;
		this.price = price;
		this.category = category;
		this.imageloc = imageloc;
	}
}
