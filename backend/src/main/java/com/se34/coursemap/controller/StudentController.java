package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Student;
import com.se34.coursemap.service.StudentService;
import com.se34.coursemap.utill.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/student")
@PreAuthorize("hasAuthority('admin')")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        if (students.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity(new CustomErrorType("Student with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
        studentService.addStudent(student);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/student/{id}").buildAndExpand(student.getStudentId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {

        Student currentStudent = studentService.getStudent(id);

        if (currentStudent == null) {
            return new ResponseEntity(new CustomErrorType("Unable to update. Student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentStudent.setComments(student.getComments());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setFirstName(student.getFirstName());
        currentStudent.setLastName(student.getLastName());
        currentStudent.setLogin(student.getLogin());
        currentStudent.setSubjects(student.getSubjects());
        currentStudent.setTelephone(student.getTelephone());

        studentService.editStudent(currentStudent);
        return new ResponseEntity<>(currentStudent, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {

        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity(new CustomErrorType("Unable to delete. Student with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
