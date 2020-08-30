package com.saama.demo.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

}
