package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.Orders;


public interface OrdersRepository extends CrudRepository<Orders, Long>{
	Orders findById(Long orderId); 

}
