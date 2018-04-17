package com.se34.coursemap.service;

import com.se34.coursemap.entity.Lecturer;

import java.util.List;

public interface LecturerService {
    Lecturer add(Lecturer lecturer);
    void delete(Lecturer lecturer);
    Lecturer edit(Lecturer lecturer);
    List<Lecturer> getAll();
}
