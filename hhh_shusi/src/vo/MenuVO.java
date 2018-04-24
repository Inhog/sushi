package vo;

public class MenuVO {
	private String 	menuCode, name, category, price, image;
	

	public MenuVO(){}
	public MenuVO(String menuCode, String name, String price, String category, String image) {
		super();
		this.menuCode = menuCode;
		this.name = name;
		this.price = price;
		this.category = category;
		this.image = image;
	}

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	//같은 menuVO를 가리키거나, menuCode가 같으면 같은 MenuVO
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuVO other = (MenuVO) obj;
		
		if (menuCode == null) {
			if (other.menuCode != null)
				return false;
		} else if (!menuCode.equals(other.menuCode))
			return false;
		
		return true;
	}
	
	
}
