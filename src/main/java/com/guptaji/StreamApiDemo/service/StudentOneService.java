package com.guptaji.StreamApiDemo.service;

import com.guptaji.StreamApiDemo.entity.StudentOne;
import com.guptaji.StreamApiDemo.repositories.StudentOneRepo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentOneService {

  Logger LOG = LogManager.getLogger(StudentOneService.class);

  @Autowired private StudentOneRepo studentOneRepo;

  public void readAllStudentData() {
    List<StudentOne> allStudents = studentOneRepo.findAll();
  }
}
