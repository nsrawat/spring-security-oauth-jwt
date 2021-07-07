package com.neosoft.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neosoft.model.Student;
import com.neosoft.repository.StudentRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private StudentRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			return new User("user","user",new ArrayList<>());
	}

}
