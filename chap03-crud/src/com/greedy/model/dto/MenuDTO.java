package com.greedy.model.dto;

public class MenuDTO {
	
	private String menuName;
	private int menuPrice;
	private int category;
	private String orderableStatus;
	
	public MenuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuDTO(String menuName, int menuPrice, int category, String orderableStatus) {
		super();
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.category = category;
		this.orderableStatus = orderableStatus;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getOrderableStatus() {
		return orderableStatus;
	}
	public void setOrderableStatus(String orderableStatus) {
		this.orderableStatus = orderableStatus;
	}
	@Override
	public String toString() {
		return "MenuDTO [menuName=" + menuName + ", menuPrice=" + menuPrice + ", category=" + category
				+ ", orderableStatus=" + orderableStatus + "]";
	}
	
	
}
