package com.shop.data.repositories;

import com.shop.data.tables.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
    Order findById(Long orderId);

}
