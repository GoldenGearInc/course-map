package com.se34.coursemap.controller;

import com.se34.coursemap.entity.Student;
import com.se34.coursemap.entity.Subject;
import com.se34.coursemap.service.StudentService;
import com.se34.coursemap.utill.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public ResponseEntity<?> subscribeToSubject(@RequestBody Subject subject,@RequestHeader("Authorization") String authorizationHeader){

        Student user = getCurrentStudent(authorizationHeader);
        if (user!=null) {

            user.getSubjects().add(subject);
            studentService.editStudent(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
    public ResponseEntity<?> unsubscribeToSubject(@RequestBody Subject subject,@RequestHeader("Authorization") String authorizationHeader){
        Student user = getCurrentStudent(authorizationHeader);
        if (user!=null) {

            user.getSubjects().remove(subject);
            studentService.editStudent(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/mysubjects", method = RequestMethod.GET)
    public ResponseEntity<?> getMySubjects(@RequestHeader("Authorization") String authorizationHeader){
        Student user = getCurrentStudent(authorizationHeader);
        if (user!=null) {

            Set<Subject> userSubjects = user.getSubjects();
            return new ResponseEntity<>(userSubjects,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private Student getCurrentStudent(String authorizationHeader){
        Student currentUser = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String authToken = authorizationHeader.substring(7);

            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            currentUser = studentService.findByLogin(username);
        }

        return currentUser;
    }

    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
    public ResponseEntity<?> changeAccountInfo(@RequestBody Student student,@RequestHeader("Authorization") String authorizationHeader){
        Student user = getCurrentStudent(authorizationHeader);
        if (user!=null) {

            user.setFirstName(student.getFirstName());
            user.setLastName(student.getLastName());
            user.setTelephone(student.getTelephone());
            studentService.editStudent(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
}
