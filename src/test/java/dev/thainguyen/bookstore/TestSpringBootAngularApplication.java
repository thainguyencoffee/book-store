package dev.thainguyen.bookstore;

import org.springframework.boot.SpringApplication;

public class TestSpringBootAngularApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpringBootAngularApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
