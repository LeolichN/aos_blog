package com.aos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication(scanBasePackages = "com.aos")
@EnableOpenApi
public class BookBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookBootApplication.class, args);
    }
}
