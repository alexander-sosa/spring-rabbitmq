//package com.example.demo.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMqFanoutConfig {
//    public static final String QUEUE1 = "fanout_queue_1";
//    public static final String QUEUE2 = "fanout_queue_1";
//    public static final String EXCHANGE = "fanout_exchange";
//
//    @Bean
//    public Queue queue1() {
//        return new Queue(QUEUE1);
//    }
//
//    @Bean
//    public Queue queue2() {
//        return new Queue(QUEUE2);
//    }
//
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding queue1Binding(Queue queue1, FanoutExchange exchange) {
//        return BindingBuilder
//                .bind(queue1)
//                .to(exchange);
//    }
//
//    @Bean
//    public Binding queue2Binding(Queue queue2, FanoutExchange exchange) {
//        return BindingBuilder
//                .bind(queue2)
//                .to(exchange);
//    }
//}
