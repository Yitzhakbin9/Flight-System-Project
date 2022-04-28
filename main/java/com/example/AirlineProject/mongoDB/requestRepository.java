package com.example.AirlineProject.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface requestRepository extends MongoRepository<requestData,Integer> {
}
