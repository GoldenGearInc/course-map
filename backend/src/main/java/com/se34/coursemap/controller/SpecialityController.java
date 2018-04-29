package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Specialty;
import com.se34.coursemap.service.SpecialtyService;
import com.se34.coursemap.utill.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/specialty")
public class SpecialityController {

    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialityController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Specialty>> getAllSpecialty() {
        List<Specialty> institutes = specialtyService.getAllSpecialty();
        if (institutes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(institutes, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Specialty> getSpecialty(@PathVariable("id") int id) {
        Specialty subject = specialtyService.getSpecialty(id);
        if (subject == null) {
            return new ResponseEntity(new CustomErrorType("Subject with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createSpecialty(@RequestBody Specialty specialty, UriComponentsBuilder ucBuilder) {

        specialtyService.addSpecialty(specialty);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/speciality/{id}").buildAndExpand(specialty.getSpecialtyId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

}
