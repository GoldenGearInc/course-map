package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Subject;
import com.se34.coursemap.repository.SubjectRepository;
import com.se34.coursemap.service.SubjectService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void addSubject(Subject subject) {
        subjectRepository.saveAndFlush(subject);
    }

    @Override
    public void deleteSubject(int id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public void editSubject(Subject subject) {
        subjectRepository.saveAndFlush(subject);
    }

    @Override
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubject(int id) {
        return subjectRepository.getOne(id);
    }

    @Override
    public List<Subject> findAllBySpecialtyNameAndCourse(String specialtyName, int course) {
        return subjectRepository.findAllBySpecialtyNameAndCourse(specialtyName,course);
    }

    @Override
    public List<Subject> findAllBySpecialtyName(String specialtyName) {
        return subjectRepository.findAllBySpecialtyName(specialtyName);
    }

    @Override
    public List<Subject> findAllByCourse(int course) {
        return subjectRepository.findAllByCourse(course);
    }
}
