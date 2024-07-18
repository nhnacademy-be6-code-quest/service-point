package com.service.point.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    @Value("${rabbit.point.exchange.name}")
    private String signupPointExchangeName;
    @Value("${rabbit.point.queue.name}")
    private String signupPointQueueName;
    @Value("${rabbit.point.routing.key}")
    private String signupPointRoutingKey;
    @Value("${rabbit.point.dlq.queue.name}")
    private String signupDlqPointQueueName;
    @Value("${rabbit.point.dlq.routing.key}")
    private String signupDlqPointRoutingKey;


    @Value("${rabbit.review.exchange.name}")
    private String reviewPointExchangeName;
    @Value("${rabbit.review.queue.name}")
    private String reviewPointQueueName;
    @Value("${rabbit.review.routing.key}")
    private String reviewPointRoutingKey;
    @Value("${rabbit.review.dlq.queue.name}")
    private String reviewDlqPointQueueName;
    @Value("${rabbit.review.dlq.routing.key}")
    private String reviewDlqPointRoutingKey;


    @Value("${rabbit.use.point.exchange.name}")
    private String usePointExchangeName;
    @Value("${rabbit.use.point.queue.name}")
    private String usePointQueueName;
    @Value("${rabbit.use.point.routing.name}")
    private String usePointRoutingKey;
    @Value("${rabbit.use.point.dlq.queue.name}")
    private String usePointDlqQueueName;
    @Value("${rabbit.use.point.dlq.routing.name}")
    private String usePointDlqRoutingName;


    @Bean
    DirectExchange reviewPointExchange() {
        return new DirectExchange(reviewPointExchangeName);
    }

    @Bean
    Queue reviewPointQueue() {
            return QueueBuilder.durable(reviewPointQueueName)
                .withArgument("x-dead-letter-exchange", reviewPointExchangeName)
                .withArgument("x-dead-letter-routing-key", reviewDlqPointRoutingKey)
                .build();
        }
    @Bean
    Queue reviewDlqPointQueue() {
        return new Queue(reviewDlqPointQueueName);
    }
    @Bean
    Binding reviewPointBinding() {
        return BindingBuilder.bind(reviewPointQueue()).to(reviewPointExchange()).with(reviewPointRoutingKey);
    }
    @Bean
    Binding reviewDlqPointBinding() {
        return BindingBuilder.bind(reviewDlqPointQueue()).to(reviewPointExchange()).with(reviewDlqPointRoutingKey);
    }

    @Bean
    DirectExchange signupPointExchange() {
        return new DirectExchange(signupPointExchangeName);
    }

    @Bean
    Queue signupPointQueue() {
        return QueueBuilder.durable(signupPointQueueName)
            .withArgument("x-dead-letter-exchange", signupPointExchangeName)
            .withArgument("x-dead-letter-routing-key", signupDlqPointRoutingKey)
            .build();
    }

    @Bean
    Queue signupDlqPointQueue(){
        return new Queue(signupDlqPointQueueName);
    }

    @Bean
    Binding signupPointBinding() {
        return BindingBuilder.bind(signupPointQueue()).to(signupPointExchange()).with(signupPointRoutingKey);
    }
    @Bean
    Binding signupDlqPointBinding() {
        return BindingBuilder.bind(signupDlqPointQueue()).to(signupPointExchange()).with(signupDlqPointRoutingKey);
    }

    @Bean
    DirectExchange usePointExchange() {
        return new DirectExchange(usePointExchangeName);
    }

    @Bean
    Queue usePointQueue() {
        return QueueBuilder.durable(usePointQueueName)
            .withArgument("x-dead-letter-exchange", usePointExchangeName)
            .withArgument("x-dead-letter-routing-key", usePointDlqRoutingName)
            .build();
    }

    @Bean
    Queue usePointDlqPointQueue() {
        return new Queue(usePointDlqQueueName);
    }

    @Bean
    Binding usePointBinding() {
        return BindingBuilder.bind(usePointQueue()).to(usePointExchange()).with(usePointRoutingKey);
    }

    @Bean
    Binding usePointDlqPointBinding() {
        return BindingBuilder.bind(usePointDlqPointQueue()).to(usePointExchange()).with(usePointDlqRoutingName);
    }


//dlx
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
