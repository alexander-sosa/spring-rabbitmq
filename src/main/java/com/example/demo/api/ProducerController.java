package com.example.demo.api;

import com.example.demo.config.RabbitMqGeneralConfig;
import com.example.demo.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
public class ProducerController {

    private final RabbitTemplate template;

    @Autowired
    public ProducerController(RabbitTemplate template) {
        this.template = template;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/direct")
    public String sendDirectExchangeMessage(@RequestBody MessageDto messageDto) {
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        template.convertAndSend(RabbitMqGeneralConfig.DIRECT_EXCHANGE, RabbitMqGeneralConfig.DIRECT_KEY, messageDto);
        return "Direct Exchange message sent!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/fanout")
    public String sendFanoutExchangeMessage(@RequestBody MessageDto messageDto){
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        template.convertAndSend(RabbitMqGeneralConfig.FANOUT_EXCHANGE, "", messageDto);
        return "Fanout Exchange message sent!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/topic1")
    public String sendTopicExchangeMessage(@RequestBody MessageDto messageDto){
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        template.convertAndSend(RabbitMqGeneralConfig.TOPIC_EXCHANGE, RabbitMqGeneralConfig.TOPIC_KEY_1, messageDto);
        return "Topic Exchange 1 message sent!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/topic2")
    public String sendTopicExchangeMessage2(@RequestBody MessageDto messageDto){
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        template.convertAndSend(RabbitMqGeneralConfig.TOPIC_EXCHANGE, RabbitMqGeneralConfig.TOPIC_KEY_2, messageDto);
        return "Topic Exchange 2 message sent!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/v1/api/topic3")
    public String sendTopicExchangeMessage3(@RequestBody MessageDto messageDto){
        messageDto.setMessageId(UUID.randomUUID().toString());
        messageDto.setMessageDate(new Date());
        template.convertAndSend(RabbitMqGeneralConfig.TOPIC_EXCHANGE, RabbitMqGeneralConfig.TOPIC_KEY_3, messageDto);
        return "Topic Exchange 3 message sent!";
    }
}
