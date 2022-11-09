package com.example.Consumer.application;

import com.example.Consumer.models.Object;
import com.example.Consumer.threads.WorkingThreads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/consumer")
public class ConsumerController {

    @PostMapping("/object")
    public ResponseEntity postObject(@RequestBody Object object){
        Logger logger = LoggerFactory.getLogger(ConsumerController.class);
        logger.info("Object with id: " + object.getId() + " was received by consumer from aggregator");
        WorkingThreads.addObject(object);
        return new ResponseEntity(HttpStatus.OK);
    }

}
