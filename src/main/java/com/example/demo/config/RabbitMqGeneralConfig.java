package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqGeneralConfig {
    // Direct Queues config
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_KEY = "direct_routing_key";

    @Bean
    public Queue directQueue(){
        return new Queue(DIRECT_QUEUE);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBinging(Queue directQueue, DirectExchange directExchange){
        return BindingBuilder
                .bind(directQueue)
                .to(directExchange)
                .with(DIRECT_KEY);
    }


    // Fanout Queues config
    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    @Bean
    public Queue fanoutQueue1(){
        return new Queue(FANOUT_QUEUE_1);
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue(FANOUT_QUEUE_2);
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }

    @Bean Binding fanoutBinding2(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }


    // Topic Queues config
    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    public static final String TOPIC_QUEUE_3 = "topic_queue_3";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String TOPIC_KEY_1 = "queue.1";
    public static final String TOPIC_KEY_2 = "queue.2";
    public static final String TOPIC_KEY_3 = "queue.*";

    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE_1);
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE_2);
    }

    @Bean
    public Queue topicQueue3(){
        return new Queue(TOPIC_QUEUE_3);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(Queue topicQueue1, TopicExchange topicExchange){
        return BindingBuilder
                .bind(topicQueue1)
                .to(topicExchange)
                .with(TOPIC_KEY_1);
    }

    @Bean
    public Binding topicBinding2(Queue topicQueue2, TopicExchange topicExchange){
        return BindingBuilder
                .bind(topicQueue2)
                .to(topicExchange)
                .with(TOPIC_KEY_2);
    }

    @Bean
    public Binding topicBinding3(Queue topicQueue3, TopicExchange topicExchange){
        return BindingBuilder
                .bind(topicQueue3)
                .to(topicExchange)
                .with(TOPIC_KEY_3);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
