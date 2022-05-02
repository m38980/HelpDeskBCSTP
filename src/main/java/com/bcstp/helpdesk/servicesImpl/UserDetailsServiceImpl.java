package com.bcstp.helpdesk.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bcstp.helpdesk.MyUserDetails;
import com.bcstp.helpdesk.models.User;



public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private com.bcstp.helpdesk.repositories.UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.getUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return new MyUserDetails(user);
	}
}
