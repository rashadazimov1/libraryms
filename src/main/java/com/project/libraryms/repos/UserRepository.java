package com.project.libraryms.repos;

import com.project.libraryms.entities.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String  email);
	User findByUsername(String  username);
	Optional<User> findByPassword(String  password);
	boolean existsByEmail(String email);
}
