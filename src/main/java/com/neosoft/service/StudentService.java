package com.neosoft.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.model.Student;
import com.neosoft.repository.StudentRepository;
@Service
public class StudentService {
	@Autowired
	StudentRepository repository;

	public Student saveStudent(MultipartFile file, Student student){
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if (fileName.contains("..")) {
				System.out.println("not a valid file");
			}
			InputStream fis = file.getInputStream();
			byte[] data = new byte[fis.available()];
			//student.setPhotoUpload(file);
			student.setPhotoUpload(Base64.getEncoder().encodeToString(data));
			repository.save(student);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return repository.save(student);
	}

	public List<Student> getAllStudents(){
		return repository.findAll();
	}

	public Student getStudentsById(Integer studentId){
		return repository.findById(studentId).orElse(null);
	}
}

