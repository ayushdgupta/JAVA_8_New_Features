package com.guptaji.StreamApiDemo.service;

import static com.guptaji.StreamApiDemo.constant.Constants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    // you cannot pass primitive data types in Stream<> i.e. (Stream<int> NOT_ALLOWED) in Java. The
    // Stream<> interface is generic, and the type parameter T must be a reference type. Primitive
    // data types are not reference types, so they cannot be used as the type parameter for
    // Stream<>.

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
    // reflects that filter did n't executed for the entire list, it executed in a shortcircuit
    // manner and the reason is streams are lazily evaluated - it will stop at the first object
    // that matches the condition. so here filetr() and findFirst() combo act as a forEach & break
    // combo.

    // let's see another example
    /*
    for below code the output was
     checking length
     checking length
     appending AA
     checking length
     appending AA
     checking length
     appending AA
     checking length
     checking length
     appending AA
     [Dragon Ball ZAA, BeybladeAA, PokemonAA, DigimonAA]
     so by output we can depict that stream run like
     for(int i = 0; i < length; i++){
       if(data[i].length >= 7){
         appendAA(data[i]);    // and then store the data in the list
       }
     }
     it is running like 'collect' will ask for the data from 'map' and 'map' will ask the data from
     'filter' and 'filter' will ask the data from source stream.
     collect <-- map <-- filter <-- Source stream
     so once filter will get the data then it'll be pass to map then to collect, so this kind of
     process will run for all N elements in the list.
    */
    System.out.println("###############");
    System.out.println(
        animeList.stream()
            .filter(this::lengthGreaterThanOrEqualToSeven)
            .map(this::appendAAToString)
            .collect(Collectors.toList()));

    // 6. static <T> Stream<T> generate(Supplier<T> s)
    // limit is another function in stream which will give only the specified no. of elements
    // from the stream.
    Stream<Integer> generateRandomStream = Stream.generate(new Random()::nextInt);
    generateRandomStream.limit(10).forEach(System.out::println);

    // here our animeList size was 6 and limit was 20 which was greater than our list but code
    // did n't blasted, it prints all 6 anime names so here limit means max size if stream will
    // cross
    // that max size then it'll not print all of them, it'll print only 20.
    animeList.stream().limit(20).forEach(System.out::println);

    System.out.println("###############");
    Stream<String> limitedStream =
        animeList.stream()
            .filter(this::lengthGreaterThanOrEqualToThree)
            .map(this::appendAAToString)
            .limit(4);

    // after writing above rules we will not get any output after line no. 164 so is it because
    // we have not written any SOP() statement and the stream will calculate internally? The answer
    // is NO, bcz Stream always calculate lazily so until or unless there will be no terminal
    // operation e.g. (collect(Collectors.toList())) will present at the end of stream, it'll not
    // calculate the stream. So here in our case as well we have defined the stream i.e. we have
    // given certain rules with Stream that whenever it'll be evaluated then it needs to do filter
    // then map and then check the limit and then the corresponding terminal operation.

    limitedStream.forEach(System.out::println);

    // So after writing above statement our stream is like
    // filter --> map --> limit --> forEach so our output be like
    // so once filter will get the data, it'll pass it to map, map will pass it to the limit, limit
    // will pass it to the forEach().
    /*
      checking length 3
      appending AA
      NarutoAA
      checking length 3
      appending AA
      Dragon Ball ZAA
      checking length 3
      appending AA
      BeybladeAA
      checking length 3
      appending AA
      PokemonAA
    */

    // 7. static <T> Stream<T> iterate(T seed, UnaryOperator<T> f)
    // In iterate function it accepts two arguments one is 'Seed' which is the base element and the
    // second argument will be the Unary operator, so Unary operator will act upon the seed or base
    // element to determine the next result, But with iterate function we should use limit function
    // because it returns an infinite sequential ordered stream i.e. first base element ka result
    // will act as an input for next iteration and so on --
    // iterate(T seed, UnaryOperator<T> f) --> seed -- > f(seed) --> f(f(seed)) --> f(f(f(seed))) --> ....
    System.out.println("iterate function output");
    Stream.iterate(2, temp -> temp + 3).limit(10).forEach(System.out::println);
  }

  private boolean lengthGreaterThanOrEqualToSeven(String s) {
    System.out.println("checking length");
    return s.length() >= 7;
  }

  private boolean lengthGreaterThanOrEqualToThree(String s) {
    System.out.println("checking length 3");
    return s.length() >= 3;
  }

  private String appendAAToString(String s) {
    System.out.println("appending AA");
    return s = s + "AA";
  }
}
