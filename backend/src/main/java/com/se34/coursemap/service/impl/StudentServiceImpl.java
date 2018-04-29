package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Student;
import com.se34.coursemap.repository.StudentRepository;
import com.se34.coursemap.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void editStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public Student getStudent(int id) {
        return studentRepository.getOne(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
