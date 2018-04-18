package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Subject;
import com.se34.coursemap.service.SubjectService;
import com.se34.coursemap.utill.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {


    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubject();
        if (subjects.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subject> getSubject(@PathVariable("id") int id) {
        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            return new ResponseEntity(new CustomErrorType("Subject with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createSubject(@RequestBody Subject subject, UriComponentsBuilder ucBuilder) {
        subjectService.addSubject(subject);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/subject/{id}").buildAndExpand(subject.getSubjectId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") int id, @RequestBody Subject subject) {

        Subject currentSubject = subjectService.getSubject(id);

        if (currentSubject == null) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Subject with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentSubject.setComments(subject.getComments());
        currentSubject.setCourse((byte) subject.getCourse());
        currentSubject.setLecturers(subject.getLecturers());
        currentSubject.setName(subject.getName());
        currentSubject.setSemester((byte) subject.getSemester());
        currentSubject.setSpecialty(subject.getSpecialty());
        currentSubject.setStudents(subject.getStudents());

        subjectService.editSubject(currentSubject);
        return new ResponseEntity<>(currentSubject, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Subject> deleteSubject(@PathVariable("id") int id) {

        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Subjectwith id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
