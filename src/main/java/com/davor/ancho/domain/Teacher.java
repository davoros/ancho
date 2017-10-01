package com.davor.ancho.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true, length = 25)
    private String email;


    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;


    public Teacher() {}

    public Teacher(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return String.format("Teacher: id=%d, name=%s, email: %s", id, name, email);
    }
}
