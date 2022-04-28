package com.example.AirlineProject.Aspects;

import com.example.AirlineProject.mongoDB.requestData;
import com.example.AirlineProject.mongoDB.requestService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.sql.Timestamp;


@Component
@Aspect
public class beforeAddAspect {

    @Autowired
    requestService rService;
    private static int ID = 0;


    // aspect before 'add' function in Controller package
    @Before("execution(* com.example.AirlineProject.Controller.*.add*(*))")
    public void beforeAdd() {
      ++ID;
        Date d = new Date();
        String date = String.valueOf(new Timestamp(d.getTime()));
        requestData rd = new requestData(ID, date);
        rService.add(rd);
    }

    //aspect before adding new message
    @Before("execution(* com.example.AirlineProject.Message.MessageController.add*(*))")
    public void beforeAddMessage() {
        ++ID;
        Date d = new Date();
        String date = String.valueOf(new Timestamp(d.getTime()));
        requestData rd = new requestData(ID, date);
        rService.add(rd);
    }

}
