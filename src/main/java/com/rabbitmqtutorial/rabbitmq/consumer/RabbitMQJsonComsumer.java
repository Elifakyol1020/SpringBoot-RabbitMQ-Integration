package com.rabbitmqtutorial.rabbitmq.consumer;


import com.rabbitmqtutorial.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonComsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(RabbitMQJsonComsumer.class);

    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("Received JSON message -> %s", user.toString()));
    }
}
