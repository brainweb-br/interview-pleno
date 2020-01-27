package br.com.brainweb.interview.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan("br.com.brainweb")
@EnableTransactionManagement
@EnableJpaRepositories(transactionManagerRef = "jpaTransactionManager")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



}
