package com.saama.demo.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saama.demo.Exception.CustomerNotFoundException;
import com.saama.demo.Model.Customer;
import com.saama.demo.Model.OrderDetails;
import com.saama.demo.Model.Orders;
import com.saama.demo.Repository.CustomerRepository;
import com.saama.demo.Repository.OrderDetailRepository;
import com.saama.demo.Repository.OrdersRepository;


/**
 *@author pkumawat
 */
@Service
public class DemoService implements IDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoService.class);
	
	@Autowired
	  private CustomerRepository customerRepository;
	
	@Autowired
	  private OrdersRepository orderRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<Customer> getAllCustomer() {
	      return customerRepository.findAll();
	}
	
	@Override
	public List<Orders> getCustomerOrdersByCustomerId(Long customerId) {
		if(!customerRepository.existsById(customerId)) {
		   	 throw new CustomerNotFoundException("Customer not found");
		    }
		return orderRepository.findByCustomerId(customerId);
	}
	

	@Override
	public Customer getCustomerByID(Long id) {
		 Optional<Customer> optStudent = customerRepository.findById(id);
	      if(optStudent.isPresent()) {
	    	LOGGER.debug("fetching details of the customer with id: "+id);
	        return optStudent.get();
	      }else {
	    	 LOGGER.error("Customer not found with id " + id); 
	        throw new CustomerNotFoundException("Customer not found with id " + id);
	      }
	}

	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Orders addOrders(Long customerId, Orders orders) {
		return customerRepository.findById(customerId)
	            .map(customer -> {
	                orders.setCustomer(customer);
	                return orderRepository.save(orders);
	            }).orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
	}

	@Override
	public OrderDetails addOrderDetailorderId(Long orderId, OrderDetails orderDetails) {
		 return orderRepository.findById(orderId)
	                .map(orders -> {
	                	orderDetails.setOrders(orders);
	                    return orderDetailRepository.save(orderDetails);
	                }).orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));
	}

	
	@Override
	public JSONArray getByPurchaseAmtAndCustomerLocation(double purchaseAmount, String customerLocation) {
		JSONArray jsonResponse = new JSONArray();
		JSONObject j= new JSONObject();
		
	    List<Customer> customerDetails = customerRepository.findByCustomerLocation(customerLocation);

		for (Customer customer : customerDetails) {
			List <Orders> orderDetails= customer.getOrders();
			
			for (Orders order : orderDetails) {
				Map<String, String> responseMap = new HashMap<String, String>();
				
			OrderDetails orderData = order.getOrderDetails();
			if(orderData.getPurchaseAmount()> purchaseAmount) {
				
				responseMap.put("customerName", customer.getCustomerName());
				responseMap.put("customerPanNumber", customer.getCustomerPanNumber());
				responseMap.put("customerAddress", customer.getCustomerAddress());
				responseMap.put("orderName", order.getOrderName());
				responseMap.put("orderQty", String.valueOf(orderData.getOrderQty()));
				jsonResponse.add(responseMap);
			}
			
			}
		}
		return jsonResponse;
	}


	
		

	

	

	

	
	
			

}
