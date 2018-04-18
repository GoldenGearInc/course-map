package com.se34.coursemap.controller;


import com.se34.coursemap.entity.Institute;
import com.se34.coursemap.service.InstituteService;
import com.se34.coursemap.utill.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/institute")
public class InstituteController {

    private final InstituteService instituteService;

    @Autowired
    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Institute>> getAllInstitutes() {
        List<Institute> institutes = instituteService.getAllInstitute();
        if (institutes.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(institutes, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Institute> getInstitute(@PathVariable("id") int id) {
        Institute institute = instituteService.getInstitute(id);
        if (institute == null) {
            return new ResponseEntity(new CustomErrorType("Institute with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(institute, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createInstitute(@RequestBody Institute institute, UriComponentsBuilder ucBuilder) {

        if (instituteService.getInstitute(institute.getInstituteId()) != null) {
            return new ResponseEntity(new CustomErrorType("Unable to create. A Institute with name " +
                    institute.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        instituteService.addInstitute(institute);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/institute/{id}").buildAndExpand(institute.getInstituteId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Institute> updateInstitute(@PathVariable("id") int id, @RequestBody Institute institute) {

        Institute currentInstitute = instituteService.getInstitute(id);

        if (currentInstitute == null) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Institute with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentInstitute.setName(institute.getName());
        currentInstitute.setSpecialties(institute.getSpecialties());

        instituteService.editInstitute(currentInstitute);
        return new ResponseEntity<>(currentInstitute, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Institute> deleteInstitute(@PathVariable("id") int id) {

        Institute institute = instituteService.getInstitute(id);
        if (institute == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Institute with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        instituteService.deleteInstitute(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
