package com.se34.coursemap.repository;

import com.se34.coursemap.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
   Lecturer findByFirstNameAndLastNameAndMiddleName(String firstName, String lastName, String middleName);
}
