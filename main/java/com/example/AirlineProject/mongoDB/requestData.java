package com.example.AirlineProject.mongoDB;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document
public class requestData {
    @Id
    private int id;
    private String date;
}
