package com.example.AirlineProject.Message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contactus")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/message")
    public List<MessageDTO> getAll() {
        return messageService.getAllMessages();
    }

    @GetMapping("/message/{id}")
    public MessageDTO get(@PathVariable int id) {
        var res = messageService.getMessage(id);
        return (res != null ? res : new MessageDTO());
    }

    @PostMapping("/message")
    public void add(@RequestBody MessageDTO msg) {
        messageService.addMessage(msg);
    }
}
