package com.example.AirlineProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirlineProjectApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(AirlineProjectApplication.class, args);

        //FOR SWAGGER:
        //http://localhost:8080/swagger-ui.html

    }

}
