package com.davor.ancho.domain;

import javax.persistence.*;

@Entity
public class Course {

    public enum Semester{WINTER, SUMMER}

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Teacher teacher;

    String name;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    public Course() {}

    public Course(Teacher teacher, String name, Semester semester) {
        this.teacher = teacher;
        this.name = name;
        this.semester = semester;
    }

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getName() {
        return name;
    }

    public Semester getSemester() {
        return semester;
    }

    @Override
    public String toString() {
        return String.format("Course: name=%s, semester: %s", name, semester);
    }
}
