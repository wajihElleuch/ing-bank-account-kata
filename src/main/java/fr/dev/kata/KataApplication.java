package fr.dev.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class KataApplication {

    public static void main(String[] args) {
        SpringApplication.run(KataApplication.class, args);
    }

}
