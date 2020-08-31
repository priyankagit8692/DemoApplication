package com.saama.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.saama.demo.Model.Orders;

/**
 *@author pkumawat
 */

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	List<Orders> findByCustomerId(Long customerId); 
	
	
}
