package com.shop.data.operations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.shop.data.repositories.UserRolesRepository;
import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.UserRole;
import com.shop.data.tables.Users;
import com.shop.others.RepositoriesAccess;

import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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

			   for(UserRole x: found)
				for (Iterator<Users> iterator = x.getUser().iterator(); iterator.hasNext();) {
					Users a = iterator.next();
					if(a.getId() == user.getId()) {
						ROLEPLAYING = x;
					}
				}
			   
				List<String> userRoles = new ArrayList<>();
				userRoles.add(ROLEPLAYING.getRole());
				
			return new CustomUserDetails(user, userRoles);
		}
	}
	
//	public static void reauthenticate(final String username, final String password) {
//		UserDetails userDetails = loadUserByUsername(username);
//
//		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//	}
	
//	public static void reauthenticate1(final String username, final String password) {
//		CustomUserDetailsService userDetailsService = new CustomUserDetailsService(usersRepository, userRolesRepository);
//		//UserCache userCache = getBean("userCache");
//		
//		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//		System.out.println(userDetails.getAuthorities());	
//		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails,
//				password == null ? userDetails.getPassword() : password, userDetails.getAuthorities()));
//		//userCache.removeUserFromCache(username);
//	}
//	
//	public static UserDetails loadUserByUsername1(String login) throws UsernameNotFoundException {
//		User user = usersRepository.findByLogin(login);
//		if (null == user) {
//			throw new UsernameNotFoundException("No user present with username: " + login);
//		} else {
//			List<String> userRoles = userRolesRepository.findRoleByUserLogin(login);
//			return new CustomUserDetails(user, userRoles);
//		}
//	}
//	
//	public static void halo1(String halo) {
//		User admin = CustomUserDetailsService.usersRepository.findByLogin(halo);
//		//System.out.println(admin);
//		
//		Iterable<UserRole> found = CustomUserDetailsService.userRolesRepository.findAll();
//		
//		//System.out.println(found);
//
////		   for(UserRole x: found)
////		        System.out.println(x.getUserroleid());
//		UserRole ROLEPLAYING = null;
//		   
//
//		   for(UserRole x: found)
//			for (Iterator<User> iterator = x.getUser().iterator(); iterator.hasNext();) {
//				User a = iterator.next();
//				if(a.getId() == admin.getId()) {
//					ROLEPLAYING = x;
//				}
//			//	System.out.println(a.getId());
//			}
//		   
//
//			//System.out.println(ROLEPLAYING.getRole());
//			List<String> userRoles = new ArrayList<>();
//			userRoles.add(ROLEPLAYING.getRole());
//
//			CustomUserDetails customUserDetails = new CustomUserDetails(admin, userRoles);
//
////			System.out.println(customUserDetails.getId());
////			System.out.println(customUserDetails.getLogin());
////			System.out.println(customUserDetails.getAuthorities());
//			
//			UserDetails userDetails = customUserDetails;
//
////			System.out.println(userDetails.getAuthorities());
//
//			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//
////
////			System.out.println(authentication.getName());
////			System.out.println(authentication.getPrincipal());
////			System.out.println(authentication.getAuthorities());
//			
//			
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//			
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
//			
//		    
//	}
//	
//	
//	
//	public static void halo(String halo) {
//		User admin = CustomUserDetailsService.usersRepository.findByLogin(halo);
//		//System.out.println(admin);
//		
//		Iterable<UserRole> found = CustomUserDetailsService.userRolesRepository.findAll();
//		
//		//System.out.println(found);
//
////		   for(UserRole x: found)
////		        System.out.println(x.getUserroleid());
//		UserRole ROLEPLAYING = null;
//		   
//
//		   for(UserRole x: found)
//			for (Iterator<User> iterator = x.getUser().iterator(); iterator.hasNext();) {
//				User a = iterator.next();
//				if(a.getId() == admin.getId()) {
//					ROLEPLAYING = x;
//				}
//			//	System.out.println(a.getId());
//			}
//		   
//
//			//System.out.println(ROLEPLAYING.getRole());
//			List<String> userRoles = new ArrayList<>();
//			userRoles.add(ROLEPLAYING.getRole());
//
//			CustomUserDetails customUserDetails = new CustomUserDetails(admin, userRoles);
//
////			System.out.println(customUserDetails.getId());
////			System.out.println(customUserDetails.getLogin());
////			System.out.println(customUserDetails.getAuthorities());
//			
//			UserDetails userDetails = customUserDetails;
//
////			System.out.println(userDetails.getAuthorities());
//
//			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//
////
////			System.out.println(authentication.getName());
////			System.out.println(authentication.getPrincipal());
////			System.out.println(authentication.getAuthorities());
//			
//			
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//			
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
//			System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
//	}

}
