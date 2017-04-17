package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.Order;


public interface OrdersRepository extends CrudRepository<Order, Long>{
	Order findById(Long orderId); 

}
