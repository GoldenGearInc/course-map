package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Student;
import com.se34.coursemap.repository.StudentRepository;
import com.se34.coursemap.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student add(Student student) {
        Student savedStudent = studentRepository.saveAndFlush(student);
        return savedStudent;
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student edit(Student student) {
        return studentRepository.saveAndFlush(student);
    }
}
