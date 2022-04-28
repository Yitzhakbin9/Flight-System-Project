package com.example.AirlineProject.mongoDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
public class requestController {

    @Autowired
    requestService requestService;

    @GetMapping
    public List<requestData> getAll() {
        return requestService.getAll();
    }

    @GetMapping("/{id}")
    public requestData get(@PathVariable int id) {
        var res = requestService.get(id);
        return (res != null ? res : new requestData());
    }

    @PostMapping
    public void add(@RequestBody requestData req) {
        requestService.add(req);
    }

}
