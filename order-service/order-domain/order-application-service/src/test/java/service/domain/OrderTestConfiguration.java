package service.domain;

import core.domain.OrderDomainService;
import core.domain.OrderDomainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentReqeustMessagePublisher;
import service.domain.ports.output.message.publisher.restaurantapproval.OrderPaidRestaurantRequestMessagePublisher;
import service.domain.ports.output.repository.CustomerRepository;
import service.domain.ports.output.repository.OrderRepository;
import service.domain.ports.output.repository.RestaurantRepository;

@SpringBootApplication(scanBasePackages = "microservices.practice")
class OrderTestConfiguration {

    @Bean
    public OrderCreatedPaymentReqeustMessagePublisher orderCreatedPaymentReqeustMessagePublisher() {
        return Mockito.mock(OrderCreatedPaymentReqeustMessagePublisher.class);
    }

    @Bean
    public OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher() {
        return Mockito.mock(OrderCancelledPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderPaidRestaurantRequestMessagePublisher orderPaidRestaurantRequestMessagePublisher() {
        return Mockito.mock(OrderPaidRestaurantRequestMessagePublisher.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public CustomerRepository customerRepository() {
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository restaurantRepository() {
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderDomainService orderDomainService() {
        return new OrderDomainServiceImpl();
    }

}