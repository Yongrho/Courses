package com.example.cruddypizza.data;

import java.util.ArrayList;

public class MenuData {

	private long id;
	private int menuName;
	private int menuImage;
	private int menuSize;
	private double menuPrice;
	private int menuPromoted;
	private ArrayList<ToppingData> arrayTopping;

	public MenuData(){}

	public MenuData(int menuName, int menuImage, int menuPromoted) {
		this.menuName = menuName;
		this.menuImage = menuImage;
		this.menuPromoted = menuPromoted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMenuName() {
		return menuName;
	}

	public void setMenuName(int menuName) {
		this.menuName = menuName;
	}

	public int getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(int menuImage) {
		this.menuImage = menuImage;
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

	public ArrayList<ToppingData> getMenuTopping() {
		return arrayTopping;
	}

	public void setMenuTopping(ArrayList<ToppingData> arrayTopping) {
		this.arrayTopping = arrayTopping;
	}

	public int getMenuPromoted() {
		return menuPromoted;
	}

	public void setMenuPromoted(int menuPromoted) {
		this.menuPromoted = menuPromoted;
	}

	@Override
	public String toString() {
		return "MenuData{" +
				"id=" + id +
				", menuName='" + menuName + '\'' +
				", menuImage='" + menuImage + '\'' +
				", menuSize='" + menuSize + '\'' +
				", menuPrice=" + menuPrice + '\'' +
				", menuPromoted=" + menuPromoted +
				'}';
	}
}
