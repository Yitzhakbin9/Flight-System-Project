package com.example.AirlineProject.Message;

import org.springframework.data.repository.CrudRepository;

//  ***  DAO  ***

public interface MessageRepo extends CrudRepository<MessageDTO,Integer> {
}
