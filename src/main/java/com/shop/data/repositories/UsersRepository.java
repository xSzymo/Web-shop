package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.Users;


public interface UsersRepository extends CrudRepository<Users, Long>{ 

}
