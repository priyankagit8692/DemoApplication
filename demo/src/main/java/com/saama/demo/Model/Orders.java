package com.saama.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import io.swagger.annotations.ApiModel;

/**
 *@author pkumawat
 */

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel("Order from Customer")

public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "order_name")
	@Size(min = 4,message = "Order Name should have atleast 4 character")
	private String orderName;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", nullable = false)
	@JsonIgnore
	private Customer customer;
	
	
	@OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "orders")
	private OrderDetails orderDetails;

	public Orders() {}

	public Orders(long id, String orderName, Customer customer, OrderDetails orderDetails) {
		
		this.id = id;
		this.orderName = orderName;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", orderName=" + orderName + ", customer=" + customer + ", orderDetails="
				+ orderDetails + "]";
	}
	

}
