package com.guptaji.StreamApiDemo.service;

import static com.guptaji.StreamApiDemo.constant.Constants.*;

import com.guptaji.StreamApiDemo.demoInterfaces.Anime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LambdaExpressionService {

  Logger LOG = LogManager.getLogger(LambdaExpressionService.class);

  public void implementLambdaDemo() {
    // so to define 'favAnime' mehod of Anime interface we have 3 ways -
    // 1. to define a separate class and provide favAnime method implement. (Legacy way)
    // 2. to go with Annonymous object of interface
    Anime favAnimeOne =
        new Anime() {
          @Override
          public void favAnime() {
            LOG.info("Fav anime is {}", NARUTO);
          }
        };
    favAnimeOne.favAnime(); // NARUTO

    // 3. Using lambda (Latest)
    Anime favAnimeTwo = () -> LOG.info("Fav anime is {}", DBZ);
    favAnimeTwo.favAnime(); // DBZ

    // Another good example of lambda is Runnable Interface i.e. Runnable
    // is also a functional interface so here also we can use lambda -
    Thread threadUsingLambda =
        new Thread(
            () ->
                LOG.info(
                    "Thread using Lambda and the thread name is {}",
                    Thread.currentThread().getName()));
    threadUsingLambda.start();
    // so here what we actually do is for creating a thread using Runnable interface which is a FI,
    // we create a class implements Runnable interface and then we override 'run()' method
    // and then we pass that class reference in Thread class to create a thread. so here
    // we shorten that process using lambda like we did above for Anime interface.
  }
}
