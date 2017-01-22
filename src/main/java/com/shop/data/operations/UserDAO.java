package com.shop.data.operations;

import org.springframework.beans.factory.annotation.Autowired;

import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Address;
import com.shop.data.tables.Users;
import com.shop.others.RunAtStart;

public class UserDAO {
	public static Users login(String login, String password) {
		Iterable<Users> u = RunAtStart.usersRepository.findAll();
		for (Users x : u)
			if (x.getLogin().equals(login) && x.getPassword().equals(password))
				return x;

		return null;
	}

	public static boolean isUser(String login, String password) {
		Iterable<Users> u = RunAtStart.usersRepository.findAll();
		for (Users x : u)
			if (x.getLogin().equals(login) && x.getPassword().equals(password))
				return true;
		return false;
	}

	public static void register(String login, String password, String eMail, String name, String surname, String street,
			String dateBirth, String country, String city, String postalCode) {

		Users user = new Users();
		user.setLogin(login);
		user.setPassword(password);
		user.setName(surname);
		user.setSurname(surname);
		//u.setDateBirth(new Date()); // add later
		//Address address = new Address(street, postalCode, city, country);
		//user.setAddress(address);
		user.setIsAdmin(false);
		
		RunAtStart.usersRepository.save(user);
	}
}
