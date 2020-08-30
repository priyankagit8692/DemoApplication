package com.saama.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saama.demo.Model.Customer;
import com.saama.demo.Model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	List<Orders> findByCustomerId(Long customerId); 
	
	
}
