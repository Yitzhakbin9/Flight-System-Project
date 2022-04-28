package com.example.AirlineProject.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//  ***  FACADE  ***


@Service
public class MessageService {

    @Autowired
    MessageRepo messageRepo;


    public void print() {
        System.out.println("THIS IS FROM MESSAGE");
    }

    public List<MessageDTO> getAllMessages() {
        List<MessageDTO> messages = new ArrayList<>();
        messageRepo.findAll().forEach(messages::add);
        return messages;
    }

    public MessageDTO getMessage(int id) {
        var res = messageRepo.findById(id);
        return res.get();
    }


    public void addMessage(MessageDTO msg) {
        messageRepo.save(msg);
    }

}
