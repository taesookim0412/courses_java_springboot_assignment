package com.taesookim.project.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.taesookim.project.models.User;
import com.taesookim.project.repositories.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	public UserService(UserRepository userRepo) {
		this.userRepository = userRepo;
	}
	public User findEmail(String email) {
		return userRepository.findEmail(email);
	}
	public void save(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		userRepository.save(user);
	}
	public boolean authenticateUser(String email, String password) {
		User user = userRepository.findEmail(email);
		if (user == null) {
			return false;
		}
		else {
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	public User findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
}
