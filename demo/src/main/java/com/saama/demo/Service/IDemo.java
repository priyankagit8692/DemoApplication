package com.saama.demo.Service;

import java.util.List;

import org.json.simple.JSONArray;


import com.saama.demo.Model.Customer;
import com.saama.demo.Model.OrderDetails;
import com.saama.demo.Model.Orders;

public interface IDemo {
	
	/**
	 * 
	 * @param customer
	 * @return Customer pojo having all the customer details 
	 */
	public Customer addCustomer(Customer customer);
	
	/**
	 * 
	 * @return fetch all the customers with their order and order details
	 */
	public List<Customer> getAllCustomer();
	
	/**
	 * 
	 * @param customerId
	 * @return order of the customer with customer Id
	 */
	public List<Orders> getCustomerOrdersByCustomerId(Long customerId);
	
	/**
	 * 
	 * @param id
	 * @return all details of the customer with customer id
	 */
	public Customer getCustomerByID(Long id);
	
	
	/**
	 * 
	 * @param customerId
	 * @param orders
	 * @return Adding orders of the customer with customer Id
	 */
	public Orders addOrders(Long customerId,Orders orders) ;
	
	/**
	 * 
	 * @param orderId
	 * @param orderDetails
	 * @return Add Order details of the customer with Order Id
	 */
	public OrderDetails addOrderDetailorderId(Long orderId,OrderDetails orderDetails);
	
	/**
	 * 
	 * @param purchaseAmount
	 * @param customerLocation
	 * @return Filter Customer with Purchase Amount greater than Amount and from Customer Location
	 */
	public JSONArray getByPurchaseAmtAndCustomerLocation( double purchaseAmount,
			 String customerLocation);

}
