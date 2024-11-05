package com.example.robotgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.robotgame.entity.User;
import com.example.robotgame.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	public boolean login(String userName, String password) {
		User user = userRepository.findByUserName(userName);
		if (user != null && user.getUserPass().equals(password)) {
			return true;
		}
		return false;
	}
}