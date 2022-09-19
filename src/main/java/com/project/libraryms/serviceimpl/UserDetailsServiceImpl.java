package com.project.libraryms.serviceimpl;

import com.project.libraryms.entities.User;
import com.project.libraryms.repos.UserRepository;
import com.project.libraryms.security.JwtUserDetails;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public UserDetailsServiceImpl(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOneUserByUserName(username);
		return JwtUserDetails.create(user);
	}

	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}

}


