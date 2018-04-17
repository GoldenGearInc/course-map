package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Lecturer;
import com.se34.coursemap.repository.LecturerRepository;
import com.se34.coursemap.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

}
