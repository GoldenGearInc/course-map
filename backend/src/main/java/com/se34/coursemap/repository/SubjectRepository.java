package com.se34.coursemap.repository;

import com.se34.coursemap.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findAllBySpecialtyNameAndCourse(String specialtyName, int course);
    List<Subject> findAllBySpecialtyName(String specialtyName);
    List<Subject> findAllByCourse( int course);

}
