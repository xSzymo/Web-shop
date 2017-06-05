package com.shop.data.repositories;

import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
    List<String> findRoleByUserLogin(String login);

    UserRole findRoleByRole(String role);

    UserRole findUserRoleByUserLogin(String login);

    void save(User user);
}