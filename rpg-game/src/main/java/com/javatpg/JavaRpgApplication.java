package com.javatpg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaRpgApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaRpgApplication.class, args);
        System.out.println("=================================");
        System.out.println("  Java RPG 서버 시작 완료!");
        System.out.println("  http://localhost:8080");
        System.out.println("=================================");
    }
}
