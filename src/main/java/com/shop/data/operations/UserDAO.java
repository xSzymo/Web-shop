package com.shop.data.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

public class UserDAO {
	public static boolean login(String login, String password) {
		Users admin = RepositoriesAccess.usersRepository.findByLogin(login);
		Iterable<UserRole> found = RepositoriesAccess.userRolesRepository.findAll();
		List<String> userRoles = new ArrayList<>();

		for (UserRole x : found)
			for (Iterator<Users> iterator = x.getUser().iterator(); iterator.hasNext();) {
				Users a = iterator.next();
				if (a.getId() == admin.getId()) {
					userRoles.add(x.getRole());
				}
			}

		if (userRoles.isEmpty()) {
			System.out.println("Anonymous user");
			return false;
		}

		SecurityContextHolder.createEmptyContext();
		CustomUserDetails customUserDetails = new CustomUserDetails(admin, userRoles);
		UserDetails userDetails = customUserDetails;
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return true;
	}

	public static void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

	public static boolean isUser(String login, String password) {
		Iterable<Users> u = RepositoriesAccess.usersRepository.findAll();
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
		// u.setDateBirth(new Date()); // add later
		// Address address = new Address(street, postalCode, city, country);
		// user.setAddress(address);

		RepositoriesAccess.usersRepository.save(user);
	}

}
