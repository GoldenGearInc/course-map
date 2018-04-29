package com.se34.coursemap.service;

import com.se34.coursemap.entity.Specialty;

import java.util.List;

public interface SpecialtyService {
    void addSpecialty(Specialty specialty);

    void deleteSpecialty(int id);

    Specialty getSpecialty(int id);

    List<Specialty> getAllSpecialty();

    void editSpecialty(Specialty specialty);
}
