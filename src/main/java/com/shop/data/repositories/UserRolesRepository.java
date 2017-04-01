package com.shop.data.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	List<String> findRoleByUserLogin(String login);

	UserRole findUserRoleByUserLogin(String login);

	void save(Users user);
}