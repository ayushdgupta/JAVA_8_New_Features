package com.guptaji.StreamApiDemo.controller;

import com.guptaji.StreamApiDemo.service.StreamServiceDemo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streamDemo")
public class StreamController {
  Logger LOG = LogManager.getLogger(StreamController.class);

  @Autowired private StreamServiceDemo streamServiceDemo;

  @PostMapping
  public ResponseEntity<?> streamDemo() {
    streamServiceDemo.streamApiMethodsDemo();
    return new ResponseEntity<>("Done dana done done", HttpStatus.CREATED);
  }
}
