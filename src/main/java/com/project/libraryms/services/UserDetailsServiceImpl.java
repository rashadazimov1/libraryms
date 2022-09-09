package com.project.libraryms.services;

import com.project.libraryms.entities.User;
import com.project.libraryms.repos.UserRepository;
import com.project.libraryms.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDetailsServiceImpl implements UserDetailsService {

	private  final UserRepository userRepository;
	
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		return JwtUserDetails.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}

}


