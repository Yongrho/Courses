package com.example.cruddypizza.data;

public class SizeData {

	private long id;
	private long menuId;
	private int menuSize;
	private double menuPrice;

	public SizeData(){}

	public SizeData(long menuId, int menuSize, double menuPrice) {
		this.menuId = menuId;
		this.menuSize = menuSize;
		this.menuPrice = menuPrice;
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

	public int getMenuSize() {
		return menuSize;
	}

	public void setMenuSize(int menuSize) {
		this.menuSize = menuSize;
	}

	public double getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(double menuPrice) {
		this.menuPrice = menuPrice;
	}

	@Override
	public String toString() {
		return "SizeData{" +
				"id=" + id +
				", menuId=" + menuId +
				", menuSize='" + menuSize + '\'' +
				", menuPrice=" + menuPrice +
				'}';
	}
}
