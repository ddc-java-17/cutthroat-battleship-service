package edu.cnm.deepdive.jata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Basic class to run the server side of JATA
 */
@SpringBootApplication
public class JataServiceApplication {

  /**
   * Default public constructor for main
   */
  public static void main(String[] args) {
    SpringApplication.run(JataServiceApplication.class, args);
  }

}
