package com.example.AirlineProject.mongoDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class requestService {


    @Autowired
    private requestRepository repo;

    public List<requestData> getAll() {
        return repo.findAll();
    }

    public requestData get(int id) {
        var res = repo.findById(id);
        return res.get();
    }

    public void add(requestData request) {
        repo.save(request);
    }

}
