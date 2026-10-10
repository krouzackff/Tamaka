package com.example.tamaka;

import org.springframework.boot.SpringApplication;

public class TestTamakaApplication {

    public static void main(String[] args) {
        SpringApplication.from(TamakaApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
