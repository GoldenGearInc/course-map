package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Subject;
import com.se34.coursemap.repository.SubjectRepository;
import com.se34.coursemap.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject add(Subject subject) {
        Subject savedSubject = subjectRepository.saveAndFlush(subject);

        return savedSubject;
    }

    @Override
    public void delete(Subject subject) {
        subjectRepository.delete(subject);
    }

    @Override
    public Subject edit(Subject subject) {
        return subjectRepository.saveAndFlush(subject);
    }
}
