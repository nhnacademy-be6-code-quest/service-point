package com.service.point.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import({RabbitConfigTest.TestConfig.class, RabbitConfig.class})
class RabbitConfigTest {

    @Configuration
    static class TestConfig {

        @Bean
        public ConnectionFactory connectionFactory() {
            return new CachingConnectionFactory("localhost");
        }
    }

    @Autowired
    private ApplicationContext context;

    @Test
    void testReviewPointExchange() {
        DirectExchange exchange = context.getBean("reviewPointExchange", DirectExchange.class);
        assertThat(exchange).isNotNull();
        assertThat(exchange.getName()).isEqualTo("code-quest.review.register.exchange");
    }

    @Test
    void testReviewPointQueue() {
        Queue queue = context.getBean("reviewPointQueue", Queue.class);
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo("code-quest.review.register.queue");
    }

    @Test
    void testReviewDlqPointQueue() {
        Queue queue = context.getBean("reviewDlqPointQueue", Queue.class);
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo("code-quest.review.register.dlq.queue");
    }

    @Test
    void testReviewPointBinding() {
        Binding binding = context.getBean("reviewPointBinding", Binding.class);
        assertThat(binding).isNotNull();
        assertThat(binding.getRoutingKey()).isEqualTo("code-quest.review.register.key");
    }

    @Test
    void testReviewDlqPointBinding() {
        Binding binding = context.getBean("reviewDlqPointBinding", Binding.class);
        assertThat(binding).isNotNull();
        assertThat(binding.getRoutingKey()).isEqualTo("code-quest.review.register.dlq.key");
    }

    @Test
    void testSignupPointExchange() {
        DirectExchange exchange = context.getBean("signupPointExchange", DirectExchange.class);
        assertThat(exchange).isNotNull();
        assertThat(exchange.getName()).isEqualTo("code-quest.client.register.point.exchange");
    }

    @Test
    void testSignupPointQueue() {
        Queue queue = context.getBean("signupPointQueue", Queue.class);
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo("code-quest.client.register.point.queue");
    }

    @Test
    void testSignupDlqPointQueue() {
        Queue queue = context.getBean("signupDlqPointQueue", Queue.class);
        assertThat(queue).isNotNull();
        assertThat(queue.getName()).isEqualTo("code-quest.client.register.dlq.point.queue");
    }

    @Test
    void testSignupPointBinding() {
        Binding binding = context.getBean("signupPointBinding", Binding.class);
        assertThat(binding).isNotNull();
        assertThat(binding.getRoutingKey()).isEqualTo("code-quest.client.register.point.key");
    }

    @Test
    void testSignupDlqPointBinding() {
        Binding binding = context.getBean("signupDlqPointBinding", Binding.class);
        assertThat(binding).isNotNull();
        assertThat(binding.getRoutingKey()).isEqualTo("code-quest.client.register.dlq.point.key");
    }

    @Test
    void testRabbitTemplate() {
        RabbitTemplate rabbitTemplate = context.getBean("rabbitTemplate", RabbitTemplate.class);
        assertThat(rabbitTemplate).isNotNull();
        assertThat(rabbitTemplate.getMessageConverter()).isInstanceOf(Jackson2JsonMessageConverter.class);
    }
}
