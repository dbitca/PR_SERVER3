package com.example.KitchenService.resources;

import com.example.KitchenService.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
//@RequestMapping ("/kitchen")
public class KitchenServiceController {
    Logger logger = LoggerFactory.getLogger(KitchenServiceController.class);
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("kitchen/order")
    public ResponseEntity postOrder(@RequestBody Order order){
        logger.info("Order with id: " + order.getId() + "; items : " + order.getItems() + "; maximum wait: " + order.getMax_wait() + "; priority : "+ order.getPriority() + "; was received by kitchen");
        KitchenServiceCooks.addOrder(order);
        return new ResponseEntity(HttpStatus.OK);
    }

}
