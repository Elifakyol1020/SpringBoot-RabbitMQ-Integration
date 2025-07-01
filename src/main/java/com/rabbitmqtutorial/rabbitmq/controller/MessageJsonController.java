package com.rabbitmqtutorial.rabbitmq.controller;


import com.rabbitmqtutorial.rabbitmq.dto.User;
import com.rabbitmqtutorial.rabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMQJsonProducer.sendJsonMesage(user);
        return ResponseEntity.ok("Json Message sent to RabbitMQ successfully");

    }
}
