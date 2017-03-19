package com.shop.data.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shop.data.tables.CouponCodes;

public interface CouponCodesRepository extends CrudRepository<CouponCodes, Long> {
	CouponCodes findById(Long id);

	CouponCodes findByCode(String code);
}
