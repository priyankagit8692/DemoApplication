package com.saama.demo.Service;

import java.util.List;

import org.json.simple.JSONArray;


import com.saama.demo.Model.Customer;
import com.saama.demo.Model.OrderDetails;
import com.saama.demo.Model.Orders;

public interface IDemo {
	
	public List<Customer> getAllCustomer();
	
	public List<Orders> getCustomerOrdersByCustomerId(Long customerId);
	
	public Customer getCustomerByID(Long id);
	
	public Customer addCustomer(Customer customer);
	
	public Orders addOrders(Long customerId,Orders orders) ;
	
	public OrderDetails addOrderDetailorderId(Long orderId,OrderDetails orderDetails);
	
	public JSONArray getByPurchaseAmtAndCustomerLocation( double purchaseAmount,
			 String customerLocation);

}
