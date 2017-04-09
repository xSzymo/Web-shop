package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.Cookies;

public interface CookiesRepository extends CrudRepository<Cookies, Long> {

	Cookies findById(long parseLong);

	Cookies findByName(String name);
	
	Cookies findByValue(String value);
}
