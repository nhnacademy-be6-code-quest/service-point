package com.service.point.config;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    @Value("${rabbit.login.exchange.name}")
    private String signupPointExchangeName;
    @Value("${rabbit.login.queue.name}")
    private String signupPointQueueName;
    @Value("${rabbit.login.routing.key}")
    private String signupPointRoutingKey;


    @Value("${rabbit.review.exchange.name}")
    private String reviewPointExchangeName;
    @Value("${rabbit.review.queue.name}")
    private String reviewPointQueueName;
    @Value("${rabbit.review.routing.key}")
    private String reviewPointRoutingKey;
    @Bean
    DirectExchange reviewPointExchange() {
        return new DirectExchange(reviewPointExchangeName);
    }

    @Bean
    Queue reviewPointQueue() {
        return new Queue(reviewPointQueueName);
    }

    @Bean
    Binding reviewPointBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(reviewPointRoutingKey);
    }
//dlx
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }


}
