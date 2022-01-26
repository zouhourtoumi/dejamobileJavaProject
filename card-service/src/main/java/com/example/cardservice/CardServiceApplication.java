package com.example.cardservice;

import com.example.cardservice.dao.CardRepository;
import com.example.cardservice.entities.Card;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.stream.Stream;

@EnableDiscoveryClient
@SpringBootApplication
public class CardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CardRepository cardRepository){
        return args -> {
            Stream.of("A","B","C").forEach(card->{
                cardRepository.save(new Card(null,card,100+Math.random()*900));
            });
            cardRepository.findAll().forEach(System.out::println);
        };
    }

}
