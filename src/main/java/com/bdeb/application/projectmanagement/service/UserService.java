package com.bdeb.application.projectmanagement.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bdeb.application.projectmanagement.forms.ResourceForm;
import com.bdeb.application.projectmanagement.repository.UserRepository;
import com.bdeb.service.commun.SecurityHeader;
import com.bdeb.service.user.Role;
import com.bdeb.service.user.User;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Value("${application.name}")
	String idAppl;
	
	@Value("${application.password}")
	String password;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SecurityHeader securityHeader = new SecurityHeader();
		securityHeader.setUsername(idAppl);
		securityHeader.setPassword(password);
		
		User user = userRepository.getUser(securityHeader,username);
				
				
		if (null == user) {
			throw new UsernameNotFoundException("User: " + username + " not found");
		}
 
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(user));
	}

	public String getAuthentifiedUser() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
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
	
	public boolean addUserAccount(ResourceForm resourceForm) {
		User user = new User();
		user.setEmail(resourceForm.getEmail());
		user.setUsername(resourceForm.getResource().getUsername());
		user.setPassword(resourceForm.getPassword());
		user.setFirstname(resourceForm.getResource().getFirstName());
		user.setLastname(resourceForm.getResource().getLastName());
		user.setPhonenumber(resourceForm.getPhone());
		user.getRoles().add(Role.fromValue(resourceForm.getRole()));
		
		SecurityHeader securityHeader = new SecurityHeader();
		securityHeader.setUsername(idAppl);
		securityHeader.setPassword(password);
		
		
		return userRepository.addUser(securityHeader, user);
		
	}
	

}
