package com.project.libraryms.serviceimpl;

import com.project.libraryms.dto.userDto.CreateRequestUser;
import com.project.libraryms.dto.userDto.UpdateRequestUserDto;
import com.project.libraryms.dto.userDto.UserDto;
import com.project.libraryms.dto.userDto.UserDtoConverter;
import com.project.libraryms.entities.User;
import com.project.libraryms.exception.NotFoundException;
import com.project.libraryms.exception.UserNotFoundException;
import com.project.libraryms.mapper.UserMapper;
import com.project.libraryms.rabbitmq.producer.Producer;
import com.project.libraryms.repos.UserRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@Service
@SessionScope
public class UserServiceImpl {

	private final UserRepository userRepository;

	private final PasswordEncoder encoder;

	private final Producer producer;

	private final UserDtoConverter userDtoConverter;

	private final KafkaTemplate<String, String> kafkaTemplate;


	private final UserMapper userMapper;
	private final DirectExchange exchange;

	private final AmqpTemplate rabbitTemplate;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder, Producer producer, UserDtoConverter userDtoConverter, KafkaTemplate<String, String> kafkaTemplate, UserMapper userMapper, DirectExchange exchange, AmqpTemplate rabbitTemplate) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.producer = producer;
		this.userDtoConverter = userDtoConverter;
		this.kafkaTemplate = kafkaTemplate;
		this.userMapper = userMapper;


		this.exchange = exchange;
		this.rabbitTemplate = rabbitTemplate;
	}

	public UserDto createUser(CreateRequestUser createRequestUser) {
		User user = new User();
		user.setFullName(createRequestUser.getFullName());
		user.setEmail(createRequestUser.getEmail());
		user.setBirthDate(createRequestUser.getBirthDate());
		user.setAddress(createRequestUser.getAddress());
		user.setUsername(createRequestUser.getUsername());
		user.setPassword(encoder.encode(createRequestUser.getPassword()));
		producer.sendQueue(user);
		userRepository.save(user);

		return userDtoConverter.userDtoConverter(user);

	}


	public User getOneUserByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}


	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UserDto> getAllUsers() {
		List<User> userList = (List<User>) userRepository.findAll();
		return userMapper.toDtoList(userList);

	}

	public User saveOneUser(User newUser) {
		return userRepository.save(newUser);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserDto getByUserDtoId(Long id) {
		Optional<User> bookOptional = userRepository.findById(id);
		return bookOptional.map(userMapper::userEntityToDto).orElse(new UserDto());


	}

	public UserDto updateOneUser(Long userId, UpdateRequestUserDto updateRequestUserDto) throws NotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		userOptional.ifPresent(user -> {
			user.setFullName(updateRequestUserDto.getFullName());
			user.setUsername(updateRequestUserDto.getUsername());
			user.setAddress(updateRequestUserDto.getAddress());
			user.setEmail(updateRequestUserDto.getEmail());
			user.setPassword(updateRequestUserDto.getPassword());
			user.setEmail(updateRequestUserDto.getEmail());
			userRepository.save(user);
			String notificationMessage = "Dear customer %s \n Your money transfer request has been succeed. Your new balance is %s";
			System.out.println("Sender(" + user.getId() + ") new account balance: " + user.getFullName());
			String senderMessage = String.format(notificationMessage, user.getId(), user.getUsername());
			kafkaTemplate.send("transfer-notification", senderMessage);
		});
		return userOptional.map(userDtoConverter::userDtoConverter).orElseThrow(() -> new UserNotFoundException("Book not found with id :" + userId));
	}

	public void deleteById(Long userId) {
		try {
			userRepository.deleteById(userId);
		} catch (EmptyResultDataAccessException e) {
			System.out.println("User " + userId + " doesn't exist");
		}
	}


}
