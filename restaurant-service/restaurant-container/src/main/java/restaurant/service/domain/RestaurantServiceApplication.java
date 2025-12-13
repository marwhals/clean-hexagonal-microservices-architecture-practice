package restaurant.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "restaurant.service.dataaccess", "dataaccess" })
@EntityScan(basePackages = { "restaurant.service.dataaccess", "dataaccess" })
@SpringBootApplication(scanBasePackages = {"dataaccess.restaurant", "kafka"})
public class RestaurantServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServiceApplication.class, args);
    }
}
