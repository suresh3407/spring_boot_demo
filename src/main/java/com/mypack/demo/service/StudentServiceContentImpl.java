/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.demo.service;

import com.mypack.demo.entities.Student;
import com.mypack.demo.repositories.StudentRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author suresh
 */
@Service

public class StudentServiceContentImpl implements StudentService{
    private static final Logger log=LoggerFactory.getLogger(StudentServiceContentImpl.class);
   @Autowired
    private StudentRepository studentrepository;
    @Override
    public String getCsvContent() {
     List<Student> stdList =   studentrepository.findAll();
     StringBuilder builder=new StringBuilder( createHeader());
    builder.append("\n");
    builder.append(createBody(stdList));
    return builder.toString();
    
     
        
    }
    /**
     * It creates a header like Name,email,address
     * @return 
     */
    public String createHeader(){
        return "Name,Email,Address";
    }
    public String createBody(List<Student> stdList){
        StringBuilder builder=new StringBuilder();
        for(Student std:stdList){
            StringJoiner joiner=new StringJoiner(",");
            joiner.add(std.getFirstName());
            joiner.add(std.getEmail());
            joiner.add(std.getAddress());
            builder.append(joiner.toString());
            builder.append("\n");
        }
        return builder.toString();
        
    }

    @Override
    public void importCsvContent() throws IOException {
        log.info("importing");
       File file = new File("student.csv");
        FileInputStream inputStream = new FileInputStream(file);
       
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    
     List<Student> stdList= reader.lines().skip(1).map(mapToStudent).collect(Collectors.toList());
     studentrepository.save(stdList);
    
}
    Function<String,Student> mapToStudent=(line)->{
        String[] fields=line.split(",");
        Student student=new Student();
        student.setFirstName(fields[0]);
        student.setEmail(fields[1]);
        student.setAddress(fields[2]);
        return student;
    };
}
