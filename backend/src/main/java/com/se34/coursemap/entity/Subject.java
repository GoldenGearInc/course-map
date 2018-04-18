package com.se34.coursemap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subject")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "course", nullable = false)
    @Check(constraints = "course >= 1 AND course <=5")
    private int course;

    @Column(name = "semester", nullable = false)
    @Check(constraints = "course >= 1 AND course <=2")
    private int semester;

    @ManyToOne
    @JoinColumn(name = "specialtyId")
    private Specialty specialty;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private Set<Lecturer> lecturers;

    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    private Set<Student> students;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Subject() { }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int id) {
        this.subjectId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(byte course) {
        this.course = course;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(byte semester) {
        this.semester = semester;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> users) {
        this.students = users;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}

