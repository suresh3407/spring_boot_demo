/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.demo.controllers;

import com.mypack.demo.service.StudentService;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author suresh
 */
@RestController

public class StudentExportController {
    @Autowired
    private StudentService studentservice;
    @GetMapping(value="/students",params={"action=export"})
    public String export() throws IOException{
        String CsvContent =studentservice.getCsvContent();
        File file = new File("content.csv");
        FileCopyUtils.copy(CsvContent, new FileWriter(file));
        return CsvContent;
    }
    
}
