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
    // supplier interface. only problem here is it's not giving starting 0 that we can add or tweak
    // logic to give the correct result.
    List<Long> fibonacciSeriesList =
        Stream.generate(new FiboHelper()).limit(20).collect(Collectors.toList());
    System.out.println(fibonacciSeriesList);

    // fibonacci is also possible using iterate function in stream
    List<Integer> fiboList =
        Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0] + t[1]})
            .limit(limit)
            .map(temp -> temp[0])
            .toList();
    System.out.println(fiboList);
  }
}
