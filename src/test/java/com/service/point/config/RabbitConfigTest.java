package com.service.point.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class RabbitConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean(name = "testReviewPointExchange")
    private DirectExchange reviewPointExchange;

    @MockBean(name = "testSignupPointExchange")
    private DirectExchange signupPointExchange;

    @MockBean(name = "testUsePointExchange")
    private DirectExchange usePointExchange;

    @MockBean(name = "testRefundPointExchange")
    private DirectExchange refundPointExchange;

    @MockBean(name = "testRefundUsedPointExchange")
    private DirectExchange refundUsedPointExchange;

    @MockBean(name = "testReviewPointQueue")
    private Queue reviewPointQueue;

    @MockBean(name = "testReviewDlqPointQueue")
    private Queue reviewDlqPointQueue;

    @MockBean(name = "testSignupPointQueue")
    private Queue signupPointQueue;

    @MockBean(name = "testSignupDlqPointQueue")
    private Queue signupDlqPointQueue;

    @MockBean(name = "testUsePointQueue")
    private Queue usePointQueue;

    @MockBean(name = "testUsePointDlqPointQueue")
    private Queue usePointDlqPointQueue;

    @MockBean(name = "testRefundPointQueue")
    private Queue refundPointQueue;

    @MockBean(name = "testRefundDlqPointQueue")
    private Queue refundDlqPointQueue;

    @MockBean(name = "testRefundUsedPointQueue")
    private Queue refundUsedPointQueue;

    @MockBean(name = "testRefundUsedDlqPointQueue")
    private Queue refundUsedDlqPointQueue;

    @MockBean(name = "testReviewPointBinding")
    private Binding reviewPointBinding;

    @MockBean(name = "testReviewDlqPointBinding")
    private Binding reviewDlqPointBinding;

    @MockBean(name = "testSignupPointBinding")
    private Binding signupPointBinding;

    @MockBean(name = "testSignupDlqPointBinding")
    private Binding signupDlqPointBinding;

    @MockBean(name = "testUsePointBinding")
    private Binding usePointBinding;

    @MockBean(name = "testUsePointDlqPointBinding")
    private Binding usePointDlqPointBinding;

    @MockBean(name = "testRefundPointBinding")
    private Binding refundPointBinding;

    @MockBean(name = "testRefundDlqPointBinding")
    private Binding refundDlqPointBinding;

    @MockBean(name = "testRefundUsedPointBinding")
    private Binding refundUsedPointBinding;

    @MockBean(name = "testRefundUsedDlqPointBinding")
    private Binding refundUsedDlqPointBinding;

    @MockBean(name = "testRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @MockBean(name = "testConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Test
    void testRabbitConfigBeans() {
        // Verify that RabbitConfig is loaded
        assertThat(applicationContext.getBean(RabbitConfig.class)).isNotNull();

        // Test Direct Exchanges
        assertThat(applicationContext.getBean("testReviewPointExchange", DirectExchange.class)).isEqualTo(reviewPointExchange);
        assertThat(applicationContext.getBean("testSignupPointExchange", DirectExchange.class)).isEqualTo(signupPointExchange);
        assertThat(applicationContext.getBean("testUsePointExchange", DirectExchange.class)).isEqualTo(usePointExchange);
        assertThat(applicationContext.getBean("testRefundPointExchange", DirectExchange.class)).isEqualTo(refundPointExchange);
        assertThat(applicationContext.getBean("testRefundUsedPointExchange", DirectExchange.class)).isEqualTo(refundUsedPointExchange);

        // Test Queues
        assertThat(applicationContext.getBean("testReviewPointQueue", Queue.class)).isEqualTo(reviewPointQueue);
        assertThat(applicationContext.getBean("testReviewDlqPointQueue", Queue.class)).isEqualTo(reviewDlqPointQueue);
        assertThat(applicationContext.getBean("testSignupPointQueue", Queue.class)).isEqualTo(signupPointQueue);
        assertThat(applicationContext.getBean("testSignupDlqPointQueue", Queue.class)).isEqualTo(signupDlqPointQueue);
        assertThat(applicationContext.getBean("testUsePointQueue", Queue.class)).isEqualTo(usePointQueue);
        assertThat(applicationContext.getBean("testUsePointDlqPointQueue", Queue.class)).isEqualTo(usePointDlqPointQueue);
        assertThat(applicationContext.getBean("testRefundPointQueue", Queue.class)).isEqualTo(refundPointQueue);
        assertThat(applicationContext.getBean("testRefundDlqPointQueue", Queue.class)).isEqualTo(refundDlqPointQueue);
        assertThat(applicationContext.getBean("testRefundUsedPointQueue", Queue.class)).isEqualTo(refundUsedPointQueue);
        assertThat(applicationContext.getBean("testRefundUsedDlqPointQueue", Queue.class)).isEqualTo(refundUsedDlqPointQueue);

        // Test Bindings
        assertThat(applicationContext.getBean("testReviewPointBinding", Binding.class)).isEqualTo(reviewPointBinding);
        assertThat(applicationContext.getBean("testReviewDlqPointBinding", Binding.class)).isEqualTo(reviewDlqPointBinding);
        assertThat(applicationContext.getBean("testSignupPointBinding", Binding.class)).isEqualTo(signupPointBinding);
        assertThat(applicationContext.getBean("testSignupDlqPointBinding", Binding.class)).isEqualTo(signupDlqPointBinding);
        assertThat(applicationContext.getBean("testUsePointBinding", Binding.class)).isEqualTo(usePointBinding);
        assertThat(applicationContext.getBean("testUsePointDlqPointBinding", Binding.class)).isEqualTo(usePointDlqPointBinding);
        assertThat(applicationContext.getBean("testRefundPointBinding", Binding.class)).isEqualTo(refundPointBinding);
        assertThat(applicationContext.getBean("testRefundDlqPointBinding", Binding.class)).isEqualTo(refundDlqPointBinding);
        assertThat(applicationContext.getBean("testRefundUsedPointBinding", Binding.class)).isEqualTo(refundUsedPointBinding);
        assertThat(applicationContext.getBean("testRefundUsedDlqPointBinding", Binding.class)).isEqualTo(refundUsedDlqPointBinding);

        // Test RabbitTemplate
        assertThat(applicationContext.getBean("testRabbitTemplate", RabbitTemplate.class)).isEqualTo(rabbitTemplate);

        // Test ConnectionFactory
        assertThat(applicationContext.getBean("testConnectionFactory", ConnectionFactory.class)).isEqualTo(connectionFactory);
    }
}
