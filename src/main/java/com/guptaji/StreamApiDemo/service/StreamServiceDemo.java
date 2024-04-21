package com.guptaji.StreamApiDemo.service;

import static com.guptaji.StreamApiDemo.constant.Constants.*;

import java.util.ArrayList;
import java.util.List;

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

    // 1. boolean allMatch(Predicate<? super T> predicate)
    // returns true if all the elements of the stream match the predicate.
    System.out.println(animeList.stream().allMatch(temp -> temp.contains("N"))); // false

    // allMatch returns true in case of empty stream
    System.out.println(
        new ArrayList<String>().stream().allMatch(temp -> temp.length() > 0)); // true
  }
}
