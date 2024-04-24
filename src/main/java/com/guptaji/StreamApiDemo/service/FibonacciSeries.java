package com.guptaji.StreamApiDemo.service;

import com.guptaji.StreamApiDemo.service.helper.FiboHelper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class FibonacciSeries {

  public void generateFibonacciUsingStream(int limit) {

    // here we tried using lambda
    AtomicInteger first = new AtomicInteger();
    AtomicInteger second = new AtomicInteger(1);
    List<Integer> collect =
        Stream.generate(
                () -> {
                  int localFirst = first.get();
                  int localSecond = second.get();
                  System.out.println(localFirst);
                  int temp = localSecond;
                  localSecond = localFirst + localSecond;
                  localFirst = temp;
                  first.set(temp);
                  second.set(localSecond);
                  return localFirst;
                })
            .limit(limit)
            .collect(Collectors.toList());

    System.out.println(collect);

    // let's give a try using FI so first we need to create one class which will implement
    // supplier interface.
    // only problem here is it's not giving starting 0 that we can add or tweak logic to
    // give the correct result.
    Stream.generate(new FiboHelper()).limit(20).forEach(System.out::println);
  }
}
