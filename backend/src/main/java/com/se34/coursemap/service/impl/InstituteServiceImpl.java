package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Institute;
import com.se34.coursemap.repository.InstituteRepository;
import com.se34.coursemap.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteRepository instituteRepository;

    @Override
    public Institute add(Institute institute) {
        Institute savedInstitute = instituteRepository.saveAndFlush(institute);

        return savedInstitute;
    }

    @Override
    public void delete(Institute institute) {
        instituteRepository.delete(institute);
    }

    @Override
    public Institute edit(Institute institute) {
        return instituteRepository.saveAndFlush(institute);
    }

    @Override
    public List<Institute> getAll() {
        return instituteRepository.findAll();
    }
}
