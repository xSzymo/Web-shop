package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.CouponCode;

public interface CouponCodesRepository extends CrudRepository<CouponCode, Long> {
	CouponCode findById(Long id);

	CouponCode findByCode(String code);
}
