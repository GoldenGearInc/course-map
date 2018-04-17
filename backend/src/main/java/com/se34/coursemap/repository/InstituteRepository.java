package com.se34.coursemap.repository;

import com.se34.coursemap.entity.Institute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteRepository extends JpaRepository<Institute, Integer> {

    Institute findByName(String name);

}
