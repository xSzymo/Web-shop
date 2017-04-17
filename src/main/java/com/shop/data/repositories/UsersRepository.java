package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	public User findByLogin(String login);

	public User findByeMail(String email);

	public User findById(Long bookId);
}
