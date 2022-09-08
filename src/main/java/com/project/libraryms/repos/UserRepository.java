package com.project.libraryms.repos;

import com.project.libraryms.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String  email);
	User findByUsername(String  username);
	Optional<User> findByPassword(String  password);
	boolean existsByEmail(String email);
}
