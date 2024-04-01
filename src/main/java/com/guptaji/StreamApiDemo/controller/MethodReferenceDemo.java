package com.guptaji.StreamApiDemo.controller;

import com.guptaji.StreamApiDemo.service.MethodReferenceService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/methodReference")
public class MethodReferenceDemo {

  Logger LOG = LogManager.getLogger(MethodReferenceDemo.class);

  @Autowired private MethodReferenceService methodReferenceService;

  @GetMapping
  public ResponseEntity<?> methodReferenceDemoRequest() {
    methodReferenceService.methodReferenceDemo();
    return new ResponseEntity<>("Done dana done done", HttpStatus.CREATED);
  }
}
