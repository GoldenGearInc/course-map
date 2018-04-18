package com.se34.coursemap.service;

import com.se34.coursemap.entity.Lecturer;

import java.util.List;

public interface LecturerService {
    void addLecture(Lecturer lecturer);

    void deleteLecture(int id);

    void editLecture(Lecturer lecturer);

    List<Lecturer> getAllLecture();

    Lecturer getLecture(int id);
}
