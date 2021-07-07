package com.neosoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.neosoft.jwt.JwtUtil;
import com.neosoft.model.AuthenticationRequest;
import com.neosoft.model.AuthenticationResponse;
import com.neosoft.model.Student;
import com.neosoft.service.CustomUserDetailsService;
import com.neosoft.service.StudentService;


import java.util.List;
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @RequestMapping(value = "/admin/students/add", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public Student uplaodImage(@RequestPart("files") MultipartFile file, @RequestPart("action") Student student) {
        return studentService.saveStudent(file, student);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/students")
    public List<Student> getUsers() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getUsersByStudentId(@PathVariable Integer studentId) {
        return studentService.getStudentsById(studentId);
    }

}

