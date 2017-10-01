package com.davor.ancho.repo;

import com.davor.ancho.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CourseRepository extends JpaRepository<Course, Long> {
    //it generates smth like:
    //SELECT c from Course c WHERE c.teacher.email = :email
    Collection<Course> findByTeacherEmail(String email);
}
