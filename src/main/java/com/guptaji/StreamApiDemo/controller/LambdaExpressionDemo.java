package com.guptaji.StreamApiDemo.controller;

import com.guptaji.StreamApiDemo.service.LambdaExpressionService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lambdaDemo")
public class LambdaExpressionDemo {

  Logger LOG = LogManager.getLogger(LambdaExpressionService.class);

  @Autowired private LambdaExpressionService lambdaExpressionService;

  @GetMapping
  public ResponseEntity<?> useLambda() {
    lambdaExpressionService.implementLambdaDemo();
    return new ResponseEntity<>("Lamda done", HttpStatus.OK);
  }
}
