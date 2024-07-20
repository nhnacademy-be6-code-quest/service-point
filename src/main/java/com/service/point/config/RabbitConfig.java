package com.service.point.config;


import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final String rabbitHost;
    private final int rabbitPort;
    private final String rabbitUsername;
    private final String rabbitPassword;

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
    @Value("${rabbit.use.point.routing.key}")
    private String usePointRoutingKey;
    @Value("${rabbit.use.point.dlq.queue.name}")
    private String usePointDlqQueueName;
    @Value("${rabbit.use.point.dlq.routing.key}")
    private String usePointDlqRoutingName;

    @Value("${rabbit.refund.point.exchange.name}")
    private String refundPointExchangeName;
    @Value("${rabbit.refund.point.queue.name}")
    private String refundPointQueueName;
    @Value("${rabbit.refund.point.routing.key}")
    private String refundPointRoutingKey;
    @Value("${rabbit.refund.point.dlq.queue.name}")
    private String refundPointDlqQueueName;
    @Value("${rabbit.refund.point.dlq.routing.key}")
    private String refundPointDlqRoutingName;

    @Value("${rabbit.usedRefund.point.exchange.name}")
    private String refundUsedPointExchangeName;
    @Value("${rabbit.usedRefund.point.queue.name}")
    private String refundUsedPointQueueName;
    @Value("${rabbit.usedRefund.point.routing.key}")
    private String refundUsedPointRoutingKey;
    @Value("${rabbit.usedRefund.point.dlq.queue.name}")
    private String refundUsedPointDlqQueueName;
    @Value("${rabbit.usedRefund.point.dlq.routing.key}")
    private String refundUsedPointDlqRoutingName;


    private static final String DLQ_EXCHANGE = "x-dead-letter-exchange";
    private static final String DLQ_ROUTING = "x-dead-letter-routing-key";



    @Bean
    DirectExchange reviewPointExchange() {
        return new DirectExchange(reviewPointExchangeName);
    }

    @Bean
    Queue reviewPointQueue() {
        return QueueBuilder.durable(reviewPointQueueName)
            .withArgument(DLQ_EXCHANGE, reviewPointExchangeName)
            .withArgument(DLQ_ROUTING, reviewDlqPointRoutingKey)
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
            .withArgument(DLQ_EXCHANGE, signupPointExchangeName)
            .withArgument(DLQ_ROUTING, signupDlqPointRoutingKey)
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
            .withArgument(DLQ_EXCHANGE, usePointExchangeName)
            .withArgument(DLQ_ROUTING, usePointDlqRoutingName)
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
    Queue refundPointQueue(){
        return QueueBuilder.durable(refundPointQueueName)
            .withArgument(DLQ_EXCHANGE, refundPointExchangeName)
            .withArgument(DLQ_ROUTING, refundPointDlqRoutingName)
            .build();
    }

    @Bean
    Queue refundDlqPointQueue(){
        return QueueBuilder.durable(refundPointDlqQueueName).build();
    }

    @Bean
    Binding refundPointBinding(){
        return BindingBuilder.bind(refundPointQueue()).to(refundPointExchange()).with(refundPointRoutingKey);
    }
    @Bean
    Binding refundDlqPointBinding(){
        return BindingBuilder.bind(refundDlqPointQueue()).to(refundPointExchange()).with(refundPointDlqRoutingName);
    }

    @Bean
    DirectExchange refundPointExchange(){
        return new DirectExchange(refundPointExchangeName);
    }
    @Bean
    DirectExchange refundUsedPointExchange() {
        return new DirectExchange(refundUsedPointExchangeName);
    }

    @Bean
    Queue refundUsedPointQueue() {
        return QueueBuilder.durable(refundUsedPointQueueName)
            .withArgument(DLQ_EXCHANGE, refundUsedPointExchangeName)
            .withArgument(DLQ_ROUTING, refundUsedPointDlqRoutingName)
            .build();
    }
    @Bean
    Queue refundUsedDlqPointQueue() {
        return new Queue(refundUsedPointDlqQueueName);
    }
    @Bean
    Binding refundUsedPointBinding() {
        return BindingBuilder.bind(refundUsedPointQueue()).to(refundUsedPointExchange()).with(refundUsedPointRoutingKey);
    }
    @Bean
    Binding refundUsedDlqPointBinding() {
        return BindingBuilder.bind(refundUsedDlqPointQueue()).to(refundUsedPointExchange()).with(refundUsedPointDlqRoutingName);
    }





    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost, rabbitPort);
        connectionFactory.setUsername(rabbitUsername);
        connectionFactory.setPassword(rabbitPassword);
        return connectionFactory;
    }


}
