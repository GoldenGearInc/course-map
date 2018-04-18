package com.se34.coursemap.service;

import com.se34.coursemap.entity.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    void deleteStudent(int id);

    void editStudent(Student student);

    Student getStudent(int id);

    List<Student> getAllStudent();

}
