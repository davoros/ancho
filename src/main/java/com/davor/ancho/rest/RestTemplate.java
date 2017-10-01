package com.davor.ancho.rest;


import com.davor.ancho.domain.Teacher;
import com.davor.ancho.repo.CourseRepository;
import com.davor.ancho.repo.TeacherRepository;
import com.davor.ancho.rest.exceptions.BadRequest;
import com.davor.ancho.rest.exceptions.NotFound;
import com.davor.ancho.rest.exceptions.PaymentRequired;
import com.davor.ancho.rest.exceptions.Unauthorized;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Api(value = "/", description = "example of REST services")
public class RestTemplate {

    private final TeacherRepository teacherRepository;
    private final CourseRepository coursRepository;

    @Autowired
    public RestTemplate(TeacherRepository teacherRepository, CourseRepository coursRepository) {
        this.teacherRepository = teacherRepository;
        this.coursRepository = coursRepository;
    }


    @ApiOperation(
            value = "Excepton Handling in Rest Controller",
            notes = "For each option an exception is thrown and different status returned",
            response = String.class
    )
    @RequestMapping(path="test/{option}", method = RequestMethod.GET)
    public String example(@PathVariable Integer option){
        String ret = "Try different options and see what happens, available are 0, 1, 2, 3, 4";
        switch (option){
            default: return ret;
            case 1: throw new BadRequest(ret);
            case 2: throw new NotFound(ret);
            case 3: throw new PaymentRequired(ret);
            case 4: throw new Unauthorized(ret);
        }
    }

    // curl -H "Content-Type: application/json" -X POST -d '{"name":"John Deer", "email" : "john.deer@gmail.com"}' http://localhost:18080/ancho/teacher/insert
    @RequestMapping (path="teacher/insert", method = RequestMethod.POST)
    public Teacher createTeacher(@RequestBody Teacher teacher){
       try{
           teacher = teacherRepository.save(teacher);
           return teacher;
       }catch (Exception e){
           throw new BadRequest(e.getMessage());
       }
    }

    //http://localhost:18080/ancho/teacher/1
    @RequestMapping(path = "teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacherById(@PathVariable Long id){
        Teacher teacher = teacherRepository.findOne(id);
        if(teacher == null)
            throw new NotFound("id: " + id);
        return teacher;
    }


    //http://localhost:18080/ancho/teacher?email=john.deer@gmail.com
    @RequestMapping(path = "teacher", method = RequestMethod.GET)
    public Teacher getTeacherByEmail(@RequestParam(value="email") String email){
        Teacher teacher = teacherRepository.findByEmail(email);
        if(teacher == null)
            throw new NotFound("email: " + email);
        return teacher;
    }

    //http://localhost:18080/ancho/teacher/count
    @RequestMapping(path = "teacher/count", method = RequestMethod.GET)
    public Integer getNumberOfTeachers(){
        return teacherRepository.countAll();
    }
}
