/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.demo.controllers;

import com.mypack.demo.service.StudentService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author suresh
 */
@RestController
public class StudentImportController  {
    @Autowired
   private  StudentService studentService;
        @GetMapping(value="/students",params={"action=import"})
    public void importStudent() throws IOException{
    studentService.importCsvContent();
}
    
}
