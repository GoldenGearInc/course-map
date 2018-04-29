package com.se34.coursemap.service;

import com.se34.coursemap.entity.Institute;

import java.util.List;

public interface InstituteService {

    void addInstitute(Institute institute);

    void deleteInstitute(int id);

    void editInstitute(Institute institute);

    List<Institute> getAllInstitute();

    Institute getInstitute(int id);
}
