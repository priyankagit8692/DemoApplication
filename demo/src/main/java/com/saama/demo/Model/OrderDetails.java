package com.saama.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *@author pkumawat
 */

@Entity
@Table(name = "orderDetails")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderDetailId;

	@Column(name = "order_qty")
	@Min(2)
	private int orderQty;
	
	@Column(name = "purchase_amt")
	private double purchaseAmount;
	
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
	@JsonIgnore
    private Orders orders;
	
	public OrderDetails() {}

	public OrderDetails(long orderDetailId, int orderQty, double purchaseAmount, Orders orders) {
		this.orderDetailId = orderDetailId;
		this.orderQty = orderQty;
		this.purchaseAmount = purchaseAmount;
		this.orders = orders;
	}

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public double getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailId=" + orderDetailId + ", orderQty=" + orderQty + ", purchaseAmount="
				+ purchaseAmount + ", orders=" + orders + "]";
	}

	
	
}

