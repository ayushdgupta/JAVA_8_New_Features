package com.guptaji.StreamApiDemo.service;

import com.guptaji.StreamApiDemo.utils.CommonUtils;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MethodReferenceService {

  Logger LOG = LogManager.getLogger(MethodReferenceService.class);

  public void methodReferenceDemo() {

    int count = 1;

    // 1. Methode reference by static method
    List<Integer> constantIntegerList = Arrays.asList(4, 12, 11);

    // now we want to call CommonUtils method 'incrementByCount' so one way is to iterate
    // the whole list using for loop and call method (legacy way) but another way is to
    // use lambda expression.
    constantIntegerList.forEach(n -> CommonUtils.incrementTheIntByOne(n));
    System.out.println();

    // here instead of using lambda we can use method reference as well bcz here we are referring
    // only method and not doing any other functionality
    constantIntegerList.forEach(CommonUtils::incrementTheIntByOne);
    System.out.println();

    // 2. Method reference by non-static method
    constantIntegerList.forEach(n -> new CommonUtils().incrementTheIntByTwo(n));
    System.out.println();

    // using method reference
    constantIntegerList.forEach(new CommonUtils()::incrementTheIntByTwo);
    System.out.println();

    // 3. Reference to a constructor
    // With lambda
    constantIntegerList.forEach(n -> new CommonUtils(n));
    System.out.println();

    // with Method Reference
    constantIntegerList.forEach(CommonUtils::new);
    System.out.println();
  }
}
