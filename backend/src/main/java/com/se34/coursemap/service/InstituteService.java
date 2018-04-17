package com.se34.coursemap.service;

import com.se34.coursemap.entity.Institute;

import java.util.List;

public interface InstituteService {

    Institute add(Institute institute);

    void delete(Institute institute);

    Institute edit(Institute institute);

    List<Institute> getAll();

    Institute getByName(String name);
}
