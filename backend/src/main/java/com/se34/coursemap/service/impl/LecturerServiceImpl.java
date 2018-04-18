package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Lecturer;
import com.se34.coursemap.repository.LecturerRepository;
import com.se34.coursemap.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService {

    private final LecturerRepository lecturerRepository;

    @Autowired
    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public void addLecture(Lecturer lecturer) {
        lecturerRepository.saveAndFlush(lecturer);
    }

    @Override
    public void deleteLecture(int id) {
        lecturerRepository.deleteById(id);
    }

    @Override
    public void editLecture(Lecturer lecturer) {
        lecturerRepository.saveAndFlush(lecturer);
    }

    @Override
    public List<Lecturer> getAllLecture() {
        return lecturerRepository.findAll();
    }

    @Override
    public Lecturer getLecture(int id) {
        return lecturerRepository.getOne(id);
    }

}
