package com.se34.coursemap.service;

import com.se34.coursemap.entity.Student;

public interface StudentService {
    Student add(Student student);

    void delete(Student student);

    Student edit(Student student);
}
