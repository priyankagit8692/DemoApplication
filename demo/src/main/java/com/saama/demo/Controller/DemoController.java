package com.saama.demo.Controller;



import java.util.List;
import javax.validation.Valid;
import org.json.simple.JSONArray;
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
import com.saama.demo.Service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.saama.demo.common.PathConstants;

/**
 *@author pkumawat
 */

@RestController
@RequestMapping(value = PathConstants.BASE_PATH)
@Api(value = "Customer and Order Details", description = "Customer and Order Details for Demo",tags = "Demo-Controller")
public class DemoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired 
	DemoService demoService;
		
	@ApiOperation(value = " Add a new customer")
	@PostMapping(value = PathConstants.CUSTOMER)
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
		
		Customer customerCreateEntity = demoService.addCustomer(customer);
        return new ResponseEntity<Customer>(customerCreateEntity,HttpStatus.CREATED);
    }

	
	@ApiOperation(value = " Add orders for the customer with cutomer id")
	@PostMapping(value = PathConstants.CUSTOMER + PathConstants.CUSTOMER_ID + PathConstants.ORDERS)
    public ResponseEntity<Orders> addOrders(@PathVariable("customerId")  Long customerId,
                            @Valid @RequestBody Orders orders) {
		LOGGER.debug("creating order of customer with customer id :"+customerId);
		Orders addOrderentity = demoService.addOrders(customerId, orders);
		return new ResponseEntity<Orders>(addOrderentity,HttpStatus.CREATED);
    }
	 
	
	@ApiOperation(value = "Add order details for the Customer Orderr")
	 @PostMapping(value = PathConstants.ORDERS + PathConstants.ORDER_ID +PathConstants.ORDER_DETAILS)
	    public ResponseEntity<OrderDetails> addOrderDetail(@PathVariable Long orderId,
	                            @Valid @RequestBody OrderDetails orderDetails) {
		LOGGER.debug("Add order detail for the customer order");
		OrderDetails addOrderDetailEntity = demoService.addOrderDetailorderId(orderId,orderDetails);
		return new ResponseEntity<OrderDetails>(addOrderDetailEntity,HttpStatus.CREATED);
	       
	    }
	
	@ApiOperation(value = " Get all the Customers")
	@GetMapping(value = PathConstants.CUSTOMER)
    public ResponseEntity<List<Customer>> getAllCustomer() 
	{
	  LOGGER.debug("fetching data of all customers");
	  List<Customer> listCustomers = demoService.getAllCustomer();
	  return new ResponseEntity<List<Customer>>(listCustomers,HttpStatus.OK);
    }
	
	
	@ApiOperation(value = " Get all the Customers Customer location and Purchase Amount greater than specific amount")
	@GetMapping(value = PathConstants.CUSTOMER + PathConstants.PURCHASE_AMT + PathConstants.CUSTOMER_LOCATION )
	public ResponseEntity<JSONArray> getByPurchaseAmtAndCustomerLocation(@PathVariable("purchaseAmount") double purchaseAmount,
			@PathVariable("customerLocation") String customerLocation){
		
		JSONArray jsonResp = demoService.getByPurchaseAmtAndCustomerLocation(purchaseAmount, customerLocation);
		return new ResponseEntity<JSONArray>(jsonResp,HttpStatus.OK);
			
	}
	
	@ApiOperation(value = " Get all the Order of specific customer")
    @GetMapping(value = PathConstants.CUSTOMER + PathConstants.ORDERS + PathConstants.CUSTOMER_ID)
    public ResponseEntity<List<Orders>> getCustomerOrdersByCustomerId(@PathVariable("customerId") 
    		Long customerId) {
		LOGGER.debug("fetching data of all customers and their orders");
		List<Orders> listOrders = demoService.getCustomerOrdersByCustomerId(customerId);
		return new ResponseEntity<List<Orders>>(listOrders,HttpStatus.OK);
     
    }
    
	@ApiOperation(value = " Get details of the Customer with specific customer id")
    @GetMapping(value = PathConstants.CUSTOMER + PathConstants.ORDER_DETAILS + PathConstants.CUSTOMER_ID)
    public ResponseEntity<Customer> getCustomerByID(@PathVariable("customerId") Long id) {
		Customer customerEntity = demoService.getCustomerByID(id);
      return new ResponseEntity<Customer>(customerEntity,HttpStatus.OK);
    }
	
	
	
	
	
}
