package com.project.libraryms.services;

import java.util.Optional;

import com.project.libraryms.dto.userDto.UserDtoConverter;
import com.project.libraryms.entities.User;
import com.project.libraryms.repos.UserRepository;
import com.project.libraryms.requests.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;


@Service
@SessionScope
public class UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	private final UserDtoConverter  userDtoConverter;

	public UserService(UserRepository userRepository,PasswordEncoder encoder, UserDtoConverter userDtoConverter) {
		this.userRepository = userRepository;

		this.encoder = encoder;

		this.userDtoConverter = userDtoConverter;
	}

	public UserRequest createUser(User userRequest) {
		User user = new User();
		user.setFullName(userRequest.getFullName());
		user.setEmail(userRequest.getEmail());
		user.setBirthDate(userRequest.getBirthDate());
		user.setAddress(userRequest.getAddress());
		user.setUsername(userRequest.getUsername());
		user.setPassword(encoder.encode(userRequest.getPassword()));
		 userRepository.save(user);

		return userDtoConverter.userDtoConverter(user);

	}


	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

//	public User getByUsername(String username){
//		return userRepository.findByUsername(username);
//	}
//
//	public boolean existsByUsername(String username){
//		return userRepository.existsByUsername(username);
//	}
//
//	public boolean existsByEmail(String email){
//		return userRepository.existsByEmail(email);
//	}
//
//	public void save(User user){
//		userRepository.save(user);
//	}
//
//
//
//
//

//
	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User getOneUserById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}

	public User updateOneUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUsername(newUser.getUsername());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}else
			return null;
	}

	public void deleteById(Long userId) {
		try {
		userRepository.deleteById(userId);
		}catch(EmptyResultDataAccessException e) {
			System.out.println("User "+userId+" doesn't exist");
		}
	}

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public User getOneUserByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}





	
	
}
