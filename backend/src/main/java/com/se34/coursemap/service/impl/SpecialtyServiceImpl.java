package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Specialty;
import com.se34.coursemap.repository.SpecialtyRepository;
import com.se34.coursemap.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;

public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Override
    public Specialty add(Specialty specialty) {
        Specialty savedSpecialty = specialtyRepository.saveAndFlush(specialty);

        return savedSpecialty;
    }

    @Override
    public void delete(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    @Override
    public Specialty getByName(String name) {
        return specialtyRepository.findByName(name);
    }
}
