package com.example.demo.bl;

import com.example.demo.config.RabbitMqGeneralConfig;
import com.example.demo.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueBl {
    private final RabbitTemplate template;

    @Autowired
    public QueueBl(RabbitTemplate template){
        this.template = template;
    }

    public String sendWithDirectExchange(Student student){
        template.convertAndSend(
                RabbitMqGeneralConfig.DIRECT_EXCHANGE,
                RabbitMqGeneralConfig.DIRECT_KEY,
                student
        );
        return "Direct Exchange sent";
    }

    public String sendWithFanoutExchange(Student student){
        template.convertAndSend(
                RabbitMqGeneralConfig.FANOUT_EXCHANGE,
                "",
                student
        );
        return "Fanout Exchange sent";
    }

    public String sendWithTopicExchange1(Student student){
        template.convertAndSend(
                RabbitMqGeneralConfig.TOPIC_EXCHANGE,
                RabbitMqGeneralConfig.TOPIC_KEY_1,
                student
        );
        return "Topic Exchange sent (Key: queue.1)";
    }

    public String sendWithTopicExchange2(Student student){
        template.convertAndSend(
                RabbitMqGeneralConfig.TOPIC_EXCHANGE,
                RabbitMqGeneralConfig.TOPIC_KEY_2,
                student
        );
        return "Topic Exchange sent (Key: queue.2)";
    }

    public String sendWithTopicExchange3(Student student){
        template.convertAndSend(
                RabbitMqGeneralConfig.TOPIC_EXCHANGE,
                RabbitMqGeneralConfig.TOPIC_KEY_3,
                student
        );
        return "Topic Exchange sent (Key: queue.*)";
    }
}
