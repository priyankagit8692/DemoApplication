package com.saama.demo.Model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;


@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ApiModel("All the Customer Details")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	
	@Column(name = "customer_name")
	@Size(min=5, message="Name should have atleast 2 characters")
	private String customerName;
	
	@Column(name = "customer_address")
	@NotNull
	private String customerAddress;
	
	@Column(name = "customer_location")
	@NotNull
	@Size(min=2,message="Location should have atleast 2 character")
	private String customerLocation;
	
	
	@Column(name = "customer_pan_number")
	@NotNull
	@Size(min=6, max=9,message="Pan Card should have atleast 6 characters and maximum 9 characters")
	private String customerPanNumber;
	
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;

	public Customer() {}

	
	public Customer(long id, String customerName, String customerAddress, String customerLocation, String customerPanNumber,
			List<Orders> orders) {
		
		this.id = id;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerLocation = customerLocation;
		this.customerPanNumber = customerPanNumber;
		this.orders = orders;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerLocation() {
		return customerLocation;
	}

	public void setCustomerLocation(String customerLocation) {
		this.customerLocation = customerLocation;
	}

	public String getCustomerPanNumber() {
		return customerPanNumber;
	}

	public void setCustomerPanNumber(String customerPanNumber) {
		this.customerPanNumber = customerPanNumber;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", customerAddress=" + customerAddress
				+ ", customerLocation=" + customerLocation + ", customerPanNumber=" + customerPanNumber + ", orders="
				+ orders + "]";
	}

	
	
}
	
	