package com.neosoft.model;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="student_details")
public class Student {
	
	@Id
	private int studentId;

	private String firstName;

	private String lastName;

	private String emailId;

	private long contactNo;
	
	//@Lob
	@Column(columnDefinition = "LONGBLOB")
	private String photoUpload;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(	name="student_project",
				joinColumns	 =		@JoinColumn(name = "student_id"),
				inverseJoinColumns =  @JoinColumn (name= "project_id")
	)
	private Set<Project> projects;

	public Student(String firstName, String lastName, String emailId, long contactNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contactNo = contactNo;
		
	}

}
