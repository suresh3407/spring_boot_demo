/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mypack.demo.service;

import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 *
 * @author suresh
 */
@Service
public interface StudentService {
    public String getCsvContent();
    public void importCsvContent() throws IOException;
}
