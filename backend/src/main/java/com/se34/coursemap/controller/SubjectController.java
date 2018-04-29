package com.se34.coursemap.controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.se34.coursemap.entity.RecommendationInfo;
import com.se34.coursemap.entity.RecommendationResult;
import com.se34.coursemap.entity.Subject;
import com.se34.coursemap.service.SubjectService;
import com.se34.coursemap.utill.CustomErrorType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    public ResponseEntity<List<Subject>> getAllSubjects(
            @RequestParam(value = "specialtyName",required = false) String specialtyName,
            @RequestParam(value = "course", required = false) Integer course) {

        List<Subject> subjects;
        if(specialtyName!=null && course!=null){
            subjects = subjectService.findAllBySpecialtyNameAndCourse(specialtyName,course);
        }else if(specialtyName!=null ){
            subjects = subjectService.findAllBySpecialtyName(specialtyName);
        }else if(course!=null){
            subjects = subjectService.findAllByCourse(course);
        }else{
            subjects = subjectService.getAllSubject();
        }
        if (subjects.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}/recommendations", method = RequestMethod.GET)
    public ResponseEntity<RecommendationResult> getRecommendations(@PathVariable("id") int id) {
        RecommendationResult recommendationResult = new RecommendationResult();
        Subject subject = subjectService.getSubject(id);

        try {
            HttpResponse<JsonNode> postResponse = Unirest.post("")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .field("description",subject.getDescription())
                    .field("title",subject.getName())
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }



        HttpResponse<JsonNode> getResponse = null;
        try {
            getResponse = Unirest.get("")
                    .header("accept", "application/json")
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = getResponse.getBody().getArray();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                    recommendationResult
                            .getRecommendationInfoArrayList()
                            .add(new RecommendationInfo(jsonObject.getString("url"),jsonObject.getString("url")));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<>(recommendationResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subject> getSubject(@PathVariable("id") int id) {
        Subject subject = subjectService.getSubject(id);
        if (subject == null) {
            return new ResponseEntity(new CustomErrorType("Subject with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
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
