package com.example.cruddypizza.data;

public class OrderData {

	private long id;
	private long orderMenuId;
	private String orderDate;
	private int orderQuantity;
	private double orderTotalPrice;
	private String orderCustomerName;
	private String orderCustomerEmail;
	private String orderCustomerAddress;
	private String orderCustomerPhone;

	public OrderData(){}

	public OrderData(long orderMenuId, String orderDate, int orderQuantity, String orderCustomerName, String orderCustomerEmail, String orderCustomerAddress, String orderCustomerPhone) {
		this.orderMenuId = orderMenuId;
		this.orderDate = orderDate;
		this.orderQuantity = orderQuantity;
		this.orderCustomerName = orderCustomerName;
		this.orderCustomerEmail = orderCustomerEmail;
		this.orderCustomerAddress = orderCustomerAddress;
		this.orderCustomerPhone = orderCustomerPhone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderMenuId() {
		return orderMenuId;
	}

	public void setOrderMenuId(long orderMenuId) {
		this.orderMenuId = orderMenuId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public double getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(double orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	public String getOrderCustomerName() {
		return orderCustomerName;
	}

	public void setOrderCustomerName(String orderCustomerName) {
		this.orderCustomerName = orderCustomerName;
	}

	public String getOrderCustomerEmail() {
		return orderCustomerEmail;
	}

	public void setOrderCustomerEmail(String orderCustomerEmail) {
		this.orderCustomerEmail = orderCustomerEmail;
	}

	public String getOrderCustomerAddress() {
		return orderCustomerAddress;
	}

	public void setOrderCustomerAddress(String orderCustomerAddress) {
		this.orderCustomerAddress = orderCustomerAddress;
	}

	public String getOrderCustomerPhone() {
		return orderCustomerPhone;
	}

	public void setOrderCustomerPhone(String orderCustomerPhone) {
		this.orderCustomerPhone = orderCustomerPhone;
	}

	@Override
	public String toString() {
		return "OrderData{" +
				"id=" + id +
				", orderMenuId='" + orderMenuId + '\'' +
				", orderDate='" + orderDate + '\'' +
				", orderQuantity=" + orderQuantity +
				", orderCustomerName='" + orderCustomerName + '\'' +
				", orderCustomerAddress='" + orderCustomerAddress + '\'' +
				", orderCustomerPhone='" + orderCustomerPhone + '\'' +
				'}';
	}
}
