package com.rabbitmqtutorial.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;
    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueueName;
    //spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //spring bean for rabbitmq queue(store json messages)
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
    }

    //spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    //binding between json queue and exchange using routing key
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(routingJsonKey);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    //spring bean for RabbitTemplate
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
