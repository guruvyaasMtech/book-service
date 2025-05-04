package com.libManager.book_service;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@SpringBootApplication
@EnableRabbit
public class BookServiceApplication implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AmqpAdmin amqpAdmin;

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        amqpAdmin.declareExchange(new TopicExchange("book.exchange"));
        System.out.println("EXCHANGE DECLARED IN APPLICATION LISTENER");
    }}