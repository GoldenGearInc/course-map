package com.se34.coursemap.service;

import com.se34.coursemap.entity.Subject;

import java.util.List;

public interface SubjectService {
    void addSubject(Subject subject);

    void deleteSubject(int id);

    void editSubject(Subject subject);

    List<Subject> getAllSubject();

    Subject getSubject(int id);

    List<Subject> findAllBySpecialtyNameAndCourse(String specialtyName, int course);

    List<Subject> findAllBySpecialtyName(String specialtyName);

    List<Subject> findAllByCourse(int course);


}
