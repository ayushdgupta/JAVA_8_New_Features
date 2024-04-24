package com.guptaji.StreamApiDemo.service.helper;

import java.util.function.Supplier;

public class FiboHelper implements Supplier<Long> {

  private Long first = 0L, second = 1L;

  @Override
  public Long get() {
    Long next = first + second;
    first = second;
    second = next;
    return first;
  }
}
