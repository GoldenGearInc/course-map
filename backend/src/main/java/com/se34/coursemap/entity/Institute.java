package com.se34.coursemap.entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "institute")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "instituteId")*/
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instituteId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "institute", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Specialty> specialties;

    public Institute() { }

    public int getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(int id) {
        this.instituteId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }
}
