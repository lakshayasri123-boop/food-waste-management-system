package com.fooddonation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class FoodWasteDonationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FoodWasteDonationApplication.class, args);
    }
}
