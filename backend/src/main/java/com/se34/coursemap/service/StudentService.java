package com.se34.coursemap.service;

import com.se34.coursemap.entity.Student;
import com.se34.coursemap.security.model.UserRegisterDTO;
import com.se34.coursemap.utill.EmailExistsException;
import com.se34.coursemap.utill.UsernameExistsException;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    void deleteStudent(int id);

    void editStudent(Student student);

    Student getStudent(int id);

    List<Student> getAllStudent();

    Student findByLogin(String login);

    Student registerNewUserAccount(UserRegisterDTO accountDto) throws EmailExistsException, UsernameExistsException;
}
