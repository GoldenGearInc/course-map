package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Specialty;
import com.se34.coursemap.repository.SpecialtyRepository;
import com.se34.coursemap.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void addSpecialty(Specialty specialty) {
        specialtyRepository.saveAndFlush(specialty);
    }

    @Override
    public void deleteSpecialty(int id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public Specialty getSpecialty(int id) {
        return specialtyRepository.getOne(id);
    }

    @Override
    public List<Specialty> getAllSpecialty() {
        return specialtyRepository.findAll();
    }

    @Override
    public void editSpecialty(Specialty specialty) {
        specialtyRepository.saveAndFlush(specialty);
    }

}
