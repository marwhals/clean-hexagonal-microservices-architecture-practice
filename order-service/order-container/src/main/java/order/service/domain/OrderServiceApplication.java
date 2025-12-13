package order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"order.service.dataaccess", "dataaccess.restaurant"})
@EntityScan(basePackages = {"order.service.dataaccess", "dataaccess.restaurant"})
@SpringBootApplication(scanBasePackages = {
        "order.service",
        "kafka"
})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
