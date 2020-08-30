package com.saama.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saama.demo.Model.Customer;
import com.saama.demo.Model.OrderDetails;


public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long>{
	List<OrderDetails> findByOrdersId(Long orderId);

	List<OrderDetails> findByPurchaseAmountGreaterThan(double purchaseAmount);


}
