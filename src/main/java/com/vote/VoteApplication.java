package com.vote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.vote.repository"})
@EntityScan(basePackages = {"com.vote.model"})
public class VoteApplication {
//TestAMIN
    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class, args);
    }
}
