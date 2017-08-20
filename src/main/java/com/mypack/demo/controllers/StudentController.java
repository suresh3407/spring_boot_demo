/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.demo.controllers;
import com.mypack.demo.entities.Student;
import com.mypack.demo.repositories.StudentRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author suresh
 */
@RestController
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    
    @Autowired
  private   StudentRepository studentRepository;
    @Transactional
    @PostMapping("/students")
    public ResponseEntity<?> saveorUpdate(@RequestBody Student student){
        if(student.getId()==null){
        studentRepository.save(student);
        log.info("saving");
        
        return ResponseEntity.ok("saved");
        }else
        {
            Student std = studentRepository.findOne(student.getId());
            std.setFirstName(student.getFirstName());
            std.setEmail(student.getEmail());
            std.setAddress(student.getAddress());
            return ResponseEntity.ok(std);
            
        }
        
        
    }
    @GetMapping("/students")
    public ResponseEntity<?> findAll()
    {
        List<Student> allStudentList =studentRepository.findAll();
        return ResponseEntity.ok(allStudentList);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) 
    {
        studentRepository.delete(id);
        return ResponseEntity.ok("deleted");
    }
    @GetMapping(value="/students", params = {"firstName"})
    public ResponseEntity<?> search(@RequestParam String firstName){
    List<Student> searchedList= studentRepository.findByFirstNameContaining(firstName);
       return ResponseEntity.ok(searchedList); 
    }
     @GetMapping("/student")
    public ResponseEntity<?> findAllByPage(){
       List<Student> returnedList =  studentRepository.findAll(new PageRequest(1, 2)).getContent();
       return ResponseEntity.ok(returnedList);
    }
  
   }
