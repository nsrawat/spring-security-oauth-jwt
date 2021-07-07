package com.neosoft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.model.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Integer> {

	Optional<Student> findByFirstName(String username);

}
