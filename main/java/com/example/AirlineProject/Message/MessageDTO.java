package com.example.AirlineProject.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

// Lombok annotations
@Getter             // creates getters
@Setter             // creates setters
@NoArgsConstructor  // creates parameterless constructor
@AllArgsConstructor // creates constructor

@Entity
public class MessageDTO {

    @Id
    private int messageId;
    private String senderName;
    private String content;
}
