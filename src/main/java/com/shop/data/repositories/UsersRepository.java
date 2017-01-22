package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.Users;


public interface UsersRepository extends CrudRepository<Users, Long>{ 
	public Users findByLogin(String login);
	public Users findByeMail(String email);
}
