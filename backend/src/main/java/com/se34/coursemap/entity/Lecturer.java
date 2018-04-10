package com.se34.coursemap.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lecturerId;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "middleName", nullable = false)
    private String middleName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "degree", nullable = false)
    private String degree;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lecturerSubject",
            joinColumns = @JoinColumn(name = "lecturerId"),
            inverseJoinColumns = @JoinColumn(name = "subjectId")
    )
    private Set<Subject> subjects;

    public Lecturer() { }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int id) {
        this.lecturerId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surName) {
        this.lastName = surName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
