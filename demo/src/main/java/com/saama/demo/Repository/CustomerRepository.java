package com.saama.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.saama.demo.Model.Customer;

/**
 *@author pkumawat
 */

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	List<Customer> findByCustomerLocation(String customerLocation);


}
