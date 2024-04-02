package com.guptaji.StreamApiDemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonUtils {

  Logger LOG = LogManager.getLogger(CommonUtils.class);

  public CommonUtils() {
    // default constructor
  }

  public CommonUtils(int a) {
    // increment the count by 3
    System.out.print(a + 3);
    System.out.print(" ");
  }

  public static void incrementTheIntByOne(int n) {
    System.out.print(n + 1);
    System.out.print(" ");
  }

  public void incrementTheIntByTwo(int n) {
    System.out.print(n + 2);
    System.out.print(" ");
  }
}
