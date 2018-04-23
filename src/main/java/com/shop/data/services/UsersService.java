package com.shop.data.services;

import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class UsersService {
	@Autowired
	private UsersRepository repository;
	@Autowired
	private OrdersService ordersService;

	public void save(User user) {
		if (user == null)
			return;
		User actualUser1 = repository.findByeMail(user.geteMail());
		User actualUser = repository.findByLogin(user.getLogin());
		boolean existUser = false;
		if (actualUser != null || actualUser1 != null)
			existUser = true;

		if (existUser) {
			actualUser.setAddress(user.getAddress());
			actualUser.setAge(user.getAge());
			actualUser.setCookieCode(user.getCookieCode());
			actualUser.seteMail(user.geteMail());
			actualUser.setName(user.getName());
			actualUser.setOrders(user.getOrders());
			actualUser.setPassword(user.getPassword());
			actualUser.setLogin(user.getLogin());
			repository.save(actualUser);
		} else {
			user.getOrders().forEach(x -> x.setUser(user));
			repository.save(user);
		}
	}

	public void save(Collection<User> category) {
		if (category.size() > 0)
			category.forEach(
					x -> {
						if (x != null)
							save(x);
					});
	}

	public User findOne(long id) {
		try {
			return repository.findOne(id);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public User findByLogin(String name) {
		try {
			return repository.findByLogin(name);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public User findByLogin(User user) {
		try {
			return repository.findByLogin(user.getLogin());
		} catch (NullPointerException e) {
			return null;
		}
	}

	public User findByEmail(String name) {
		try {
			return repository.findByeMail(name);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public User findOne(User user) {
		try {
			return repository.findOne(user.getId());
		} catch (NullPointerException e) {
			return null;
		}
	}

	public Iterable<User> findAll() {
		return repository.findAll();
	}

	public void delete(long id) {
		User foundBook = repository.findById(id);
		deleteOperation(foundBook);
	}

	public void delete(String name) {
		deleteOperation(repository.findByLogin(name));
	}

	public void delete(User category) {
		delete(category.getId());
	}

	public void delete(Collection<User> category) {
		if (category.size() > 0)
			category.forEach(x -> {
				if (x.getId() != null)
					delete(x.getId());
			});
	}

	private void deleteOperation(User user) {
		if (user == null)
			return;

		user.getOrders().forEach(x -> ordersService.delete(x));

		repository.delete(user.getId());
	}
}
