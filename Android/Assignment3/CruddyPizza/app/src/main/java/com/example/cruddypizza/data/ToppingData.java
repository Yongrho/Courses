package com.example.cruddypizza.data;

public class ToppingData {

	private long id;
	private long menuId;
	private int toppingName;
	private double toppingPrice;

	public ToppingData(){}

	public ToppingData(long menuId, int toppingName, double toppingPrice) {
		this.menuId = menuId;
		this.toppingName = toppingName;
		this.toppingPrice = toppingPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public int getToppingName() {
		return toppingName;
	}

	public void setToppingName(int toppingName) {
		this.toppingName = toppingName;
	}

	public double getToppingPrice() {
		return toppingPrice;
	}

	public void setToppingPrice(double toppingPrice) {
		this.toppingPrice = toppingPrice;
	}

	@Override
	public String toString() {
		return "ToppingData{" +
				"id=" + id +
				", menuId=" + menuId +
				", toppingName='" + toppingName + '\'' +
				", toppingPrice=" + toppingPrice +
				'}';
	}
}
