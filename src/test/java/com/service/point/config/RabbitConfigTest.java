package com.service.point.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class RabbitConfigTest {

    @Mock
    private ConnectionFactory mockConnectionFactory;

    private RabbitConfig rabbitConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize RabbitConfig with dummy values
        rabbitConfig = new RabbitConfig("localhost", 5672, "guest", "guest");

        // Set field values using ReflectionTestUtils
        ReflectionTestUtils.setField(rabbitConfig, "signupPointExchangeName", "signup.exchange");
        ReflectionTestUtils.setField(rabbitConfig, "signupPointQueueName", "signup.queue");
        ReflectionTestUtils.setField(rabbitConfig, "signupPointRoutingKey", "signup.key");
        ReflectionTestUtils.setField(rabbitConfig, "signupDlqPointQueueName", "signup.dlq.queue");
        ReflectionTestUtils.setField(rabbitConfig, "signupDlqPointRoutingKey", "signup.dlq.key");

        ReflectionTestUtils.setField(rabbitConfig, "reviewPointExchangeName", "review.exchange");
        ReflectionTestUtils.setField(rabbitConfig, "reviewPointQueueName", "review.queue");
        ReflectionTestUtils.setField(rabbitConfig, "reviewPointRoutingKey", "review.key");
        ReflectionTestUtils.setField(rabbitConfig, "reviewDlqPointQueueName", "review.dlq.queue");
        ReflectionTestUtils.setField(rabbitConfig, "reviewDlqPointRoutingKey", "review.dlq.key");

        ReflectionTestUtils.setField(rabbitConfig, "usePointExchangeName", "use.exchange");
        ReflectionTestUtils.setField(rabbitConfig, "usePointQueueName", "use.queue");
        ReflectionTestUtils.setField(rabbitConfig, "usePointRoutingKey", "use.key");
        ReflectionTestUtils.setField(rabbitConfig, "usePointDlqQueueName", "use.dlq.queue");
        ReflectionTestUtils.setField(rabbitConfig, "usePointDlqRoutingName", "use.dlq.key");

        ReflectionTestUtils.setField(rabbitConfig, "refundPointExchangeName", "refund.exchange");
        ReflectionTestUtils.setField(rabbitConfig, "refundPointQueueName", "refund.queue");
        ReflectionTestUtils.setField(rabbitConfig, "refundPointRoutingKey", "refund.key");
        ReflectionTestUtils.setField(rabbitConfig, "refundPointDlqQueueName", "refund.dlq.queue");
        ReflectionTestUtils.setField(rabbitConfig, "refundPointDlqRoutingName", "refund.dlq.key");

        ReflectionTestUtils.setField(rabbitConfig, "refundUsedPointExchangeName", "refundUsed.exchange");
        ReflectionTestUtils.setField(rabbitConfig, "refundUsedPointQueueName", "refundUsed.queue");
        ReflectionTestUtils.setField(rabbitConfig, "refundUsedPointRoutingKey", "refundUsed.key");
        ReflectionTestUtils.setField(rabbitConfig, "refundUsedPointDlqQueueName", "refundUsed.dlq.queue");
        ReflectionTestUtils.setField(rabbitConfig, "refundUsedPointDlqRoutingName", "refundUsed.dlq.key");
    }

    @Test
    void testConnectionFactory() {
        ConnectionFactory factory = rabbitConfig.connectionFactory();
        assertNotNull(factory);
        assertTrue(factory instanceof CachingConnectionFactory);
    }

    @Test
    void testSignupPointExchange() {
        DirectExchange exchange = rabbitConfig.signupPointExchange();
        assertNotNull(exchange);
        assertEquals("signup.exchange", exchange.getName());
    }

    @Test
    void testSignupPointQueue() {
        Queue queue = rabbitConfig.signupPointQueue();
        assertNotNull(queue);
        assertEquals("signup.queue", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void testSignupPointBinding() {
        Queue queue = rabbitConfig.signupPointQueue();
        DirectExchange exchange = rabbitConfig.signupPointExchange();
        Binding binding = rabbitConfig.signupPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("signup.key", binding.getRoutingKey());
    }

    @Test
    void testSignupDlqQueue() {
        Queue queue = rabbitConfig.signupDlqPointQueue();
        assertNotNull(queue);
        assertEquals("signup.dlq.queue", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void testSignupDlqBinding() {
        Queue dlqQueue = rabbitConfig.signupDlqPointQueue();
        DirectExchange exchange = rabbitConfig.signupPointExchange();
        Binding binding = rabbitConfig.signupDlqPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("signup.dlq.key", binding.getRoutingKey());
    }

    @Test
    void testRabbitTemplate() {
        RabbitTemplate template = rabbitConfig.rabbitTemplate(mockConnectionFactory);
        assertNotNull(template);
        assertEquals(mockConnectionFactory, template.getConnectionFactory());
        assertTrue(template.getMessageConverter() instanceof Jackson2JsonMessageConverter);
    }

    @Test
    void testReviewPointExchange() {
        DirectExchange exchange = rabbitConfig.reviewPointExchange();
        assertNotNull(exchange);
        assertEquals("review.exchange", exchange.getName());
    }

    @Test
    void testReviewPointQueue() {
        Queue queue = rabbitConfig.reviewPointQueue();
        assertNotNull(queue);
        assertEquals("review.queue", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void testReviewPointBinding() {
        Queue queue = rabbitConfig.reviewPointQueue();
        DirectExchange exchange = rabbitConfig.reviewPointExchange();
        Binding binding = rabbitConfig.reviewPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("review.key", binding.getRoutingKey());
    }

    @Test
    void testReviewDlqQueue() {
        Queue dlqQueue = rabbitConfig.reviewDlqPointQueue();
        assertNotNull(dlqQueue);
        assertEquals("review.dlq.queue", dlqQueue.getName());
        assertTrue(dlqQueue.isDurable());
    }

    @Test
    void testReviewDlqBinding() {
        Queue dlqQueue = rabbitConfig.reviewDlqPointQueue();
        DirectExchange exchange = rabbitConfig.reviewPointExchange();
        Binding binding = rabbitConfig.reviewDlqPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("review.dlq.key", binding.getRoutingKey());
    }

    @Test
    void testUsePointExchange() {
        DirectExchange exchange = rabbitConfig.usePointExchange();
        assertNotNull(exchange);
        assertEquals("use.exchange", exchange.getName());
    }

    @Test
    void testUsePointQueue() {
        Queue queue = rabbitConfig.usePointQueue();
        assertNotNull(queue);
        assertEquals("use.queue", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void testUsePointBinding() {
        Queue queue = rabbitConfig.usePointQueue();
        DirectExchange exchange = rabbitConfig.usePointExchange();
        Binding binding = rabbitConfig.usePointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("use.key", binding.getRoutingKey());
    }

    @Test
    void testUsePointDlqQueue() {
        Queue dlqQueue = rabbitConfig.usePointDlqPointQueue();
        assertNotNull(dlqQueue);
        assertEquals("use.dlq.queue", dlqQueue.getName());
        assertTrue(dlqQueue.isDurable());
    }

    @Test
    void testUsePointDlqBinding() {
        Queue dlqQueue = rabbitConfig.usePointDlqPointQueue();
        DirectExchange exchange = rabbitConfig.usePointExchange();
        Binding binding = rabbitConfig.usePointDlqPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("use.dlq.key", binding.getRoutingKey());
    }

    @Test
    void testRefundPointExchange() {
        DirectExchange exchange = rabbitConfig.refundPointExchange();
        assertNotNull(exchange);
        assertEquals("refund.exchange", exchange.getName());
    }

    @Test
    void testRefundPointQueue() {
        Queue queue = rabbitConfig.refundPointQueue();
        assertNotNull(queue);
        assertEquals("refund.queue", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void testRefundPointBinding() {
        Queue queue = rabbitConfig.refundPointQueue();
        DirectExchange exchange = rabbitConfig.refundPointExchange();
        Binding binding = rabbitConfig.refundPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("refund.key", binding.getRoutingKey());
    }

    @Test
    void testRefundPointDlqQueue() {
        Queue dlqQueue = rabbitConfig.refundDlqPointQueue();
        assertNotNull(dlqQueue);
        assertEquals("refund.dlq.queue", dlqQueue.getName());
        assertTrue(dlqQueue.isDurable());
    }

    @Test
    void testRefundPointDlqBinding() {
        Queue dlqQueue = rabbitConfig.refundDlqPointQueue();
        DirectExchange exchange = rabbitConfig.refundPointExchange();
        Binding binding = rabbitConfig.refundDlqPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("refund.dlq.key", binding.getRoutingKey());
    }

    @Test
    void testRefundUsedPointExchange() {
        DirectExchange exchange = rabbitConfig.refundUsedPointExchange();
        assertNotNull(exchange);
        assertEquals("refundUsed.exchange", exchange.getName());
    }

    @Test
    void testRefundUsedPointQueue() {
        Queue queue = rabbitConfig.refundUsedPointQueue();
        assertNotNull(queue);
        assertEquals("refundUsed.queue", queue.getName());
        assertTrue(queue.isDurable());
    }

    @Test
    void testRefundUsedPointBinding() {
        Queue queue = rabbitConfig.refundUsedPointQueue();
        DirectExchange exchange = rabbitConfig.refundUsedPointExchange();
        Binding binding = rabbitConfig.refundUsedPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("refundUsed.key", binding.getRoutingKey());
    }

    @Test
    void testRefundUsedPointDlqQueue() {
        Queue dlqQueue = rabbitConfig.refundUsedDlqPointQueue();
        assertNotNull(dlqQueue);
        assertEquals("refundUsed.dlq.queue", dlqQueue.getName());
        assertTrue(dlqQueue.isDurable());
    }

    @Test
    void testRefundUsedPointDlqBinding() {
        Queue dlqQueue = rabbitConfig.refundUsedDlqPointQueue();
        DirectExchange exchange = rabbitConfig.refundUsedPointExchange();
        Binding binding = rabbitConfig.refundUsedDlqPointBinding();

        assertNotNull(binding);
        assertEquals(Binding.DestinationType.QUEUE, binding.getDestinationType());
        assertEquals("refundUsed.dlq.key", binding.getRoutingKey());
    }
}
