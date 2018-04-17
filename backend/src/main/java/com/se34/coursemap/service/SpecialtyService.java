package com.se34.coursemap.service;

import com.se34.coursemap.entity.Specialty;

public interface SpecialtyService {

    Specialty add(Specialty specialty);

    void delete(Specialty specialty);

    Specialty getByName(String name);
}
