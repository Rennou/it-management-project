package com.bdeb.application.projectmanagement.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bdeb.application.projectmanagement.repository.UserRepository;
import com.bdeb.service.user.User;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	private User authentifiedUser = null;;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByUsername(username);
		
		this.authentifiedUser = user;
		
		if (null == user) {
			throw new UsernameNotFoundException("User: " + username + " not found");
		}

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				getAuthorities(user));
	}

	public User getAuthentifiedUser() {
		return authentifiedUser;
	}

	public void setAuthentifiedUser(User authentifiedUser) {
		this.authentifiedUser = authentifiedUser;
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String[] userRoles = user.getRoles().stream().map((role) -> role.value()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}

	@SuppressWarnings("unchecked")
	public boolean hasRole(String role) {
		Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities();
		boolean hasRole = false;
		for (GrantedAuthority authority : authorities) {
			hasRole = authority.getAuthority().equals(role);
			if (hasRole) {
				break;
			}
		}
		return hasRole;
	}

	public boolean hasAnyRole(List<String> roles) {
		for (String role : roles) {
			if (hasRole(role)) {
				return true;
			}
		}
		return false;
	}

}
