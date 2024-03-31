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
  }
}
