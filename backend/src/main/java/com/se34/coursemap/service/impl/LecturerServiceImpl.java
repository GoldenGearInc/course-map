package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Lecturer;
import com.se34.coursemap.repository.LecturerRepository;
import com.se34.coursemap.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Lecturer add(Lecturer lecturer) {
        Lecturer savedLecturer = lecturerRepository.saveAndFlush(lecturer);

        return savedLecturer;
    }

    @Override
    public void delete(Lecturer lecturer) {
        lecturerRepository.delete(lecturer);
    }

    @Override
    public Lecturer edit(Lecturer lecturer) {
        return lecturerRepository.saveAndFlush(lecturer);
    }

    @Override
    public List<Lecturer> getAll() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer getByFullName(String firstName, String lastName, String middleName) {
        return lecturerRepository.findByFirstNameAndLastNameAndMiddleName(firstName, lastName, middleName);
    }
}
