package com.saama.demo.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.saama.demo.Model.OrderDetails;

/**
 *@author pkumawat
 */

public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long>{
	List<OrderDetails> findByOrdersId(Long orderId);

	List<OrderDetails> findByPurchaseAmountGreaterThan(double purchaseAmount);


}
