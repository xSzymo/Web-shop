package com.shop.data.services;

import com.shop.data.repositories.UserRolesRepository;
import com.shop.data.tables.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UserRolesService {
    @Autowired
    private UserRolesRepository repository;

    public void save(UserRole userRole) {
        if (userRole != null)
            repository.save(userRole);
    }

    public void save(Collection<UserRole> userRoles) {
        userRoles.forEach(
                x -> {
                    if (x != null)
                        repository.save(x);
                });
    }

    public UserRole findOne(long id) {
        try {
            return repository.findOne(id);
        } catch (Exception e) {
            return null;
        }
    }

    public UserRole findOne(UserRole userRole) {
        try {
            return findOne(userRole.getId());
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Iterable<UserRole> findAll() {
        return repository.findAll();
    }

    public void delete(long id) {
        try {
            repository.delete(id);
        } catch (NullPointerException e) {

        } catch (EmptyResultDataAccessException e) {

        }
    }

    public void delete(UserRole userRole) {
        try {
            delete(userRole.getId());
        } catch (NullPointerException e) {

        }
    }

    public void delete(Collection<UserRole> userRoles) {
        userRoles.forEach(
                x -> {
                    if (x != null)
                        delete(x);
                });
    }
}