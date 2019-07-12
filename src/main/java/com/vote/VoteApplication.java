package com.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.vote.repository"})
@EntityScan(basePackages = {"com.vote.model"})
public class VoteApplication {

    public static void main(String[] args) {
        //aaaa
        SpringApplication.run(VoteApplication.class, args);
    }
}
