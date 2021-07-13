package com.taesookim.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taesookim.project.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findAll();
	
	@Query("SELECT u from User u WHERE email = ?1")
	User findEmail(String email);
}
