package com.guptaji.StreamApiDemo.service;

import static com.guptaji.StreamApiDemo.constant.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class StreamServiceDemo {

  Logger LOG = LogManager.getLogger(StreamServiceDemo.class);

  private static List<String> animeList;

  static {
    animeList = new ArrayList<>();
    animeList.add(NARUTO);
    animeList.add(DBZ);
    animeList.add(BEYBLADE);
    animeList.add(POKEMON);
    animeList.add(BORUTO);
    animeList.add(DIGIMON);
  }

  public void streamApiMethodsDemo() {

    // All stream functions require a non-null collection data otherwise NPE will be thrown
    // bcz of Object.NonRequireNull check internally.

    // 1. boolean allMatch(Predicate<? super T> predicate) (Shortcircuit method)
    // returns true if all the elements of the stream match the predicate.
    System.out.println(animeList.stream().allMatch(temp -> temp.contains("N"))); // false

    // allMatch returns true in case of empty stream
    System.out.println(
        new ArrayList<String>().stream().allMatch(temp -> temp.length() > 0)); // true

    // 2. boolean anyMatch(Predicate<? super T> predicate)  (Shortcircuit method)
    // anyMatch() returns true if any single element in the stream satisfy the predicate.
    System.out.println(animeList.stream().anyMatch(temp -> temp.contains("N"))); // true

    System.out.println(
        new ArrayList<String>().stream().anyMatch(temp -> temp.contains("N"))); // false

    // 3. Stream<T> filter(Predicate<? super T> predicate)
    // a. filter is used to apply a filter criteria on a collection so if a element in the list
    // or any collection passes the predicate then it'll allow to move forward further in the
    // next stream.
    // b. Here 'collect' is a method in stream interface which accepts 'Collector' interface
    // Syntax of collect method -- <R, A> R collect(Collector<? super T, A, R> collector);
    // Collector interface accepts final operation in the input after which we will get an o/p.
    // c. 'Collectors' is a final class that provided many methods but in our case it provided
    // the stream data into a list, here we can also use set or many other methods.
    List<String> animeNameContainNWord =
        animeList.stream().filter(temp -> temp.contains("N")).collect(Collectors.toList());
    List<String> animeNameContainNWordTwo =
        animeList.stream().filter(temp -> temp.contains("N")).toList();

    // result of both the list is same, only difference is list created using toList() method
    // (animeNameContainNWordTwo) is immutable while list animeNameContainNWord is mutable.
    System.out.println("Filtered anime list contain N keyword " + animeNameContainNWord); // Naruto
    System.out.println(
        "Filtered anime list contain N keyword Two " + animeNameContainNWordTwo); // Naruto

    // 4. Optional<T> findAny()
    // findAny returns the optional interface in the result so if there will be any value in
    // the stream, it'll return something otherwise empty optional. But result of findAny() is
    // random so it can be used when we want any result from the collection not the specific one.
    Optional<String> any = animeList.stream().findAny();
    System.out.println("findAny() result " + any.orElse("No data present in any"));

    // 5. Optional<T> findFirst()
    // findFirst() will return the first result from the stream but findAny() and finsFirst()
    // will rarely be used directly they will be used after some other operations only like after
    // doing some filter operation we need to extract a single element then it can be used after the
    // filter.
    System.out.println(
        animeList.stream()
            .filter(temp -> temp.length() >= 5)
            .findFirst()
            .orElse("No anime name present for which length is greater than 5")); // Naruto

    // this requirement can also be evaluated using forEach and break combo.Here forEach means loop
    // simple one not stream.forEach (in stream.forEach we can n't use break statement)
    for (String animeName : animeList) {
      if (animeName.length() >= 5) {
        System.out.println("anime name lenght >=5 " + animeName);
        break;
      }
    }

    // so in our first approach where we used filter and findFirst() combo over there how actually
    // the stream work? did it first complete the filter() method and then jump to findFirst() or
    // when the predicate of filter() method returns true at that point of time it executed the
    // findFirst() let's see with the example -
    Optional<String> first =
        animeList.stream().filter(this::lengthGreaterThanOrEqualToSeven).findFirst();
    System.out.println(
        "findFirst result " + first.orElse("No element greater than 7 length found"));

    // Above our 'lengthGreaterThanOrEqualToSeven' function executed 2 times only so it clearly
    // reflects that filter did n't executed for the entire list it executed in a shortcircuit
    // manner
    // the reason is streams are lazily evaluated - it will stop at the first object that matches
    // the condition. so here filetr() and findFirst() combo act as a forEach & break combo.

    // let's see another example
    System.out.println("###############");
    System.out.println(
        animeList.stream()
            .filter(this::lengthGreaterThanOrEqualToSeven)
            .map(this::appendAAToString)
            .collect(Collectors.toList()));
  }

  private boolean lengthGreaterThanOrEqualToSeven(String s) {
    System.out.println("checking length");
    return s.length() >= 7;
  }

  private String appendAAToString(String s) {
    System.out.println("appending AA");
    return s = s + "AA";
  }
}
