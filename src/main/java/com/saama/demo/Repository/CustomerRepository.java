package com.saama.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saama.demo.Model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
	


	/*@Query("select c.customerName,c.customerAddress,c.customerPanNumber,"
			+ "o.orderName,od.orderQty\r\n" + 
			"from \r\n" + 
			"Customer c JOIN Orders o ON c.id = o.id  \r\n" + 
			"JOIN OrderDetails od ON o.id = od.id \r\n" + 
			"WHERE od.purchaseAmount > ?1 and c.customerLocation = ?1")
	/*
	 * List<Customer>
	 * findByPurchaseAmountGreaterThanAndCustomerLocation(@Param("purchaseAmount")
	 * double purchaseAmount,
	 * 
	 * @Param("customerLocation") String customerLocation);
	 */
	
	List<Customer> findByCustomerLocation(String customerLocation);


}
