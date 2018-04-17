package com.se34.coursemap.service;

import com.se34.coursemap.entity.Subject;

public interface SubjectService {
    Subject add(Subject subject);

    void delete(Subject subject);

    Subject edit(Subject subject);
}
