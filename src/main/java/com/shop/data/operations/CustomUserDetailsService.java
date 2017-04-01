package com.shop.data.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Users user = RepositoriesAccess.usersRepository.findByLogin(login);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + login);
		} else {
			Iterable<UserRole> found = RepositoriesAccess.userRolesRepository.findAll();

			UserRole ROLEPLAYING = null;

			for (UserRole x : found)
				for (Iterator<Users> iterator = x.getUser().iterator(); iterator.hasNext();) {
					Users a = iterator.next();
					if (a.getId() == user.getId()) {
						ROLEPLAYING = x;
					}
				}

			List<String> userRoles = new ArrayList<>();
			userRoles.add(ROLEPLAYING.getRole());

			return new CustomUserDetails(user, userRoles);
		}
	}
}
