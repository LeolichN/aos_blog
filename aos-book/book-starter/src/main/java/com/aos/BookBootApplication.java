package com.aos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.aos.*",exclude = SecurityAutoConfiguration.class)
public class BookBootApplication {
  public static void main(String[] args) {
    SpringApplication.run(BookBootApplication.class, args);
  }
}
