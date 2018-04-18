package com.se34.coursemap.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "specialty")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int specialtyId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "instituteId")
    private Institute institute;

    @OneToMany(mappedBy = "specialty", fetch = FetchType.LAZY)
    private Set<Subject> subjects;

    public Specialty() { }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int id) {
        this.specialtyId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
