package com.shop.data.services;

import com.shop.data.repositories.CookiesRepository;
import com.shop.data.tables.Cookies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class CookiesService {
	@Autowired
	private CookiesRepository repository;

	public void save(Cookies cookie) {
		if (cookie == null)
			return;

		for (Cookies x : repository.findAll()) {
			if (x.getValue().equals(cookie.getValue()) || x.getName().equals(cookie.getValue()))
				return;
			if (x.getValue().equals(cookie.getName()) || x.getName().equals(cookie.getName()))
				return;
		}
		repository.save(cookie);
	}

	public void save(Collection<Cookies> cookie) {
		if (cookie.size() > 0)
			cookie.forEach(
					x -> {
						if (x != null)
							save(x);
					});
	}

	public Cookies findOne(long id) {
		try {
			return repository.findOne(id);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Cookies findOne(Cookies cookie) {
		try {
			return repository.findOne(cookie.getId());
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Cookies findOneByName(String name) {
		try {
			return repository.findByName(name);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Cookies findOneByName(Cookies cookie) {
		try {
			String name = cookie.getName();
			return repository.findByName(name);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Cookies findOneByValue(String name) {
		Iterable<Cookies> all = findAll();
		try {
			return repository.findByValue(name);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Cookies findOneByValue(Cookies cookie) {
		try {
			String name = cookie.getValue();
			return repository.findByValue(name);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Iterable<Cookies> findAll() {
		return repository.findAll();
	}

	public void delete(long id) {
		Cookies foundBook = repository.findById(id);
		deleteOperation(foundBook);
	}

	public void delete(Cookies cookie) {
		delete(cookie.getId());
	}

	public void delete(Collection<Cookies> cookies) {
		if (cookies.size() > 0)
			cookies.forEach(x -> {
				if (x.getId() != null)
					delete(x.getId());
			});
	}

	/*
	delete user cookie?
	 */
	private void deleteOperation(Cookies cookie) {
		if (cookie == null)
			return;
		repository.delete(cookie.getId());
	}
}
