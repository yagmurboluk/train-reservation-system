package com.project.trainreservation.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.project.trainreservation")
@EntityScan(basePackages = "com.project.trainreservation.entity")
@EnableJpaRepositories(basePackages = "com.project.trainreservation.repository")
public class TrainReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrainReservationApplication.class, args);
    }
}


