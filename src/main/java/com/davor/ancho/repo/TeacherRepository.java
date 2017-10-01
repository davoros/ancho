package com.davor.ancho.repo;

import com.davor.ancho.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.Collection;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    Teacher findByEmail(String email);

    Collection<Teacher> findByName(String name);

    @Query("SELECT COUNT(*) FROM Teacher t")
    Integer countAll();

}
