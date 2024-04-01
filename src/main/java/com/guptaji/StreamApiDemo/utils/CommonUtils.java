package com.guptaji.StreamApiDemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonUtils {

  Logger LOG = LogManager.getLogger(CommonUtils.class);

  public static void incrementTheIntByOne(int n) {
    System.out.println(n + 1);
    ;
  }
}
