package com.aos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.aos.*")
public class BookBootApplication {
  public static void main(String[] args) {
    SpringApplication.run(BookBootApplication.class, args);
  }
}
