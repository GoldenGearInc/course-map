package com.se34.coursemap.repository;

import com.se34.coursemap.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByLogin(String login);

    Student findByEmail(String email);

}