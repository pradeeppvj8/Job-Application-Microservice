package com.pradeep.jobms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JobMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobMsApplication.class, args);
    }

}
