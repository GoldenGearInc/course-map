package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Institute;
import com.se34.coursemap.entity.Lecturer;
import com.se34.coursemap.service.LecturerService;
import com.se34.coursemap.utill.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/lecturer")
public class LectureController {

    private final LecturerService lecturerService;

    @Autowired
    public LectureController(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Lecturer>> getAllLectures() {
        List<Lecturer> lecturers = lecturerService.getAllLecture();
        if (lecturers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lecturers, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Lecturer> getLecture(@PathVariable("id") int id) {
        Lecturer lecture = lecturerService.getLecture(id);
        if (lecture == null) {
            return new ResponseEntity(new CustomErrorType("Lecture with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(lecture, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createLecturer(@RequestBody Lecturer lecturer, UriComponentsBuilder ucBuilder) {
        lecturerService.addLecture(lecturer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/lecturer/{id}").buildAndExpand(lecturer.getLecturerId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable("id") int id, @RequestBody Lecturer lecturer) {

        Lecturer currentLecture = lecturerService.getLecture(id);

        if (currentLecture == null) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Lecturer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentLecture.setDegree(lecturer.getDegree());
        currentLecture.setEmail(lecturer.getEmail());
        currentLecture.setFirstName(lecturer.getFirstName());
        currentLecture.setLastName(lecturer.getLastName());
        currentLecture.setMiddleName(lecturer.getMiddleName());
        currentLecture.setSubjects(lecturer.getSubjects());

        lecturerService.editLecture(currentLecture);
        return new ResponseEntity<>(currentLecture, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Institute> deleteLecturer(@PathVariable("id") int id) {

        Lecturer lecture = lecturerService.getLecture(id);
        if (lecture == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Lecturer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        lecturerService.deleteLecture(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
