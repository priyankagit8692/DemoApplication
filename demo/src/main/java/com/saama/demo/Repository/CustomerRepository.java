package com.saama.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saama.demo.Model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByCustomerLocation(String customerLocation);


}
