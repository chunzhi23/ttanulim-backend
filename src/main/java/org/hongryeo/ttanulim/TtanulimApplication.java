package org.hongryeo.ttanulim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TtanulimApplication {

  public static void main(String[] args) {
    SpringApplication.run(TtanulimApplication.class, args);
  }
}
