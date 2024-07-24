package com.service.point.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
 class RabbitConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    private RabbitConfig rabbitConfig;

    @BeforeEach
    void setUp() {
        rabbitConfig = applicationContext.getBean(RabbitConfig.class);
    }

    @Test
    void testRabbitConfigBeans() {
        // Verify that RabbitConfig is loaded
        assertThat(rabbitConfig).isNotNull();

        // Test Direct Exchanges
        assertThat(applicationContext.getBean("reviewPointExchange", DirectExchange.class)).isNotNull();
        assertThat(applicationContext.getBean("signupPointExchange", DirectExchange.class)).isNotNull();
        assertThat(applicationContext.getBean("usePointExchange", DirectExchange.class)).isNotNull();
        assertThat(applicationContext.getBean("refundPointExchange", DirectExchange.class)).isNotNull();
        assertThat(applicationContext.getBean("refundUsedPointExchange", DirectExchange.class)).isNotNull();

        // Test Queues
        assertThat(applicationContext.getBean("reviewPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("reviewDlqPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("signupPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("signupDlqPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("usePointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("usePointDlqPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("refundPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("refundDlqPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("refundUsedPointQueue", Queue.class)).isNotNull();
        assertThat(applicationContext.getBean("refundUsedDlqPointQueue", Queue.class)).isNotNull();

        // Test Bindings
        assertThat(applicationContext.getBean("reviewPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("reviewDlqPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("signupPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("signupDlqPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("usePointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("usePointDlqPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("refundPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("refundDlqPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("refundUsedPointBinding", Binding.class)).isNotNull();
        assertThat(applicationContext.getBean("refundUsedDlqPointBinding", Binding.class)).isNotNull();

        // Test RabbitTemplate
        assertThat(applicationContext.getBean(RabbitTemplate.class)).isNotNull();

        // Test ConnectionFactory
        assertThat(applicationContext.getBean(ConnectionFactory.class)).isNotNull();
    }
}
