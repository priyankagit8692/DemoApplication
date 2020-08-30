package com.saama.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saama.demo.Model.Customer;
import com.saama.demo.Model.OrderDetails;
import com.saama.demo.Model.Orders;
import com.saama.demo.Repository.CustomerRepository;
import com.saama.demo.Repository.OrderDetailRepository;
import com.saama.demo.Service.DemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "Customer and Order Details", description = "Customer and Order Details for Demo",tags = "Demo-Controller")
public class DemoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired 
	DemoService demoService;
	
	@Autowired
	  private CustomerRepository customerRepository;
	@Autowired
	  private OrderDetailRepository orderDetailRepository;
	
	@ApiOperation(value = " Get all the Customers")
	@GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomer() 
	{
	  LOGGER.debug("fetching data of all customers");
	  List<Customer> listCustomers = demoService.getAllCustomer();
	  return new ResponseEntity<List<Customer>>(listCustomers,HttpStatus.OK);
    }
    
	@GetMapping("/customers/{purchaseAmount}/{customerLocation}")
	public JSONArray getAllValues(@PathVariable("purchaseAmount") double purchaseAmount,
			@PathVariable("customerLocation") String customerLocation){
		System.out.println("in method");
		JSONArray jsonResponse = new JSONArray();
		JSONObject j= new JSONObject();
		
	List<Customer> customerDetails = 	customerRepository.findByCustomerLocation(customerLocation);

	for (Customer customer : customerDetails) {
		List <Orders> orderDetails= customer.getOrders();
		
		for (Orders order : orderDetails) {
			Map<String, String> responseMap = new HashMap<String, String>();
			
		OrderDetails orderData = order.getOrderDetails();
		if(orderData.getPurchaseAmount()> purchaseAmount) {
			
			responseMap.put("customerName", customer.getCustomerName());
			responseMap.put("customerPanNo", customer.getCustomerPanNumber());
			responseMap.put("customerAdd", customer.getCustomerAddress());
			responseMap.put("OrderName", order.getOrderName());
			responseMap.put("OrderQty", String.valueOf(orderData.getOrderQty()));
			jsonResponse.add(responseMap);
		
		}
		
		}
		
				}
		
		 return jsonResponse;
	}
	
	@ApiOperation(value = " Get all the Customers with their orders")
    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<List<Orders>> getCustomerOrdersByCustomerId(@PathVariable Long customerId) {
		LOGGER.debug("fetching data of all customers and their orders");
		List<Orders> listOrders = demoService.getCustomerOrdersByCustomerId(customerId);
		return new ResponseEntity<List<Orders>>(listOrders,HttpStatus.OK);
     
    }
    
	@ApiOperation(value = " Get detail of the Customer with specific customer id")
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerByID(@PathVariable Long id) {
		Customer customerEntity = demoService.getCustomerByID(id);
      return new ResponseEntity<Customer>(customerEntity,HttpStatus.OK);
    }
	
	
	
	@ApiOperation(value = " Add a new customer")
	@PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		
		Customer customerCreateEntity = demoService.addCustomer(customer);
        return new ResponseEntity<Customer>(customerCreateEntity,HttpStatus.CREATED);
    }

	
	@ApiOperation(value = " Get orders for the customer")
	@PostMapping("/customer/{customerId}/orders")
    public ResponseEntity<Orders> addOrders(@PathVariable Long customerId,
                            @RequestBody Orders orders) {
		LOGGER.debug("creating order of customer with customer id :"+customerId);
		Orders addOrderentity = demoService.addOrders(customerId, orders);
		return new ResponseEntity<Orders>(addOrderentity,HttpStatus.CREATED);
    }
	 
	@ApiOperation(value = "Add order details for the Customer Orderr")
	 @PostMapping("/orders/{orderId}/orderDetails")
	    public ResponseEntity<OrderDetails> addOrderDetail(@PathVariable Long orderId,
	                             @RequestBody OrderDetails orderDetails) {
		LOGGER.debug("Add order detail for the customer order");
		OrderDetails addOrderDetailEntity = demoService.addOrderDetailorderId(orderId,orderDetails);
		return new ResponseEntity<OrderDetails>(addOrderDetailEntity,HttpStatus.CREATED);
	       
	    }
	
	
}
