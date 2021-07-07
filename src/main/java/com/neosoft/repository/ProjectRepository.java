package com.neosoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.model.Project;


@Repository
public interface ProjectRepository  extends JpaRepository<Project,Integer> {

}
