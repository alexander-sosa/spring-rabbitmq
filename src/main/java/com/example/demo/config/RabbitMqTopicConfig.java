//package com.example.demo.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMqTopicConfig {
//    // queue names
//    public static final String QUEUE1 = "topic_queue_1";
//    public static final String QUEUE2 = "topic_queue_2";
//    public static final String QUEUE3 = "topic_queue_3";
//
//    // exchange name
//    public static final String EXCHANGE = "topic_exchange";
//
//    // routing keys
//    public static final String KEY1 = "queue.1";
//    public static final String KEY2 = "queue.2";
//    public static final String KEY3 = "queue.*";
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
//    public Queue queue3() {
//        return new Queue(QUEUE3);
//    }
//
//    @Bean
//    public TopicExchange topicExchange(){
//        return new TopicExchange(EXCHANGE);
//    }
//
//    @Bean
//    public Binding topic1Binding(Queue queue1, TopicExchange exchange) {
//        return BindingBuilder
//                .bind(queue1)
//                .to(exchange)
//                .with(KEY1);
//    }
//
//    @Bean
//    public Binding topic2Binding(Queue queue2, TopicExchange exchange) {
//        return BindingBuilder
//                .bind(queue2)
//                .to(exchange)
//                .with(KEY2);
//    }
//
//    @Bean
//    public Binding topic3Binding(Queue queue3, TopicExchange exchange) {
//        return BindingBuilder
//                .bind(queue3)
//                .to(exchange)
//                .with(KEY3);
//    }
//}
