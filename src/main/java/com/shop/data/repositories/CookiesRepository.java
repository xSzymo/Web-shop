package com.shop.data.repositories;

import com.shop.data.tables.Cookies;
import org.springframework.data.repository.CrudRepository;

public interface CookiesRepository extends CrudRepository<Cookies, Long> {

    Cookies findById(long parseLong);

    Cookies findByName(String name);

    Cookies findByValue(String value);
}
