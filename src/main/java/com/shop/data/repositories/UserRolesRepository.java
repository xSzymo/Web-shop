package com.shop.data.repositories;

import com.shop.data.tables.User;
import com.shop.data.tables.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
    List<String> findRoleByUserLogin(String login);

    UserRole findRoleByRole(String role);

    UserRole findUserRoleByUserLogin(String login);

    void save(User user);
}