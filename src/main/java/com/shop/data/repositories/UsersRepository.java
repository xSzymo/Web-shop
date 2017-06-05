package com.shop.data.repositories;

import com.shop.data.tables.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);

    User findByeMail(String email);

    User findById(Long bookId);
}
