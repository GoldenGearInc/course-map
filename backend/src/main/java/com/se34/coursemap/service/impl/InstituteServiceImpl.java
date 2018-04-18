package com.se34.coursemap.service.impl;

import com.se34.coursemap.entity.Institute;
import com.se34.coursemap.repository.InstituteRepository;
import com.se34.coursemap.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteServiceImpl implements InstituteService {

    private final InstituteRepository instituteRepository;

    @Autowired
    public InstituteServiceImpl(InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    @Override
    public void addInstitute(Institute institute) {
        instituteRepository.saveAndFlush(institute);
    }

    @Override
    public void deleteInstitute(int id) {
        instituteRepository.deleteById(id);
    }

    @Override
    public void editInstitute(Institute institute) {
        instituteRepository.saveAndFlush(institute);
    }

    @Override
    public List<Institute> getAllInstitute() {
        return instituteRepository.findAll();
    }

    @Override
    public Institute getInstitute(int id) {
        return instituteRepository.getOne(id);
    }
}
