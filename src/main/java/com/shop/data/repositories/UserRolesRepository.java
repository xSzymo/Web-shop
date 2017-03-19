package com.shop.data.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;


public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	List<String> findRoleByUserLogin(String login);
	UserRole findUserRoleByUserLogin(String login);
	void save(Users user);
}