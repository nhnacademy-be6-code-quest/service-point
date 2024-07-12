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
@Import({RabbitTest.TestConfig.class, RabbitConfig.class})
class RabbitTest {

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
    void signupPointExchange() {
        DirectExchange loginExchange = context.getBean("signupCouponExchange", DirectExchange.class);
        assertThat(loginExchange).isNotNull();
        assertThat(loginExchange.getName()).isEqualTo("code-quest.client.register.exchange");
    }

    @Test
    void testSignupPointQueue() {
        Queue loginQueue = context.getBean("signupCouponQueue", Queue.class);
        assertThat(loginQueue).isNotNull();
        assertThat(loginQueue.getName()).isEqualTo("code-quest.client.register.queue");
    }

    @Test
    void testSignupPointBinding() {
        Binding loginBinding = context.getBean("signupCouponBinding", Binding.class);
        assertThat(loginBinding).isNotNull();
        assertThat(loginBinding.getRoutingKey()).isEqualTo("code-quest.client.register.key");
    }

    @Test
    void testRabbitTemplate() {
        RabbitTemplate rabbitTemplate = context.getBean("rabbitTemplate", RabbitTemplate.class);
        assertThat(rabbitTemplate).isNotNull();
        assertThat(rabbitTemplate.getMessageConverter()).isInstanceOf(
            Jackson2JsonMessageConverter.class);
    }
}
