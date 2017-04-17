package com.shop.data.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.User;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	List<String> findRoleByUserLogin(String login);

	UserRole findRoleByRole(String role);

	UserRole findUserRoleByUserLogin(String login);

	void save(User user);
}