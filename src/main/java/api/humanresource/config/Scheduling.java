package api.humanresource.config;

import api.humanresource.HumanResourceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Scheduling {
    public static void main(String[] args) {
        SpringApplication.run(HumanResourceApplication.class, args);

    }
}
