package com.saama.demo.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.saama.demo.Controller.DemoController;
import com.saama.demo.Exception.NotFoundException;
import com.saama.demo.Model.Customer;
import com.saama.demo.Model.OrderDetails;
import com.saama.demo.Model.Orders;
import com.saama.demo.Repository.CustomerRepository;
import com.saama.demo.Repository.OrderDetailRepository;
import com.saama.demo.Repository.OrdersRepository;

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
		   	 throw new NotFoundException("Customer not found");
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
	        throw new NotFoundException("Customer not found with id " + id);
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
	            }).orElseThrow(() -> new NotFoundException("Customer not found!"));
	}

	@Override
	public OrderDetails addOrderDetailorderId(Long orderId, OrderDetails orderDetails) {
		 return orderRepository.findById(orderId)
	                .map(orders -> {
	                	orderDetails.setOrders(orders);
	                    return orderDetailRepository.save(orderDetails);
	                }).orElseThrow(() -> new NotFoundException("Student not found!"));
	}


	
		

	

	

	

	
	
			

}
