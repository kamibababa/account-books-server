package com.zytd.account.books;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.zytd.account.books.dao")
@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
public class AccountBooksApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountBooksApplication.class, args);
    }

}
