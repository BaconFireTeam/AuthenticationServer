package com.baconfire.authserver.services;

import com.baconfire.authserver.dao.User.UserDao;
import com.baconfire.authserver.entity.MyUserDetails;
import com.baconfire.authserver.entity.Role;
import com.baconfire.authserver.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> userOptional = Optional.ofNullable(userDao.getUserByUserName(userName));

		userOptional.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		User user = userOptional.get();
		List<Role> roles = userDao.getRolesByUserId(user.getId());
		MyUserDetails myUserDetails = new MyUserDetails(user);
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
		if (roles != null) {
			grantedAuthorities = roles.stream()
					.map(Role::getRoleName)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		}

		myUserDetails.setAuthorities(grantedAuthorities);
		return myUserDetails;
	}
}
