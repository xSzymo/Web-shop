package com.shop.data.services;

import com.shop.data.repositories.CouponCodesRepository;
import com.shop.data.tables.CouponCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CouponCodesService {
	@Autowired
	private CouponCodesRepository repository;

	public void save(CouponCode couponCode) {
		if (couponCode == null)
			return;
		for (CouponCode x : findAll())
			if (x.getCode().equals(couponCode.getCode()))
				return;
		repository.save(couponCode);
	}

	public void save(Collection<CouponCode> couponCodes) {
		if (couponCodes.size() > 0)
			couponCodes.forEach(
					x -> {
						if (x != null)
							save(x);
					});
	}

	public CouponCode findOne(long id) {
		try {
			return repository.findOne(id);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public CouponCode findOne(CouponCode couponCode) {
		try {
			return repository.findOne(couponCode.getId());
		} catch (NullPointerException e) {
			return null;
		}
	}

	public CouponCode findOneByCode(String name) {
		Iterable<CouponCode> all = findAll();
		try {
			for (CouponCode x : all)
				if (x.getCode().equals(name))
					return x;
		} catch (NullPointerException e) {
			return null;
		}
		return null;
	}

	public CouponCode findOneByCode(CouponCode couponCode) {
		Iterable<CouponCode> all = findAll();

		try {
			String name = couponCode.getCode();
			for (CouponCode x : all)
				if (x.getCode().equals(name))
					return x;
		} catch (NullPointerException e) {
			return null;
		}
		return null;
	}

	public Iterable<CouponCode> findAll() {
		return repository.findAll();
	}

	public void delete(long id) {
		CouponCode foundCouponCode = repository.findById(id);
		delete(foundCouponCode);
	}

	public void delete(CouponCode couponCode) {
		try {
			repository.delete(couponCode.getId());
		} catch (EmptyResultDataAccessException e) {
		} catch (NullPointerException e) {
		}
	}

	public void delete(Collection<CouponCode> category) {
		if (category.size() > 0)
			category.forEach(x -> {
				if (x.getId() != null)
					delete(x);
			});
	}
}
